package service.admin;

import dao.admin.IContractDAO;
import dao.admin.IProjectDAO;
import dao.admin.ISettlementDAO;
import dto.*;
import util.PageInfo;

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
        LocalDate targetSettleDate = firstSettleDay.plusMonths(nextCnt - 1);
        LocalDate today = LocalDate.now();

        System.out.println("[디버그] 계산된 정산일: " + targetSettleDate);
        System.out.println("[디버그] 현재 날짜: " + today);

        // 정산일이 아직 미래라면 (단, 미정산 인원이 남아있는 경우는 허용)
        if (today.isBefore(targetSettleDate) && isAllSettled) {
            System.out.println("정산일이 오늘보다 미래입니다. 정산을 진행할 수 없습니다.");
            return null;
        }

        // 기존에 정산리스트가 있나 체크
        Settlelist existingList = settlementDAO.selectAnySettlelistByProjectIdAndDate(projectId, Date.valueOf(targetSettleDate));
        int cnt = nextCnt;

        Settlelist settlelist = new Settlelist(
                item.getId(),
                Integer.parseInt(prepareSettle.getPosition()),
                projectId,
                prepareSettle.getClientId(),
                project.getProjectName(),
                item.getAmount(),
                Date.valueOf(targetSettleDate),
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
    public HashMap<Integer, AdminSettleProject> filterProjectsWithUnsettled(HashMap<Integer, AdminSettleProject> fullList) {
        HashMap<Integer, AdminSettleProject> filtered = new HashMap<>();
        for (Integer key : fullList.keySet()) {
            AdminSettleProject p = fullList.get(key);
            if (p.getTotalContracts() > p.getSettledCount()) {
                filtered.put(key, p);
                System.out.println("필터링된 프로젝트 : " + p);
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
