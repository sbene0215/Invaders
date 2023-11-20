package entity;

public class UserInfo {

    private String Id;
    private int coin;
    private double remained_lives;
    private int highest_score;

    public UserInfo (String Id, int coin, double remained_lives, int highest_score) {
        this.Id = Id;
        this.coin = coin;
        this.remained_lives = remained_lives;
        this.highest_score = highest_score;
    }
    public String getId() {
        return Id;
    }
    public int getcoin() {
        return coin;
    }
    public double getRemained_lives() {
        return remained_lives;
    }
    public int getHighest_score() {
        return highest_score;
    }
}
