package service.admin;

import dao.admin.IContractDAO;
import dao.admin.IProjectDAO;
import dao.admin.ISettlementDAO;
import dto.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementService implements ISettlementService {

    private final IContractDAO contractDAO;
    private final IProjectDAO projectDAO;
    private final ISettlementDAO settlementDAO;

    public SettlementService(IContractDAO contractDAO, IProjectDAO projectDAO, ISettlementDAO settlementDAO) {
        this.contractDAO = contractDAO;
        this.projectDAO = projectDAO;
        this.settlementDAO = settlementDAO;
    }

    @Override
    public Settlelist createSettleList(PrepareSettleJson item, Integer projectId) throws Exception {
        AdminProjectDetail project = projectDAO.selectProjectDetail(projectId);
        AdminPrepareSettle prepareSettle = contractDAO.selectInfoForSettleById(item.getId());

        LocalDate firstSettleDay = prepareSettle.getSettleDay().toLocalDate(); // 최초 정산일
        int maxCnt = settlementDAO.getMaxCntByProjectId(projectId); // 현재까지 존재하는 최대 회차
        int nextCnt = maxCnt + 1; // 기본은 다음 회차를 가정

        // 🔥 현재 회차(maxCnt)의 미정산 인원이 남아있는지 체크
        boolean isAllSettled = settlementDAO.isAllSettledInCnt(projectId, maxCnt);

        if (!isAllSettled) {
            // 아직 이전 회차 인원 정산이 덜 끝났으면 같은 회차에 추가 정산
            nextCnt = maxCnt;
        }

        // 최초 정산일 + (회차-1)개월 로 실제 정산일자 계산
//        LocalDate targetSettleDate = firstSettleDay.plusMonths(nextCnt - 1);
        LocalDate today = LocalDate.now();

        System.out.println("[디버그] 계산된 정산일: " + firstSettleDay);
        System.out.println("[디버그] 현재 날짜: " + today);

        // 정산일이 아직 미래라면 (단, 미정산 인원이 남아있는 경우는 허용)
        if (today.isBefore(firstSettleDay) && isAllSettled) {
            System.out.println("정산일이 오늘보다 미래입니다. 정산을 진행할 수 없습니다.");
            return null;
        }

        // 기존에 정산리스트가 있나 체크
        Settlelist existingList = settlementDAO.selectAnySettlelistByProjectIdAndDate(projectId, Date.valueOf(firstSettleDay));
        int cnt = nextCnt;

        Settlelist settlelist = new Settlelist(
                item.getId(),
                Integer.parseInt(prepareSettle.getPosition()),
                projectId,
                prepareSettle.getClientId(),
                project.getProjectName(),
                item.getAmount(),
                Date.valueOf(firstSettleDay),
                cnt
        );

        System.out.println("Settlelist 생성됨 → " + settlelist);

        if (existingList == null) {
            settlementDAO.createSettlelist(settlelist);
            System.out.println("✅ 정산 리스트 생성됨 → " + settlelist);
        } else {
            settlelist.setSlistId(existingList.getSlistId());
        }

        return settlelist;
    }


    @Override
    public Settlement createSettlement(Settlelist settlelist, PrepareSettleJson[] item, Integer projectId) throws Exception {
        AdminProjectDetail project = projectDAO.selectProjectDetail(projectId);
        Settlement settlement = null;
        for (PrepareSettleJson p : item) {
            AdminPrepareSettle aSettle = contractDAO.selectInfoForSettleById(p.getId());

            // ✅ 이 부분은 '이미 같은 slistId+freelancer+startDate' 조합으로 정산된 게 있는지 체크해야 함
            boolean alreadySettled = settlementDAO.existsSettlementBySlistIdAndStartEndDate(
                    settlelist.getSlistId(), p.getStart(), p.getEnd(), aSettle.getName());

            if (alreadySettled) {
                continue;
            }

            settlement = new Settlement(
                    settlelist.getSlistId(),
                    Integer.parseInt(aSettle.getPosition()),
                    aSettle.getClientId(),
                    project.getProjectName(),
                    p.getAmount(),
                    Date.valueOf(p.getStart()), // ✅ 이번 회차 시작일
                    Date.valueOf(p.getEnd()),   // ✅ 이번 회차 종료일
                    settlelist.getSettleDate(), // ✅ 정산일은 settlelist의 settle_date 사용
                    aSettle.getPosition(),
                    aSettle.getName(),
                    "정산완료",
                    p.getAccount()
            );
            settlementDAO.insertSettlement(settlement);
        }
        return settlement;
    }


    @Override
    public HashMap<Integer, AdminSettleProject> filterProjectsWithUnsettled() throws Exception {
        LocalDate today = LocalDate.now();
        HashMap<String, Object> params = new HashMap<>();
        LocalDate targetSettleDay = LocalDate.of(today.getYear(), today.getMonth(), 6); // 정산일을 6일로 고정하거나 동적으로 설정 가능
        params.put("targetSettleDay", targetSettleDay);
        HashMap<Integer, AdminSettleProject> fullList = settlementDAO.selectProjectsForSettlementWithParams(params);
        HashMap<Integer, AdminSettleProject> filtered = new HashMap<>();

        for (Integer key : fullList.keySet()) {
            AdminSettleProject p = fullList.get(key);
            System.out.println("\n🎯 대상 프로젝트: " + p.getProjectName());

            LocalDate calculatedSettleDate = null;

            // 1. 정산일 계산
            if (p.getSettleDay() == null) {
                try {
                    int dayOfMonth = p.getSettleDate();
                    calculatedSettleDate = today.withDayOfMonth(Math.min(dayOfMonth, today.lengthOfMonth()));
                    System.out.println("📅 정산 예정일 (기본 날짜 기반): " + calculatedSettleDate);
                } catch (Exception e) {
                    System.out.println("⚠️ 정산일 계산 실패 (settleDate=" + p.getSettleDate() + ")");
                    continue;
                }
            } else {
                calculatedSettleDate = p.getSettleDay().toLocalDate().plusMonths(1);
                System.out.println("📅 정산 예정일 (settleDay 기준 +1달): " + calculatedSettleDate);
            }

            // 2. 필터링 조건
            boolean dateCondition = today.compareTo(calculatedSettleDate) >= 0;
            boolean unfinishedSettle = p.getTotalContracts() > p.getSettledCount();

            System.out.println("🔢 전체 계약: " + p.getTotalContracts() + ", 정산 완료: " + p.getSettledCount());

            if (dateCondition) {
                System.out.println("✅ 필터링 통과: 오늘(" + today + ") >= 정산일(" + calculatedSettleDate + ")");
                filtered.put(key, p);
            } else if (unfinishedSettle) {
                System.out.println("✅ 필터링 통과: 아직 이전 회차 정산 인원 부족");
                filtered.put(key, p);
            } else {
                System.out.println("❌ 필터링 실패: 조건 미충족");
            }
        }

        return filtered;
    }



    @Override
    public List<AdminSettleHistory> getHistoryList(String keyword, String startDate, String endDate, int offset, int limit) throws Exception {
        return null;
    }

    @Override
    public int countHistory(String keyword, String startDate, String endDate) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("keyword", keyword);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        return settlementDAO.countHistory(param);
    }

    @Override
    public List<AdminSettleHistorySummary> selectHistorySummaryList(String keyword, String startDate, String endDate, int offset, int limit) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("keyword", keyword);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("offset", offset);
        param.put("limit", limit);
        return settlementDAO.selectHistorySummaryList(param);
    }

    @Override
    public int countHistorySummary(String keyword, String startDate, String endDate) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("keyword", keyword);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        return settlementDAO.countHistorySummary(param);
    }

}
