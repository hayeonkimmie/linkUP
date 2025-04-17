package dao.admin;

import dto.admin.AdminProject;
import dto.admin.AdminProjectDetail;

import java.util.List;

public interface IProjectDAO {
    List<AdminProject> selectAllOngoingProject() throws Exception;
    AdminProjectDetail selectProjectDetail(int projectId) throws Exception;
}
