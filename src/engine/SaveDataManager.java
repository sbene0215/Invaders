package engine;

import java.io.*;
import java.util.ArrayList;

public class SaveDataManager {
    private static final String USER_DATA_FILE = "user_names.txt";

    public static void saveUserNameToFile(String userName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(userName);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileCreate() {
        try {
            File file = new File(USER_DATA_FILE);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> fileRead() {
        ArrayList<String> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void fileWrite(ArrayList<String> userList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (String userData : userList) {
                writer.write(userData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
