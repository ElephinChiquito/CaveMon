package src.NPCs;

import src.PlayAudio;
import src.Items.Items;
import src.PJ.Inventory;
import src.Pantallas.Map;
import src.Pantallas.Message;

public class Chest extends NPC{

    private Items[] items;
    private boolean looted;

    /**
     * Constructor for the Chest class.
     * @param x The x coordinate of the chest
     * @param y The y coordinate of the chest
     * @param dialog The dialog of the chest
     * @param name The name of the chest
     * @param mapa The map where the chest is located
     * @param items The items inside the chest
     * @param looted If the chest has been looted or not, false by deafult.
     */
    public Chest(int x, int y, String[] dialog, String name, Map mapa, Items[] items, boolean looted) {
        super(x, y, dialog, name, mapa);
        this.items = items;
        /* insertar en la matriz del mapa el NPC como val 5 */
        mapa.changeTile(x, y,5);
        looted = false;
    }

    /**
     * Method to get the items inside the chest.
     * @return The items inside the chest as an array of Items.
     */
    public Items[] getItems() {
        if(looted)
            return new Items[0];
        return items;
    }

    /**
     * Method to get the looted state of the chest.
     * @return The looted state of the chest.
     */
    public void looted(boolean state){
        this.looted = state;
    }

    @Override
    public void interact(){
        if(!looted){
            for(int i = 0; i <items.length && !looted; i++){
                Inventory.addItem(items[i]);
            }
            PlayAudio.playSound("Audio\\OpenChest.wav", -10f);
            looted = true;
            Message.showInMenuAndWait(new String[] {"Abriste un cofre! Obtuviste:", toString(), "Presiona la 'e' para continuar."});
        }
        else    
            Message.showInMenuAndWait(new String[] {"Ya abriste este cofre", "Sigue explorando!", "Presiona la 'e' para continuar."});
        
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < items.length-1; i++) {
            if(items[i].getAmount() > 0)
                res += items[i] + ", ";
        }
        if(items[items.length-1].getAmount() > 0)
            res += items[items.length-1];
        return res;
    }
}
