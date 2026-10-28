package src.PJ;

import src.PlayAudio;
import src.Items.*;
import java.util.LinkedList;

public class Inventory {
    
    private static LinkedList<Items> items;

    /**
     * Constructor of the inventory
     */
    public Inventory(){

        items = new LinkedList<>();
        //Set inital "slots" for the inventory
        items.add(0, new MonBall("MonBall", 0));
        items.add(1, new HealthPotion("Health Potion", 0));
        items.add(2, new StrenghtPotion("Strength Potion", 0));
        items.add(3, new Coins("Coins", 0));
    }

    /**
     * Returns the items lnkedList
     * @return
     */
    public static LinkedList<Items> getInventory(){
        return items;
    }

    public static Items getHPPotion(){
        return items.get(1);
    }

    public static Items getStrPotion(){
        return items.get(2);
    }

    public static Items getMonBall(){
        return items.get(0);
    }
    public static Items getCoins(){
        return items.get(3);
    }

    /**
     * Removes items from player's inventory
     * 
     * @param i Number/type of item: 0 for Monballs, 1 for Health Potions, 2 for Strength Potions
     * @param cant Amount of items u want to remove
     */
    public static void removeItem(int i, int cant){
        if(items.get(i).getAmount() - cant >= 0)
            items.get(i).subsAmount(cant);
            if(items.get(i) instanceof Coins)
                PlayAudio.playSound("Audio\\BuyItem.wav", -10f);
        else
            System.out.println("Not enough items to remove");
    }

    
    /**
     *  To add items from a CHEST (or any other array of items)
     * @param item that u want to add
     */    
    public static void addItem(Items item){

        if(item instanceof MonBall)
            items.get(0).addAmount(item.getAmount());
        if(item instanceof HealthPotion)
            items.get(1).addAmount(item.getAmount());
        if(item instanceof StrenghtPotion)
            items.get(2).addAmount(item.getAmount());
        if(item instanceof Coins)
            items.get(3).addAmount(item.getAmount());
    }

    public String toString(){
        String res = "\n";
        for(int i = 0; i<items.size(); i++){
            res+=  (i+1) + ". " + items.get(i) + "\n";
        }
        return "Inventory: " + res;
    }

}
