package src.NPCs;

import src.Pantallas.Map;
import src.Pantallas.Message;

public abstract class NPC{

    protected int posX;
    protected int posY;
    protected String[] dialog;
    protected String name;

    protected Map mapa;

    public NPC(int x, int y, String[] dialog, String name, Map mapa){
        posX = x;
        posY = y;

        /* insertar en la matriz del mapa el NPC como val 4 */
        if(!(this instanceof MonChest))
            mapa.changeTile(x, y,4);

        this.mapa = mapa;
        this.name = name;
        this.dialog = dialog;
    }

    public abstract void interact();

    public int posX(){
        return posX;
    }
    public int posY(){
        return posY;
    }
    public String[] dialog(){
        return dialog;
    }
    public String name(){
        return name;
    }
    @Override
    public String toString(){
        return "x = " + posX + " y = " + posY + " dialog = " + dialog + " name = " + name;
    }

}