public class Main {
    public static void main(String[] args) {
        //로그인 정보 저장
        Player player = new Player("exampleUser", 100);
        PlayerDataHandler.savePlayerData(player);

        //로그인 정보 불러오기
        Player loadedPlayer = PlayerDataHandler.loadPlayerData();
        System.out.println("Loaded Player: " + loadedPlayer.getUsername() + ", Coins: " + loadedPlayer.getCoins());
    }
}
