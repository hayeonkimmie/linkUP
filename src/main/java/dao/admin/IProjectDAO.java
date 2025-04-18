package dao.admin;

import dto.AdminProject;
import dto.AdminProjectDetail;

import java.util.List;

public interface IProjectDAO {
    List<AdminProject> selectAllOngoingProject() throws Exception;
    AdminProjectDetail selectProjectDetail(int projectId) throws Exception;
}
