package src.NPCs;

import src.PlayAudio;
import src.PJ.*;
import src.Pantallas.Map;
import src.Pantallas.Message;

public class Professor extends NPC{


    public Professor(int x, int y, String[] dialog, String name, Map mapa){
        super(x, y, dialog, name, mapa);

        /* insertar en la matriz del mapa el NPC como val 9 */
        mapa.changeTile(x, y,4);
    }

    public void interact(){
        
        String[] res = new String[dialog.length+1];
        for(int i = 0; i < dialog.length; i++){ res[i] = dialog[i]; }
        res[dialog.length] = "Presiona la 'e' para continuar.";
        Message.showInMenuAndWait(res); //(100, 30) - (165, 0)

    }
}
