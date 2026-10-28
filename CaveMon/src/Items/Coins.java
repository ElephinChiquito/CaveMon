package src.Items;

import src.PJ.Inventory;

public class Coins extends Items {
    
    public Coins(String name, int amount){
        super(name, amount);
    }

    /**
     * MAKES NOTHING
     * @param player The player using the Monaball
     * @param invetory The inventory of the player
     */
    @Override
    public void use(){
        
        System.out.println("Used a Coin");
        Inventory.removeItem(3, 1);

    }
}