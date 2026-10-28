package src.TEST;

import stdlib.StdDraw;
import java.util.LinkedList;

import src.PJ.*;
import src.Pantallas.*;

import src.PlayAudio;

import src.NPCs.*;




public class Main {
    
    public static void main(String[] args) {
        Player pj = new Player(9, 9);

        Inventory inventory = new Inventory();
        Map mapa = new Lvl1();
        Player.inLvl1 = true;

        while(true){

            /* Se podria hacer otro while pero en true, para resetear para el siguiente nivel a partir de aqui, y que no haga lo de arriba de
                Pero que si dibuje, pero no haga mapa = lvl1 */
            for(int i = 0; i < 3; i++){
                mapa.draw();
                mapa.sideMenuDraw();
                pj.draw();
                StdDraw.show();
            }
            LinkedList<NPC> npcs = mapa.npcs;
    
            
            while(Player.inLvl1){
                
                if(StdDraw.hasNextKeyTyped()){
                    char key = StdDraw.nextKeyTyped();
    
                    switch (key) {
                        case 'w':
                            pj.mover(0, 1, mapa);
                            PlayAudio.playSound("Audio\\Step5.wav", 0f);
                            break;
                        case 'a':
                            pj.mover(-1, 0, mapa);
                            PlayAudio.playSound("Audio\\Step5.wav", 0f);
                            break;
                        case 's':
                            pj.mover(0, -1, mapa);
                            PlayAudio.playSound("Audio\\Step5.wav", 0f);
                            break;
                        case 'd':
                            pj.mover(1, 0, mapa);
                            PlayAudio.playSound("Audio\\Step5.wav", 0f);
                            break;
                        case 'e':
    
                            /* Esto comprueba cual de los npcs que haya en el mapa completo 
                             * es con el que se esta interactuando. Y interactua especificamente con ese.
                             */
                            int[] posNpc;
                            NPC npcInteres = null;
                            /* Cuatro coordenadas cardinales */
                            if(mapa.NPCSNUMBERS.contains(mapa.getVal(pj.posX()+3, pj.posY()))){
                                posNpc = new int[] {pj.posX()+3, pj.posY()};
                            }
                            else if(mapa.NPCSNUMBERS.contains(mapa.getVal(pj.posX()-3, pj.posY()))){
                                posNpc = new int[] {pj.posX()-3, pj.posY()};
                            }
                            else if(mapa.NPCSNUMBERS.contains(mapa.getVal(pj.posX(), pj.posY()+3))){
                                posNpc = new int[] {pj.posX(), pj.posY()+3};
                            }
                            else if(mapa.NPCSNUMBERS.contains(mapa.getVal(pj.posX(), pj.posY()-3))){
                                posNpc = new int[] {pj.posX(), pj.posY()-3};
                            }
                            else{
                                System.out.println("no hay NPC");
                                break;
                            }
    
                            for(int i = 0; i < npcs.size(); i++){
                                if(npcs.get(i).posX() == posNpc[0] && npcs.get(i).posY() == posNpc[1]){
                                    npcInteres = npcs.get(i);
                                    System.out.println("Npc interactuado");
                                }
                            }
    
                            //HACER UN NPC QUE CAMBIE AQUI MAPA DE LVL1 A LVL2 EL RESTO NO DEBERIA HABER QUE CAMBIARLO SI ESTA BIEN DEFINIDO LVL2
                            npcInteres.interact();
                            
                            break;
                        /*
                        case 'm': 
                            pj.invertCheckCollision();
                            System.out.println("collision = " + pj.checkCollision());
                            break;
    
                        case 'i':
                            System.out.println(inventory);
                            break;
                        case 'ñ':
                            Inventory.getCoins().addAmount(100);
                            break;
                        case 'l':
                            for(int i = 0; i < Player.playerMonsList().size(); i++){ Player.getMon(i).heal(Player.getMon(i).maxHP()-Player.getMon(i).hp()); }
                            break;*/
                        default:
                            break;
                    }
    
                    //for(int i = 0; i < 2; i++){
                        mapa.draw();
                        mapa.sideMenuDraw();
                        pj.draw();
                        StdDraw.show();
                    //}
    
                    //System.out.println("\n"); //Siguiente linea para informacion
                    /* System.out.println(mapa); */
                }
            }
    
            mapa = new Lvl1();
            Player.difficultyModifier += 0.25;
            Player.setPos(9,9);
            Player.inLvl1 = true;
            Player.addLvl();
        }
    }
}