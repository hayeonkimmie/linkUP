package service.client;

import dao.client.ClientFavoritesDAOImpl;
import dao.client.IClientFavoritesDAO;
import dto.ClientFavorites;
import util.PageInfo;

import java.util.*;

public class ClientFavoritesServiceImpl implements IClientFavoritesService {
    private final IClientFavoritesDAO clientFavoritesDAO;

    public ClientFavoritesServiceImpl() {
        this.clientFavoritesDAO = new ClientFavoritesDAOImpl();
    }

    @Override
    public List<ClientFavorites> getClientFavorites(PageInfo pageInfo) throws Exception {
        int allCount = clientFavoritesDAO.selectClientFavoritesCount();
        int allPage = (int) Math.ceil((double) allCount / 9);
        int startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
        int endPage = Math.min(startPage + 9, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        int row = (pageInfo.getCurPage() - 1) * 9;
        return clientFavoritesDAO.selectJjimFree(row);
    }

    @Override
    public List<ClientFavorites> getClientFavoritesWithFilter(PageInfo pageInfo, String clientId, String sort, String keyword) throws Exception {

        final int pageSize = 9;
        int curPage = Math.max(1, pageInfo.getCurPage());
        int offset = (curPage - 1) * pageSize;

        Map<String, Object> param = new HashMap<>();
        param.put("clientId", clientId);
        param.put("sort", sort);
        param.put("keyword", keyword);
        param.put("offset", offset);
        param.put("limit", pageSize);

        int allCount = clientFavoritesDAO.selectFavoritesWithFilter(param);
        int allPage = (int) Math.ceil((double) allCount / pageSize);
        int startPage = (curPage - 1) / 10 * 10 + 1;
        int endPage = Math.min(startPage + 9, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);

        List<Map<String, Object>> rawList = clientFavoritesDAO.selectFavoritesCard(param);
        List<ClientFavorites> result = new ArrayList<>();

        for (Map<String, Object> map : rawList) {
            System.out.println(" \uD83D\uDD25 raw map: " + map); // 테스트 코드
            ClientFavorites view = new ClientFavorites();
            view.setFreelancerId((String) map.get("freelancer_id"));
            view.setProfileImage((String) map.get("profile_img"));
            view.setName((String) map.get("name"));
            view.setLocation((String) map.get("desired_location"));
            Object star = map.get("rating");
            view.setRating(star != null ? ((Number) star).doubleValue() : 0.0);

            Object countObj = map.get("project_count");
            view.setProjectCount(countObj != null ? ((Number) countObj).intValue() : 0);


            String skill = (String) map.get("skill");
            if (skill != null && !skill.trim().isEmpty()) {
                view.setTags(Arrays.asList(skill.split(",")));
                view.setJob(skill.split(",")[0]); // 수정필요할 듯 (skill다 표시해야함)
            } else {
                view.setTags(new ArrayList<>());
                view.setJob("정보 없음");
            }
            result.add(view);
        }

        return result;
    }



    @Override
    public String toggleFavorite(String clientId, String freelancerId) throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("clientId", clientId);
        param.put("freelancerId", freelancerId);

        List<ClientFavorites> list = clientFavoritesDAO.selectJjimFree(clientId);
        boolean already = list.stream().anyMatch(j -> j.getFreelancerId().equals(freelancerId));

        if(already) {
            clientFavoritesDAO.deleteFavorite(param);
            return "removed";
        } else{
            clientFavoritesDAO.insertFavorite(param);
            return "added";
        }
    }
}
