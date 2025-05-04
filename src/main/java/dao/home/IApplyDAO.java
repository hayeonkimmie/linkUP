package dao.home;

import dto.Apply;
import dto.ClientApply;

public interface IApplyDAO {
    Apply makeProjectApply(Apply apply) throws Exception;
    ClientApply setlectApplyByApplyId(int applyId) throws Exception;
}
