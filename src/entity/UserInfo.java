package entity;

public class UserInfo {

    private static String Id;
    private int coin;
    private double remained_lives;
    private static int highest_score;

    public UserInfo (String Id, int coin, double remained_lives, int highest_score) {
        this.Id = Id;
        this.coin = coin;
        this.remained_lives = remained_lives;
        this.highest_score = highest_score;
    }
    public static String getId() {
        return Id;
    }
    public int getcoin() {
        return coin;
    }
    public double getRemained_lives() {
        return remained_lives;
    }
    public static int getHighest_score() {
        return highest_score;
    }

    public void setcoin(int coin){this.coin = coin;}
    public void setRemained_lives(double lives){this.remained_lives = lives;}
    public void setHighest_score(int score){this.highest_score = score;}

}
