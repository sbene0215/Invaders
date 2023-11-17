import java.io.Serializable;

public class Player implements Serializable {
    private String username;
    private int coins;

    public Player(String username, int coins) {
        this.username = username;
        this.coins = coins;
    }

    // Getter 메서드들

    public String getUsername() {
        return username;
    }

    public int getCoins() {
        return coins;
    }

    // Setter 메서드들

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
