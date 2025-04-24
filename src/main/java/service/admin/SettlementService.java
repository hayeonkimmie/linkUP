package service.admin;

import dao.admin.IContractDAO;
import dao.admin.IProjectDAO;
import dao.admin.ISettlementDAO;
import dto.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

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

        LocalDate targetSettleDate = prepareSettle.getSettleDay().toLocalDate();
        LocalDate today = LocalDate.now();

        if (today.isBefore(targetSettleDate)) {
            return null;
        }

        // ✅ 동일한 project_id + settle_date 정산리스트 존재 여부 확인
        Settlelist existingList = settlementDAO.selectAnySettlelistByProjectIdAndDate(projectId, Date.valueOf(targetSettleDate));
        int cnt;

        if (existingList != null) {
            cnt = existingList.getCnt();
        } else {
            cnt = settlementDAO.getMaxCntByProjectId(projectId) + 1;
        }

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

        // 정산리스트는 중복 없이 한 번만 insert
        if (existingList == null) {
            settlementDAO.createSettlelist(settlelist);
            System.out.println("✅ 정산 리스트 생성됨 → " + settlelist);
        } else {
            settlelist.setSlistId(existingList.getSlistId()); // 이후 settlement 등록용 slistId 설정
        }

        return settlelist;
    }

    @Override
    public void createSettlement(Settlelist settlelist, PrepareSettleJson[] item, Integer projectId) throws Exception {
        AdminProjectDetail project = projectDAO.selectProjectDetail(projectId);
        for (PrepareSettleJson p : item) {
            AdminPrepareSettle aSettle = contractDAO.selectInfoForSettleById(p.getId());
            boolean alreadySettled = settlementDAO.existsSettlementBySlistIdAndsettleDate(
                   settlelist.getClientId(), settlelist.getSlistId(), aSettle.getSettleDay());
            if (alreadySettled) {
                continue;
            }

            Settlement settlement = new Settlement(
                    settlelist.getSlistId(),
                    Integer.parseInt(aSettle.getPosition()),
                    aSettle.getClientId(),
                    project.getProjectName(),
                    p.getAmount(),
                    aSettle.getSettleDay(),
                    aSettle.getStartDate(),
                    aSettle.getEndDate(),
                    aSettle.getPosition(),
                    aSettle.getName(),
                    "정산완료",
                    p.getAccount()
            );
            settlementDAO.insertSettlement(settlement);
        }
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


}
