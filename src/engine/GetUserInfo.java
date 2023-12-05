package engine;

import entity.UserInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class GetUserInfo {

//    public static void main(String[] args) {
//        String filePath = "res/user_data.txt";
//        String keyword = "sss";

//        UserInfo userInfo = readUserInfo(filePath, keyword);

//        if (userInfo != null) {
//            System.out.println("UserInfo: " + userInfo.getRemained_lives());
//        } else {
//            System.out.println("Keyword '" + keyword + "' not found in the file.");
//        }
//    }
    public static UserInfo readUserInfo(String filePath, String Id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(Id)) {
                    // 키워드를 포함하는 줄을 찾았을 경우 정보를 추출하여 UserInfo 객체 생성
                    String[] parts = line.split("\\s+"); // 공백을 기준으로 문자열을 분리
                    if (parts.length == 4) {
                        String id = parts[0];
                        int coin = Integer.parseInt(parts[1]);
                        double remainedLives = Double.parseDouble(parts[2]);
                        int highestScore = Integer.parseInt(parts[3]);

                        return new UserInfo(id, coin, remainedLives, highestScore);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // 예외 처리: 파일 읽기 오류 또는 변환 오류
        }

        return null; // 파일을 다 읽었거나 예외가 발생했을 경우 null 반환
    }
}