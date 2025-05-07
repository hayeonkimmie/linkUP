package service.freelancer;

import dto.Academic;
import dto.License;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class InfoSerializer {

    // Null-safe 헬퍼 메서드
    private static String safe(String value) {
        return value == null ? "" : value;
    }

    /**
     * 학력 리스트(List<Academic>)를 하나의 문자열로 직렬화합니다.
     */
    public static String serializeAcademicList(List<Academic> academicList) {
        List<String> parts = new ArrayList<>();
        for (Academic ac : academicList) {
            String entry = String.join("^",
                    safe(ac.getAcademicType()),
                    safe(ac.getAcademicName()),
                    safe(ac.getAcademicMajor()),
                    safe(ac.getEntranceDate()),
                    safe(ac.getGraduateDate()),
                    safe(ac.getGraduateStatus())
            );
            parts.add(entry);
        }
        return String.join("#", parts);
    }

    /**
     * 직렬화된 학력 문자열을 다시 List<Academic> 객체 리스트로 역직렬화합니다.
     */
    public static List<Academic> deserializeAcademicList(String input) {
        List<Academic> list = new ArrayList<>();
        if (input == null || input.isEmpty()) return list;

        String[] entries = input.split("#");
        for (String entry : entries) {
            String[] fields = entry.split("\\^");
            if (fields.length == 6) {
                Academic ac = new Academic();
                ac.setAcademicType(safe(fields[0]));
                ac.setAcademicName(safe(fields[1]));
                ac.setAcademicMajor(safe(fields[2]));
                ac.setEntranceDate(safe(fields[3]));
                ac.setGraduateDate(safe(fields[4]));
                ac.setGraduateStatus(safe(fields[5]));
                list.add(ac);
            }
        }
        return list;
    }

    /**
     * 자격증 리스트(List<License>)를 하나의 문자열로 직렬화합니다.
     */
    public static String serializeLicenseList(List<License> licenseList) {
        List<String> parts = new ArrayList<>();
        for (License lc : licenseList) {
            String entry = String.join("^",
                    safe(lc.getLicenseName()),
                    safe(lc.getLicenseGrade()),
                    safe(lc.getLicenseAgency()),
                    lc.getLicenseDate() != null ? lc.getLicenseDate().toString()+"-01" : ""
            );
            parts.add(entry);
        }
        return String.join("#", parts);
    }

    /**
     * 직렬화된 자격증 문자열을 다시 List<License>로 역직렬화합니다.
     */
    public static List<License> deserializeLicenseList(String input) {
        List<License> list = new ArrayList<>();
        if (input == null || input.isEmpty()) return list;

        String[] entries = input.split("#");
        for (String entry : entries) {
            String[] fields = entry.split("\\^");
            if (fields.length == 4) {
                License lc = new License();
                lc.setLicenseName(safe(fields[0]));
                lc.setLicenseGrade(safe(fields[1]));
                lc.setLicenseAgency(safe(fields[2]));
                try {
                    lc.setLicenseDate(fields[3].isEmpty() ? null : Date.valueOf(fields[3]));
                } catch (IllegalArgumentException e) {
                    lc.setLicenseDate(null); // 포맷이 잘못된 경우 null 처리
                }
                list.add(lc);
            }
        }
        return list;
    }
}