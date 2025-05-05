package service.common;

import dto.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class KakaoLoginService implements IKakaoLoginService {
    public User kakaoLogin(String code) throws Exception {
        String token = getKakaoToken(code);
        System.out.println(token);
        User user = getKakaoUserInfo(token);
        System.out.println(user);
        return user;
    }
    String getKakaoToken(String code) throws Exception{
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        URL url = new URL(tokenUrl);
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);
        StringBuilder param = new StringBuilder();
        param.append("grant_type=authorization_code");
        param.append("&client_id=406379efec436984eb3393e7024851df");
        param.append("&redirect_uri=http://localhost:8080/linkup/kakao");
        param.append("&code="+code);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(param.toString());
        bw.flush();
        //응답
        BufferedReader br;
        int resCode = conn.getResponseCode();
        if(resCode >=200 && resCode <= 300) { //정상
            System.out.println("토큰 가져오기 성공");
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else { //오류
            System.out.println("토큰 가져오기 실패");
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder resBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine())!=null) {
            resBuilder.append(line);
        }
        br.close();
        conn.disconnect();
        System.out.println(resBuilder.toString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject)parser.parse(resBuilder.toString());
        return (String)jsonObj.get("access_token");
    }
    User getKakaoUserInfo(String token) throws Exception{
        String userUrl ="https://kapi.kakao.com/v2/user/me";
        URL url = new URL(userUrl);
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setRequestProperty("Authorization", "Bearer "+token);
        BufferedReader br;
        int resCode = conn.getResponseCode();
        if(resCode >=200 && resCode <= 300) { //정상
            System.out.println("계정 정보 가져오기 성공");
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else { //오류
            System.out.println("계정 정보 가져오기 실패");
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder resBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine())!=null) {
            resBuilder.append(line);
        }
        br.close();
        conn.disconnect();
        System.out.println(resBuilder.toString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject)parser.parse(resBuilder.toString());
        System.out.println(jsonObj.toJSONString());
        Long id = (Long)jsonObj.get("id");
        JSONObject kakao_account = (JSONObject)jsonObj.get("kakao_account");
        JSONObject properties = (JSONObject)jsonObj.get("properties");
        String nickname = (String)properties.get("nickname");
        String profileImg = (String)properties.get("profile_image");
        String email = (String)kakao_account.get("email");

        User user = new User();
        user.setUserId(id+"");
        user.setName("");
        user.setPassword("");
        user.setNickname(nickname);
        user.setProfileImg(profileImg);
        user.setEmail(email);
        user.setPhoneNum("");
        user.setSnsType("Kakao");
        System.out.println(user);
        return user;
    }
}
