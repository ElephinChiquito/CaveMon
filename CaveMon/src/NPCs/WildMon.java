package src.NPCs;

import stdlib.StdDraw;

//import static src.NPCs.Enemy.activeEnemyMon;

import java.util.ArrayList;
import java.util.Random;

import src.PJ.*;
import src.Pantallas.*;
import src.Mons.*;

public class WildMon extends Enemy{ // estaria bien poner que enemy sea hijo de NPC    

    /* private String name; Comentado por ser ahora hijo*/
    private ArrayList<Attack> attackArray;
    private Random rand = new Random();

    public WildMon(int x, int y, String[] dialog, String name, Map mapa, Mon[] monList, String pictures, int reward) {
        super(x, y, dialog, name, mapa, monList, pictures, reward);

        mapa.changeTile(x, y, 15);

        //Se podria hacer que si en esta ArrayList hay especificamente un ataque que hace counter que lo use, y si no que haga poll
        attackArray = new ArrayList<>();
        System.out.println(monList[0]);
        for(int i = 0; i < 2; i++){
            attackArray.add(monList[0].attackList().get(rand.nextInt(monList[0].attackList().size())));
        }

        
    }

    public WildMon(int x, int y, String[] dialog, String name, Map mapa, Mon[] monList, String pictures, int reward, Door doorAssociated) {
        super(x, y, dialog, name, mapa, monList, pictures, reward, doorAssociated);

        mapa.changeTile(x, y, 15);

        //Se podria hacer que si en esta ArrayList hay especificamente un ataque que hace counter que lo use, y si no que haga poll
        attackArray = new ArrayList<>();
        System.out.println(monList[0]);
        for(int i = 0; i < 2; i++){
            attackArray.add(monList[0].attackList().get(rand.nextInt(monList[0].attackList().size())));
        }

        
    }

    public void initializeDiscardMon(Battle battlemap){
        boolean discardingMon = true;
        Message.showInResponseAndWait(new String[]{"Tienes 3 Mons debes descartarte de uno", "Elige un Mon del cual descartarte."});
        battlemap.restartGraphics();

        draw();

        battlemap.escribirDiscardMon(super.getActiveEnemyMon());
        
        String[] monsToWrite = new String[Player.playerMonsList().size()+2];
        monsToWrite[0] = "Escoge el mon que quieres sustituir:";
        for(int i = 0; i < Player.playerMonsList().size(); i++){
            monsToWrite[i+1] = (i+1 + ".- " + Player.getMon(i).name());
        }
        monsToWrite[Player.playerMonsList().size()+1] = "4.- " + super.getActiveEnemyMon().name();
        Message.showInMenu(monsToWrite);

        StdDraw.show();

        while(discardingMon){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                boolean monDiscarded = false;
                
                switch (key) {
                    case '1': 
                        Player.playerMonsList().set(key-'1', super.getActiveEnemyMon());
                        monDiscarded = true;
                        break;
                    case '2':
                        Player.playerMonsList().set(key-'1', super.getActiveEnemyMon());
                        monDiscarded = true;
                        break;
                    case '3': 
                        Player.playerMonsList().set(key-'1', super.getActiveEnemyMon());
                        monDiscarded = true;
                        break;
                        
                    case '4': 
                        monDiscarded = true;
                        break;
                    default:
                        break;
                }

                if(monDiscarded){
                    discardingMon = false;
                    if(isThereDoor)
                        doorAssociated.setStateOfOpen(true);
                }
                
            }
        }   
    }

    
}
   