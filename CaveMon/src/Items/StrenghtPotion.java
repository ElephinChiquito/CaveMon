package src.Items;

import src.PlayAudio;
import src.PJ.Inventory;
import src.PJ.Player;

public class StrenghtPotion extends Items {
    

    public StrenghtPotion(String name, int amount){
        super(name, amount);
    }

    /**
     * Method to use the strength potion.
     * @param player The player using the potion
     * @param invetory The inventory of the player
     */
    @Override
    public void use(){

        System.out.println("Using Strngth POTION");
        Inventory.removeItem(2, 1);
        Player.getActiveMon().setStrPotion(true);
        PlayAudio.playSound("Audio\\StrengthPotion.wav", -20.00f);
    }
}
