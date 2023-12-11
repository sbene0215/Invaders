package engine;

import entity.UserInfo;
import org.junit.jupiter.api.Test;

import static engine.GetUserInfo.readUserInfo;
import static org.junit.jupiter.api.Assertions.*;

class GetUserInfoTest {
    private static final String USER_DATA_FILE = "res/user_data.txt";

    @Test
    void readUserInfo_test() {
        UserInfo userInfo = readUserInfo(USER_DATA_FILE, "sss");
        UserInfo testuserInfo = new UserInfo("sss", 104, 0.0, 2164);
        int userInfo_coin = userInfo.getcoin();
        int testuserInfo_coin = testuserInfo.getcoin();
        assertEquals(userInfo_coin,testuserInfo_coin); // coin test
        double userInfo_lives = userInfo.getRemained_lives();
        double testuserInfo_lives = testuserInfo.getRemained_lives();
        assertEquals(userInfo_lives,testuserInfo_lives); // lives test
    }
}