import java.io.*;

public class PlayerDataHandler {

    public static void savePlayerData(Player player) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("player_data.dat"))) {
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadPlayerData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("player_data.dat"))) {
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // 파일이 없거나 오류가 발생할 경우 빈 플레이어 생성
            return new Player("", 0);
        }
    }
}
