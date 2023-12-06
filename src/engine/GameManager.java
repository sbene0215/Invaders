package engine;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<String> userList;
    private SaveDataManager saveDataManager;
    private String currentUserName;

    public GameManager() {
        userList = new ArrayList<>();
        saveDataManager = new SaveDataManager();
    }

    public void memberInit() {
        saveDataManager.fileCreate();
        userList = saveDataManager.fileRead();
    }

    public void memberRec() {
        saveDataManager.fileWrite(userList);
    }

    public void addNewUser(String userName) {
        userList.add(userName + "=0");
        currentUserName = userName;
    }

    public void updateCoins(int collectedCoins) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).startsWith(currentUserName)) {
                userList.set(i, currentUserName + "=" + collectedCoins);
                break;
            }
        }
    }

    public int getCollectedCoins() {
        for (String userData : userList) {
            String[] parts = userData.split("=");
            if (parts.length == 2 && parts[0].equals(currentUserName)) {
                return Integer.parseInt(parts[1]);
            }
        }
        return 0;
    }
}
