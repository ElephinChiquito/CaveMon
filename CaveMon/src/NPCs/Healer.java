package src.NPCs;

import src.PlayAudio;
import src.PJ.*;
import src.Pantallas.Map;
import src.Pantallas.Message;

public class Healer extends NPC{


    public Healer(int x, int y, String[] dialog, String name, Map mapa){
        super(x, y, dialog, name, mapa);

        /* insertar en la matriz del mapa el NPC como val 9 */
        mapa.changeTile(x, y,9);
    }

    public void interact(){
        
        String[] res = new String[dialog.length+1];
        for(int i = 0; i < dialog.length; i++){ res[i] = dialog[i]; }
        res[dialog.length] = "Presiona la 'e' para continuar.";
        Message.showInMenuAndWait(res); //(100, 30) - (165, 0)
        
        if(Inventory.getCoins().getAmount() >= 100){
            for(int i = 0; i < Player.playerMonsList().size(); i++){
                Player.getMon(i).heal(Player.getMon(i).maxHP()-Player.getMon(i).hp());
            }
            PlayAudio.playSound("Audio\\HealthPotion.wav", -20f);
            Inventory.getCoins().subsAmount(100);
        }
    }
}
