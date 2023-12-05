package engine;

import entity.UserInfo;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveDataManager {
    private static final String ID_DATA_FILE = "res/user_names.txt";
    private static final String USER_DATA_FILE = "res/user_data.txt";
    private static final String COIN_DATA_FILE = "res/coin_data.txt";
    private static final Logger LOGGER = Logger.getLogger(SaveDataManager.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("log.txt");
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUserNameToFile(String userName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_DATA_FILE, true))) {
            writer.write(userName);
            writer.newLine();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while saving the user name.", e);
            String errorMessage = "Error occurred while saving the user name. Please try again.";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void SaveInitialUserInfo(String Id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(Id + " " + 0 + " " + 3.0 + " " + 0);
            writer.newLine();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while saving the user name.", e);
            String errorMessage = "Error occurred while saving the user name. Please try again.";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void SaveUserInfo(UserInfo userInfo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(userInfo.getId() + " " + userInfo.getcoin() + " " + userInfo.getRemained_lives() + " " + userInfo.getHighest_score());
            writer.newLine();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while saving the user name.", e);
            String errorMessage = "Error occurred while saving the user name. Please try again.";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void saveGameCoinData(int collectedCoins) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COIN_DATA_FILE))) {
            writer.write(String.valueOf(collectedCoins));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while saving the game coin data.", e);
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

            File coinFile = new File(COIN_DATA_FILE);
            if (coinFile.createNewFile()) {
                System.out.println("Coin file created: " + coinFile.getName());
            } else {
                System.out.println("Coin file already exists.");
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
    public static void deleteLinesByKeyword(String filePath, String keyword) {
        File inputFile = new File(filePath);
        File tempFile = new File(filePath + "_temp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(keyword)) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the modified file
        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            System.out.println("Lines containing the keyword deleted.");
        } else {
            System.err.println("Failed to update the file.");
        }
    }




}
