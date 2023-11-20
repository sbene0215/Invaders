package screen;

import engine.SaveDataManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UserScreen {
    private static final String ID_DATA_FILE = "res/user_names.txt";
    private static String userName;

    public static String promptUserName() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Enter the name of user");
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            Font gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/BlackOpsOne-Regular.ttf")).deriveFont(24f);
            titleLabel.setFont(gameFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        }
        panel.add(titleLabel);

        JTextField textField = new JTextField(10);
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        textField.setCaretColor(Color.GREEN);
        textField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        textField.setFont(titleLabel.getFont());
        panel.add(textField);

        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.GREEN);
        UIManager.put("Button.font", titleLabel.getFont());
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter the name of user",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            //return textField.getText();
            String inputUserName = textField.getText();
            if (isVariableInFile(ID_DATA_FILE, inputUserName)) {
                System.out.println("Logged in.");
            }
            else {
                SaveDataManager.saveUserNameToFile(inputUserName);
                SaveDataManager.SaveInitialUserInfo(inputUserName);
            }
            userName = inputUserName;
            return userName;
        } else {
            return null;
        }
    }

    public static String getUserName() {
        return userName;
    }

    public static boolean isVariableInFile(String filePath, String variableToCheck) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 변수를 찾을 수 있는 로직을 작성
                if (line.contains(variableToCheck)) {
                    return true; // 변수를 찾았을 경우 true 반환
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 예외 처리: 파일 읽기 오류
        }

        return false; // 파일을 다 읽었거나 예외가 발생했을 경우 false 반환
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

