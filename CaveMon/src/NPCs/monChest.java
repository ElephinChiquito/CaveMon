package src.NPCs;

import src.PlayAudio;
import src.Pantallas.Battle;
import src.Pantallas.Map;
import src.Pantallas.Message;
import stdlib.StdDraw;
import src.Mons.Mon;
import src.PJ.Player;

public class MonChest extends NPC {


    private boolean looted;
    private Mon[] monArr;
    private Door associatedDoor;

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
    public MonChest(int x, int y, String[] dialog, String name, Map mapa, boolean looted, Mon[] monArr) {
        super(x, y, dialog, name, mapa);
        this.monArr = monArr;
        looted = false;
        mapa.changeTile(x, y,8);
    }
    public MonChest(int x, int y, String[] dialog, String name, Map mapa, boolean looted, Mon[] monArr, Door door) {
        super(x, y, dialog, name, mapa);
        this.monArr = monArr;
        looted = false;
        mapa.changeTile(x, y,8);
        associatedDoor = door;
    }

    /**
     * Method to get the looted state of the chest.
     * @return The looted state of the chest.
     */
    public void looted(boolean state){
        this.looted = state;
    }

     /**
     * Method to get the Mons inside the chest.
     * @return The mons inside the chest as an array of Mon.
     */
    public Mon[] getMons(){
        if(looted)
            return new Mon[0];
        return monArr;
    }

    @Override
    public void interact(){
        if(!looted){
            boolean decided = false;
            PlayAudio.playSound("Audio\\OpenChest.wav", -5f);
            Message.showInMenu(new String[] {"Has encontrado un cofre de Mons", toString(), "Presiona '1' para el primero", "Presiona '2' para el segundo"});
            while(!decided){

                if(StdDraw.hasNextKeyTyped()){

                    char key2 = StdDraw.nextKeyTyped();
                    switch (key2) {
                        case '1':

                            if(Player.playerMonsList().size() == 3){
                                Battle battleMap = new Battle();
    
                                initializeDiscardMonChest(battleMap, monArr[0]);
                                
                                //restart Graphics for new messeage
                                battleMap.restartGraphics();
                                StdDraw.show();
                            } else{
                                Player.addMon(monArr[0]);
                            }
                            
                            decided = true;
                            System.out.println("added mon 1");
                            break;
                        case '2':

                            if(Player.playerMonsList().size() == 3){
                                Battle battleMap = new Battle();
                                
                                initializeDiscardMonChest(battleMap, monArr[1]);


                                //restart Graphics for new messeage
                                battleMap.restartGraphics();
                                StdDraw.show();
                            } else{
                                Player.addMon(monArr[1]);
                            }
                            
                            decided = true;
                            System.out.println("added mon 2");
                            break;
                        default:
                            break;
                    }
                }

            }
            looted = true;
            associatedDoor.setStateOfOpen(true);
        }
        else
            Message.showInMenuAndWait(new String[] {"Ya abriste este cofre", "Sigue explorando!", "Presiona la 'e' para continuar."});
        
    }
    
    
    @Override
    public String toString() {
        
        return monArr[0].getName() +" (1), "  + monArr[1].getName() + " (2)";

    }

    /** Dibujar todo lo relacionado a la batalla que sean imagenes y la vida */
    public void draw(Mon mon){
        StdDraw.picture(50, 50, "./Images/FondoBatalla.png", 98,98);
        StdDraw.picture(63, 55, mon.getImageFront(), 35, 35);
        Player.drawInBattle();

        drawEnemyMonHP(mon);
        Player.drawMonHP();
    }

    public void drawEnemyMonHP(Mon mon){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(104, 58, "Vida del enemigo (" + mon.hp() + " / " + mon.maxHP() + ")");
        StdDraw.picture(Map.SCALE + 59, Map.SCALE - 42, mon.getTypeImage(), 4, 4);

        int pixelHP = mon.maxHP()/57;
        for(int i = 0; i < 57; i++){
            if(pixelHP*i < mon.hp())
                StdDraw.setPenColor(StdDraw.GREEN);
            else
                StdDraw.setPenColor(StdDraw.RED);
            
            StdDraw.filledSquare(104 + i + 0.5, 55 + 0.5, 0.5);
        }
    }

    //DiscardMon para MonChests
    public void initializeDiscardMonChest(Battle battlemap, Mon availableMon){
        boolean discardingMon = true;
        battlemap.restartGraphics();

        draw(availableMon);

        battlemap.escribirDiscardMon(availableMon);
        
        String[] monsToWrite = new String[Player.playerMonsList().size()+2];
        monsToWrite[0] = "Escoge el mon que quieres sustituir:";
        for(int i = 0; i < Player.playerMonsList().size(); i++){
            monsToWrite[i+1] = (i+1 + ".- " + Player.getMon(i).name());
        }
        monsToWrite[Player.playerMonsList().size()+1] = "4.- " + availableMon.name();
        Message.showInMenu(monsToWrite);

        StdDraw.show();

        while(discardingMon){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                
                switch (key) {
                    case '1': 
                        Player.playerMonsList().set(key-'1', availableMon);
                        discardingMon = false;
                        break;
                    case '2':
                        Player.playerMonsList().set(key-'1', availableMon);
                        discardingMon = false;
                        break;
                    case '3': 
                        Player.playerMonsList().set(key-'1', availableMon);
                        discardingMon = false;
                        break; 
                    case '4': 
                        discardingMon = false;
                        break;
                    default:
                        break;
                }
            }
        }   
    }
}

    