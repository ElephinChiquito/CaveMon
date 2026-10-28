package src.Items;

import src.PlayAudio;
import src.PJ.*;

public class HealthPotion extends Items {
    

    public HealthPotion(String name, int amount){
        super(name, amount);
    }

    /**
     * Method to use the health potion.
     * @param invetory The inventory of the player
     */
    @Override
    public void use(){

        System.out.println("Using HEALTH POTION");
        Inventory.removeItem(1, 1);
        Player.getActiveMon().heal(Player.getActiveMon().maxHP()/10);
        PlayAudio.playSound("Audio\\HealthPotion.wav", -20.00f);
        
    }
}
