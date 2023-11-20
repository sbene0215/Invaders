package screen;

import engine.SaveDataManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class UserScreen {
    private static final String ID_DATA_FILE = "res/user_names.txt";
    private static String userName;

    public static String promptUserName() {
        if (userName != null && !userName.isEmpty()) {
            return userName;
        }
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
                if (line.contains(variableToCheck)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

