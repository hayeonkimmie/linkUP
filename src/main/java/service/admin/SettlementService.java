package service.admin;

import dao.admin.IContractDAO;
import dao.admin.IProjectDAO;
import dao.admin.ISettlementDAO;
import dto.*;

import java.sql.Date;
import java.time.LocalDate;

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

        System.out.println("\uD83D\uDCC5 정산 대상일: " + targetSettleDate);

        if (today.isBefore(targetSettleDate)) {
            System.out.println("⛔ 아직 정산일 도달 전");
            return null;
        }

        // ✅ 동일한 project_id + settle_date 정산리스트 존재 여부 확인
        Settlelist existingList = settlementDAO.selectAnySettlelistByProjectIdAndDate(projectId, Date.valueOf(targetSettleDate));
        int cnt;

        if (existingList != null) {
            System.out.println("⚠️ 동일 정산일의 정산 리스트가 이미 존재함 → cnt: " + existingList.getCnt());
            cnt = existingList.getCnt();
        } else {
            cnt = settlementDAO.getMaxCntByProjectId(projectId) + 1;
            System.out.println("✅ 새로운 정산일 → 회차 cnt 설정: " + cnt);
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

            // 중복 정산 방지: slist_id + contract_id 기준 체크
            boolean alreadySettled = settlementDAO.existsSettlementBySlistIdAndsettleDate(
                    settlelist.getSlistId(), aSettle.getSettleDay());

            if (alreadySettled) {
                System.out.println("⚠️ 이미 정산된 계약입니다 → contractId: " + aSettle.getId());
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
            System.out.println(settlement);
            settlementDAO.insertSettlement(settlement);
        }
    }
}
