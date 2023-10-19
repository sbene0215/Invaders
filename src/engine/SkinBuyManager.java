package engine;

import java.util.HashMap;
import java.util.Map;

import entity.Coin;


public class SkinBuyManager {

    private static SkinBuyManager instance;

    /* map to store skin ownership*/
    private Map<String, Boolean> ownedSkins;
    /* map to store skin wearing status */
    private Map<String, Boolean> equippedSkins;

    private SkinBuyManager() {
        ownedSkins = new HashMap<>();
        equippedSkins = new HashMap<>();
    }
    
    /**
     * Returns the singleton instance of the SkinBuyManager class
     * access the unique instance of the SkinBuyManager class
     *
     * @return The singleton instance of the SkinBuyManager class
     */
    public static SkinBuyManager getInstance() {
        if (instance == null) {
            instance = new SkinBuyManager();
        }
        return instance;
    }
    /**
     * Returns the boolean of skin price payment
     *
     * @return the boolean of skin price payment
     */
    public boolean isPossible(int skinPrice) {
        Coin coinInstance = new Coin(0, 0);
        int coinCurrent = coinInstance.getCoin();
        return coinCurrent >= skinPrice;
    }

    /**
     * Purchase a skin if it is possible based on the provided skin price.
     *
     * @param skinName   The name of the skin to purchase.
     * @param skinPrice  The price of the skin.
     */
    public void purchaseSkin(String skinName, int skinPrice) {
        Coin coinInstance = new Coin(0, 0);
        if (isPossible(skinPrice)) {
            if (!(isSkinOwned(skinName))){
                coinInstance.minusCoin(skinPrice);
            }
        }
        ownedSkins.put(skinName, true);  
    }

    /**
     * check for the skin ownership
     * 
     * @return True if the player owns the skin, or false if the skin is not owned
     */
    public boolean isSkinOwned(String skinName) {
        return ownedSkins.getOrDefault(skinName, false);
    }

    /**
     * check for the skin wearing
     * 
     * @return True if the player wear the skin, or false if the skin isn't worn
     */
    public boolean isSkinEquipped(String skinName) {
        return equippedSkins.getOrDefault(skinName, false);
    }

    /**
     * Equips the skin with the given name. This method marks the skin as equipped.
     *
     * @param skinName The name of the skin to equip.
     */
    public void equipSkin(String skinName) {
        equippedSkins.put(skinName, true);
    }

    /**
     * Unequips the skin with the given name. This method marks the skin as unequipped.
     *
     * @param skinName The name of the skin to unequip.
     */
    public void unequipSkin(String skinName) {
        equippedSkins.put(skinName, false);
    }

}
