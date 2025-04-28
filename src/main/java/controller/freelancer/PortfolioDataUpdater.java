package controller.freelancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortfolioDataUpdater {

    // 스킬, URL, 첨부파일 처리 (여러개)
    public static List<String> updateList(String originalStr, String removedStr, String addedStr) {
        List<String> list = new ArrayList<>();

        if (originalStr != null && !originalStr.trim().isEmpty()) {
            list = new ArrayList<>(Arrays.asList(originalStr.split("\\^")));
        }

        if (removedStr != null && !removedStr.trim().isEmpty()) {
            List<String> removedItems = Arrays.asList(removedStr.split("\\^"));
            list.removeIf(item -> removedItems.contains(item));
        }

        if (addedStr != null && !addedStr.trim().isEmpty()) {
            List<String> addedItems = Arrays.asList(addedStr.split("\\^"));
            for (String added : addedItems) {
                if (!list.contains(added)) {
                    list.add(added);
                }
            }
        }

        return list;
    }

    // 프로젝트 ID 처리 (항상 하나만)
    public static Integer updateSingleProjectId(String originalProjectIdStr, String removedStr, String addedStr) {
        List<String> list = new ArrayList<>();

        if (originalProjectIdStr != null && !originalProjectIdStr.trim().isEmpty()) {
            list.add(originalProjectIdStr.trim());
        }

        if (removedStr != null && !removedStr.trim().isEmpty()) {
            List<String> removedItems = Arrays.asList(removedStr.split("\\^"));
            list.removeIf(item -> removedItems.contains(item));
        }

        if (addedStr != null && !addedStr.trim().isEmpty()) {
            List<String> addedItems = Arrays.asList(addedStr.split("\\^"));
            for (String added : addedItems) {
                if (!list.contains(added)) {
                    list.add(added);
                }
            }
        }

        // 결과적으로 하나만 남아야 함
        if (!list.isEmpty()) {
            return Integer.parseInt(list.get(0));
        } else {
            return null;
        }
    }
}
