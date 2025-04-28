package service.freelancer;
import dto.Academic;
import dto.License;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class InfoSerializer {

    /**
     * 학력 리스트(List<Academic>)를 하나의 문자열로 직렬화합니다.
     * 각 Academic 객체는 필드 사이를 `^`로 구분하고,
     * 여러 객체는 `#`로 구분하여 하나의 문자열로 만듭니다.
     */
    public static String serializeAcademicList(List<Academic> academicList) {
        List<String> parts = new ArrayList<>();
        for (Academic ac : academicList) {
            String entry = String.join("^",
                    ac.getAcademicType(),                    // 학력 구분 (예: 고등학교, 대학교(2,3년), 대학교(4년) 등)
                    ac.getAcademicName(),                    // 학교명
                    ac.getAcademicMajor(),                   // 전공
                    ac.getEntranceDate(),                    // 입학일 (yyyy-MM), 만약 학력구분이 '대입자격검정고시'라면 합격 연도
                    ac.getGraduateDate(),                    // 졸업일 (yyyy-MM)
                    ac.getGraduateStatus()                    // 중퇴 여부 (재학중, 졸업, 중퇴 등)
            );
            parts.add(entry); // 하나의 학력 정보를 `^`로 묶어서 리스트에 추가
        }
        return String.join("#", parts); // 전체 학력 리스트는 `#`로 구분
    }

    /**
     * 직렬화된 학력 문자열을 다시 List<Academic> 객체 리스트로 역직렬화합니다.
     * 구분자: 학력 항목은 `#`, 각 항목의 필드는 `^`로 나뉘어 있습니다.
     */
    public static List<Academic> deserializeAcademicList(String input) {
        List<Academic> list = new ArrayList<>();
        if (input == null || input.isEmpty()) return list;

        String[] entries = input.split("#"); // 각 학력 항목 분리
        for (String entry : entries) {
            String[] fields = entry.split("\\^"); // 각 필드 분리
            if (fields.length == 6) { // 필드가 모두 존재할 경우만 처리
                Academic ac = new Academic();
                ac.setAcademicType(fields[0]);
                ac.setAcademicName(fields[1]);
                ac.setAcademicMajor(fields[2]);
                ac.setEntranceDate(fields[3]);
                ac.setGraduateDate(fields[4]);
                ac.setGraduateStatus(fields[5]);
                list.add(ac);
            }
        }
        return list;
    }

    /**
     * 자격증 리스트(List<License>)를 하나의 문자열로 직렬화합니다.
     * 각 License 객체는 `^`로 필드 구분, 여러 개는 `#`로 연결합니다.
     */
    public static String serializeLicenseList(List<License> licenseList) {
        List<String> parts = new ArrayList<>();
        for (License lc : licenseList) {
            String entry = String.join("^",
                    lc.getLicenseName(),    // 자격증명
                    lc.getLicenseAgency(),   // 발급기관
                    lc.getLicenseDate().toString(),    // 취득일 (yyyy-MM)
                    lc.getLicenseGrade()
            );
            parts.add(entry); // 각 자격증 문자열을 리스트에 추가
        }
        return String.join("#", parts); // 자격증 목록은 `#`로 연결
    }

    /**
     * 직렬화된 자격증 문자열을 다시 List<License>로 역직렬화합니다.
     * 구분자: 자격증 항목은 `#`, 필드는 `^`
     */
    public static List<License> deserializeLicenseList(String input) {
        List<License> list = new ArrayList<>();
        if (input == null || input.isEmpty()) return list;
        //자격증명^급/점수^발급기관^취득연월
        String[] entries = input.split("#"); // 각 자격증 항목 분리
        for (String entry : entries) {
            String[] fields = entry.split("\\^"); // 필드 분리
            if (fields.length == 4) { // 필드가 모두 있는 경우만 처리
                License lc = new License();
                lc.setLicenseName(fields[0]);
                lc.setLicenseGrade(fields[1]);
                lc.setLicenseAgency(fields[2]);
                lc.setLicenseDate(Date.valueOf(fields[3]+"-01"));
                list.add(lc);
            }
        }
        return list;
    }
}
