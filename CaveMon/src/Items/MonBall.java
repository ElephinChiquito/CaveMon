package src.Items;

import src.PJ.Inventory;
import src.PJ.Player;
import src.Mons.*;
import src.NPCs.Enemy;

public class MonBall extends Items {
    

    public MonBall(String name, int amount){
        super(name, amount);

    }

    /**
     * Method to use a Monball. We use another one different because we cant simply
     * reference Enemy as an static class, and neither can access the enemy atributes.
     * Therefore we must specify which Mon the Monball is being used on.
     * @param player The player using the Monaball
     * @param invetory The inventory of the player
     */

    public void useMonBall(Mon mon){
        
        System.out.println("Used a Monball");
        Player.addMon(mon);
        Inventory.removeItem(0, 1);
        //PlayAudio.playSound("Audio\\MonBall.wav"); Lo he puesto en Enemigo apra que aunque tengas 3 mons y tengas que cambiar, suene el sonido.

    }

    @Override
    public void use(){System.out.println("ERROOOOOOOOOR, Intentando usar monBall sin definir el Mon");}
    
}
