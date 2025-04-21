package service.admin;

import dto.AdminProject;
import dto.DashboardProject;

import java.util.List;

public interface IDashboardService {
    public List<AdminProject> getDashboardProjectList() throws Exception;
}
