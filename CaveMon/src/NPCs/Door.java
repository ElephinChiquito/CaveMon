package src.NPCs;

import src.PlayAudio;
import src.Pantallas.Map;
import src.Pantallas.Message;

public class Door extends NPC{

    private boolean abierta;

    public Door(int x, int y, String[] dialog, String name, Map mapa, boolean abierta){
        super(x, y, dialog, name, mapa);
        this.abierta = abierta;
        if(!abierta)
            mapa.changeTile(x, y, 12);
        if(abierta)
            mapa.changeTile(x, y, 13);
        
    }


    @Override
    public void interact() {
        if(!abierta){
            String[] res = new String[dialog.length+1];
            for(int i = 0; i < dialog.length; i++){ res[i] = dialog[i]; }
            res[dialog.length] = "Presiona la 'e' para continuar.";
            Message.showInMenuAndWait(res); //(100, 30) - (165, 0)
        }
        else
            Message.showInMenuAndWait(new String[] {"Una puerta abierta", "Pulsa la e para continuar"});
    }

    public void setStateOfOpen(boolean x){ 
        abierta = x;
        int valor = 12;
        if(abierta){
            valor++;
            PlayAudio.playSound("Audio\\DoorOpened.wav", +5f);
        }
        super.mapa.changeTile(posX, posY, valor);

    }
}
