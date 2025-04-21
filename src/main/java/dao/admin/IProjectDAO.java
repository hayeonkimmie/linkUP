package dao.admin;

import dto.AdminProject;
import dto.AdminProjectDetail;

import java.util.List;

public interface IProjectDAO {
    List<AdminProject> selectAllOngoingProject() throws Exception;
    AdminProjectDetail selectProjectDetail(int projectId) throws Exception;
    List<AdminProject> selectPagedProjects(int offset, int limit, String keyword, String settleStatus, String startDate, String endDate) throws Exception;
    int countAllProjects() throws  Exception;
}
