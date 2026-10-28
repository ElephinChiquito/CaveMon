package src.NPCs;

import src.PlayAudio;
import src.Pantallas.Map;
import src.PJ.*;

public class Trampilla extends NPC{

    private boolean abierta;

    public Trampilla(int x, int y, String[] dialog, String name, Map mapa){
        super(x, y, dialog, name, mapa);
        mapa.changeTile(x, y, 16);
    }


    @Override
    public void interact() {
        Player.inLvl1 = false;
        Map.pauseMusic();
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
