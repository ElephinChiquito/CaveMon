package src.NPCs;

import stdlib.StdDraw;
import tads.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import src.PJ.*;
import src.Pantallas.*;
import src.PlayAudio;
import src.Items.*;
import src.Mons.*;

import javax.sound.sampled.*;
import java.io.*;


public class Enemy extends NPC{  

    /* private String name; Comentado por ser ahora hijo*/
    private LinkedQueue<Mon> monList = new LinkedQueue<>();
    private String enemyFront;
    private boolean battling;
    private ArrayList<Attack> attackArray;
    private Random rand = new Random();
    private int reward;
    private boolean defeated;
    private Mon activeEnemyMon;
    protected Door doorAssociated;
    protected boolean isThereDoor = false;

    public Enemy(int x, int y, String[] dialog, String name, Map mapa, Mon[] monList, String pictures, int reward) {
        super(x, y, dialog, name, mapa);

        for(int i = 0; i < monList.length; i++){
            this.monList.add(monList[i]);
        }
        this.reward = reward;
        enemyFront = pictures;
        mapa.changeTile(x, y, 10);

        defeated = false;
        nextMon();
    }

    public Enemy(int x, int y, String[] dialog, String name, Map mapa, Mon[] monList, String pictures, int reward, Door doorAssociated) {
        super(x, y, dialog, name, mapa);

        for(int i = 0; i < monList.length; i++){
            this.monList.add(monList[i]);
        }
        this.reward = reward;
        enemyFront = pictures;
        mapa.changeTile(x, y, 10);

        defeated = false;
        nextMon();

        this.doorAssociated = doorAssociated;
        isThereDoor = true;
    }

    public LinkedQueue<Mon> getMons() { return monList; }
    public String enemyFront(){ return enemyFront; }
    public boolean battling(){ return battling; }
    public Mon getActiveEnemyMon() { return activeEnemyMon; }

    public static Clip playMusic(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // Lower volume.Use 0.0f for default volume.

            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop music
            clip.start();
            return clip;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void interact(){
        
        Lvl1.pauseMusic();
        if(defeated){
            Message.showInMenuAndWait(new String[] {"Eres muy bueno!", "Mucha suerte en tu aventura!!!", "Presiona la 'e' para continuar"});
            Map.resumeMusic();
        }
        else if(Player.allDead()){
            Message.showInMenuAndWait(new String[] {"Cura a tus Mons!!!!!"});
            Map.resumeMusic();
        }
        else{
            battling = true;
            
            String[] res = new String[dialog.length+1];
            for(int i = 0; i < dialog.length; i++){ res[i] = dialog[i]; }
            res[dialog.length] = "Presiona la 'e' para continuar.";
            Message.showInMenuAndWait(res); //(100, 30) - (165, 0)

            Battle battleMap = new Battle();
    
            for(int i = 0; i < 3; i++){
                battleMap.draw();
                battleMap.escribirTurnoInicial();
                StdDraw.show();
            }
            Message.showInMenu(new String[] {"El rival espera tu acción", "1.- Ver el menu de Ataques", "2.- Ver la lista de Objetos para usarlos", "3.- Cambiar tu Mon activo", "4.- Salir de la batalla al mapa"});
            
            draw();
            StdDraw.show();
    
            Clip battleMusic = null;
            if (!defeated && !Player.allDead()) {
                battleMusic = playMusic("Audio\\BattleMusic.wav"); 
            }

            while(battling && !defeated){
                if(StdDraw.hasNextKeyTyped()){
                    char key = StdDraw.nextKeyTyped();
                    boolean refrescar = false;
    
                    switch (key) {
                        case '1': //Ataque
                            initializeAtaque(battleMap);
                            refrescar = true;
                            break;
                        case '2':
                            initializeItems(battleMap);
                            refrescar = true;
                            break;
                        case '3':
                            initializeChangeMon(battleMap);
                            refrescar = true;
                            break;
                        case '4':
                            refrescar = true; 
                            battling = false;
                            break;
                    }
    
                    if(refrescar){
                        //Debemos cambiar la condicion para perde para que funcione con mas de un mon
                        //Se me ocurre hacer activeMon a Enemy. Y si active esta muerto, depende de si el resto de mons
                        //estan muertos acabar que es lo que hay, y si no cambiar al siguiente
                        if(allDead()){
                            //Ganar dinero
                            battleMap.restartGraphics();
                            draw();
                            battleMap.escribirTurnoInicial();
                            

                            Message.showInMenuAndWait(new String[] {"Has ganado la batalla", "Me has vencido", "Presiona la 'e' para continuar"});
                            battleMap.restartGraphics();
                            draw();
                            Message.showInMenuAndWait(new String[] {"Me has ganado", "¡Toma esta recomensa!", new Coins("monedas: ", reward).toString(), "Presiona la 'e' para continuar"});
                            Inventory.addItem(new Coins("Coins", reward));
                            battling = false;
                            defeated = true;
                        }
                        else if(!activeEnemyMon.isAlive()){
                            //Ganar dinero
                            battleMap.restartGraphics();
                            draw();
                            battleMap.escribirTurnoInicial();
                            nextMon();
                            
                            Message.showInResponseAndWait(new String[] {"El Mon del rival murio", name() + " cambio a " + activeEnemyMon});
                            
                            
                        }
                        if(Player.allDead()){
                            battleMap.restartGraphics();
                            draw();
                            battleMap.escribirTurnoInicial();

                            Message.showInMenuAndWait(new String[] {"Has perdido la batalla", "El rival te ha vencido", "Presiona la 'e' para continuar"});
                            battleMap.restartGraphics();
                            draw();
                            StdDraw.show();

                            Message.showInMenuAndWait(new String[] {"Te he ganado!!!", "Vuelve cuando seas más fuerte", "Presiona la 'e' para continuar"});
                            battling = false;
                        }
                        else if(!Player.getActiveMon().isAlive()){

                            PlayAudio.playSound("Audio\\Die.wav", 0f);
                            
                            while(!Player.getActiveMon().isAlive()){
                                battleMap.restartGraphics();
                                draw();
                                StdDraw.show();
                                Message.showInResponseAndWait(new String[] {"Tu mon activo ha muerto", "Debes cambiar a otro vivo"});
                                initializeChangeMon(battleMap);
                            }
            
                            
                        }
                        if(battling){
                            battleMap.restartGraphics();
                            draw();
                            battleMap.escribirTurnoInicial();
                            Message.showInMenu(new String[] {"El rival espera tu acción", "1.- Ver el menu de Ataques", "2.- Ver la lista de Objetos para usarlos", "3.- Lanzar MonBall", "4.- Salir de la batalla al mapa"});
            
                            StdDraw.show();
                        }
                        
                    }
                }
            }
            if(defeated == false)
                curarMons();
            else{
                if(isThereDoor)
                    doorAssociated.setStateOfOpen(true);
            }
            if (battleMusic != null) {
                battleMusic.stop();
                battleMusic.close();
                Lvl1.resumeMusic();
            }

            StdDraw.clear();
        }

    }



    public void initializeAtaque(Battle battlemap){
        battlemap.restartGraphics();

        draw();

        battlemap.escribirTurnoAtaque();
        String[] res = new String[Player.getActiveMon().attackList().size()+1];
        res[0] = "Ataques:";
        //Cambiar Player.mon.attackList().get(1) Por una manera de obtener el pokemon elegido no uno fijo y static
        for(int i = 0; i < Player.getActiveMon().attackList().size(); i++){
            res[i+1] = (i+1) + ".- " + Player.getActiveMon().attackList().get(i);
        }
        Message.showInMenu(res);

        StdDraw.show();

        boolean inAttack = true;

        while(inAttack){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                boolean attacked = false;
                
                switch (key) {
                    case '1':
                        if(Player.getActiveMon().attackList().size()>= 1){ 
                        activeEnemyMon.receiveAttack(Player.getActiveMon().attackList().get(0), Player.getActiveMon());
                        attacked = true;
                        }

                        break;
                    case '2': 
                        if(Player.getActiveMon().attackList().size()>= 2){
                            activeEnemyMon.receiveAttack(Player.getActiveMon().attackList().get(1), Player.getActiveMon());
                            attacked = true;
                        }
                        break;
                    case '3':
                        if(Player.getActiveMon().attackList().size()>= 3){
                            activeEnemyMon.receiveAttack(Player.getActiveMon().attackList().get(2), Player.getActiveMon());
                            attacked = true;
                        } 
                        break;
                    case '4': // Volver
                        inAttack = false;
                        break;
                }
                
                if(attacked){
                    //Recives ataque
                    Attack receivedAttack = chooseAttack();
                    Player.getActiveMon().receiveAttack(receivedAttack, activeEnemyMon);

                    Message.showInResponseAndWait(new String[] {activeEnemyMon.name() + " enemigo utlizo: ", receivedAttack.toString()});

                    inAttack = false;

                }
            }
        }   
    }

    /**
     * IA Que escoge del buffer de dos ataques aleatorios el mas conveniente, es decir el que haga x2 de daño
     * sino, coge el primero cargado. 
     * (La palabra IA hace referencia a Inteligencia de comportamiento de un enemigo dentro de un videojuego)
     * @return Attack
     */
    public Attack chooseAttack(){
        String need = Player.getActiveMon().whatCrits();
        boolean encontrado = false;
        Attack res = new Attack(null, -1, null);

        for(int i = 0; i < 2 && !encontrado; i++){
            if(activeEnemyMon.attackList().get(i).type().equals(need)){
                encontrado = true;
                res = attackArray.remove(i);
            }
        }

        attackArray.add(activeEnemyMon.attackList().get(rand.nextInt(activeEnemyMon.attackList().size())));

        if(encontrado)
            return res;
        else
            return attackArray.remove(0);

    }

    public void curarMons(){
        LinkedQueue<Mon> aux = new LinkedQueue<>();
        activeEnemyMon.heal(activeEnemyMon.maxHP());
        while(!monList.isEmpty()){
            aux.add(monList.peek());
            monList.peek().heal(monList.peek().maxHP());
            monList.poll();
        }

        monList = aux;
    }

    public boolean allDead(){
        return monList.isEmpty() && !activeEnemyMon.isAlive();
    }

    public void nextMon(){
        activeEnemyMon = monList.peek();
        monList.poll();

        attackArray = new ArrayList<>();
        for(int i = 0; i < 2; i++)
            attackArray.add(activeEnemyMon.attackList().get(rand.nextInt(activeEnemyMon.attackList().size())));
        
    }

    /** Dibujar todo lo relacionado a la batalla que sean imagenes y la vida */
    public void draw(){
        // Imagenes //!!!!!!CAMBIAR A ACTIVEMON.IMAGEFRONT y back
        StdDraw.picture(50, 50, "./Images/FondoBatalla.png", 98,98);
        StdDraw.picture(85, 68, enemyFront, 40, 40);
        StdDraw.picture(63, 55, activeEnemyMon.getImageFront(), 35, 35);
        getActiveEnemyMon().drawHealthBar(52, 72);
        Player.getActiveMon().drawHealthBar(23.5,43.5);
        Player.drawInBattle();

        drawEnemyMonHP();
        Player.drawMonHP();
        drawSpacingLines();

    }

    public void drawEnemyMonHP(){
        //Vida !!!!Cambiar 0 por manera de acceder al mon que sea
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(104, 58, "Vida del enemigo (" + activeEnemyMon.hp() + " / " + activeEnemyMon.maxHP() + ")");
        StdDraw.picture(Map.SCALE + 59, Map.SCALE - 42, getActiveEnemyMon().getTypeImage(), 4, 4);

        int pixelHP = activeEnemyMon.maxHP()/57;
        for(int i = 0; i < 57; i++){
            if(pixelHP*i < activeEnemyMon.hp())
                StdDraw.setPenColor(StdDraw.GREEN);
            else
                StdDraw.setPenColor(StdDraw.RED);
            
            StdDraw.filledSquare(104 + i + 0.5, 55 + 0.5, 0.5);
        }
    }

    public void drawSpacingLines(){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(Map.SCALE+Map.SCALEMENU/2, 60.5, Map.SCALEMENU/2, 0.15);
        StdDraw.filledRectangle(100+50000/(750*2), 30, 50000/(750*2), 0.15);
    }

    public void initializeItems(Battle battlemap){
        battlemap.restartGraphics();

        draw();

        battlemap.escribirTurnoItems();
        battlemap.sideMenuItemsDraw();
        
        Message.showInMenu(new String[] {"Los items te ayudan a luchar:","1.- Curar 10%. (No consume un turno)", "2.- Aumentar un 100% el daño del Mon un turno", "3.- Lanzar MonBall para capturar"});

        StdDraw.show();

        boolean inItems = true;

        while(inItems){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                boolean usedItem = false;
                String action = "error";
                
                switch (key) {
                    case '1': 
                        if(Inventory.getHPPotion().getAmount() > 0){
                            Inventory.getHPPotion().use();
                            usedItem = true;
                            action = "Y se curo " + Player.getActiveMon().maxHP()/10 + " de vida.";
                        }
                        break;
                    case '2': 
                        if(Inventory.getStrPotion().getAmount() > 0){
                            Inventory.getStrPotion().use();
                            usedItem = true;
                            action = "Tu pokemon se siente mas fuerte.";
                        }
                        break;
                    case '3': 
                        //Aqui hay problema de que usa showInMenu y no showInResponse
                        if(this instanceof WildMon){
                            WildMon aux = (WildMon) this;
                            if(Inventory.getMonBall().getAmount() > 0){
                                //Audio usar monball
                                PlayAudio.playSound("Audio\\ThrowMonBall.wav", 0f);

                                if(Player.rand.nextInt(activeEnemyMon.maxHP()) + 1 > activeEnemyMon.hp()*(Player.difficultyModifier)){ //hacer bien con random
                                    MonBall x = (MonBall) Inventory.getMonBall();
                                    action = "Has atrapado a " + activeEnemyMon.name() + "!";

                                    //AudioCapturado
                                    PlayAudio.playSound("Audio\\CatchedPokemon.wav", 0f);
                                    defeated = true;
                                    
                                    if(Player.playerMonsList().size() == 3){
                                        Inventory.getMonBall().subsAmount(1);
                                        aux.initializeDiscardMon(battlemap);

                                        //restart Graphics for new messeage
                                        battlemap.restartGraphics();
                                        draw();
                                        battlemap.escribirTurnoItems();
                                        battlemap.sideMenuItemsDraw();
                                        StdDraw.show();
                                    } else{
                                        x.useMonBall(activeEnemyMon);
                                    }
                                }
                                else{
                                    Inventory.removeItem(0, 1);
                                    action = "El Mon se ha escapado! Debilitalo primero!";
                                }
                                usedItem = true;
                            }else{
                                Message.showInResponseAndWait(new String[] {"No te quedan MonBalls!"});
                                inItems = false;
                                System.out.println("no te quedan monballs");
                            }
  
                        }
                        else{
                            Message.showInResponseAndWait(new String[] {"Solo se puede usar con Mons salvajes"});
                            inItems = false;
                            break;
                        }
                    case '4': 
                        inItems = false;
                        break;
                    default:
                        break;
                }
                
                if(usedItem){
                    
                    Message.showInResponseAndWait(new String[] {Player.getActiveMon().name() + " aliado utlizo: ",
                        "" + action});
                    inItems = false;
                }
            }
        }   
    }

    /** Cosas a tener en cuenta El mon activo si se cambia no deberia pasar nada, o que lo que haga
     * no cambie nada. Hay que marcar el mon activo como activo. Si size de playermonslist es menor, 
     * solo hacer size veces.
    */
    public void initializeChangeMon(Battle battlemap){

        battlemap.restartGraphics();
        draw();
        battlemap.escribirTurnoChangeMon();
 
        String[] res = new String[Player.playerMonsList().size() + 2];
        int contador = 1;
        res[0] = "Escoge un Mon (El resaltado es el activo)";
        for(int i = 0; i < Player.playerMonsList().size(); i++){
            res[i+1] = i+1 + ".- " + Player.getMon(i).name();
            contador++;
        }
        res[contador] = "4.- Volver";
        Message.showInMenu(res);

        if(Player.getActiveMonIndex()==0){
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.rectangle(117.5, 88, 13.5, 8);
        }
        else if(Player.getActiveMonIndex()==1){
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.rectangle(148.5, 88, 13.5, 8);
        }
        else if(Player.getActiveMonIndex()==2){
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.rectangle(117.5, 70, 13.5, 8);
        }

        StdDraw.show();

        boolean inChangeMon = true;
        LinkedList<Integer> availableActions = new LinkedList<>();//Hace falta linked list por ser tamaño variable
        for(int i = 0; i < contador; i++){
            availableActions.add(i);
        }

        while(inChangeMon){

            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                
                if(key == '4')
                    inChangeMon = false;
                else{

                    //Solo puede cambiar si esta relleno el hueco del pokemon en esa posicion
                    int i = (int) (key - '0');

                    if(availableActions.contains(i)){
                        Player.setActiveMon(i-1);
                        inChangeMon = false;
                        Message.showInResponseAndWait(new String[] {"Pokemon activo cambiado a:",
                        "" + Player.getActiveMon().name()});

                    } else{ 
                        Message.showInResponseAndWait(new String[] {"No esta disponible"});
                        inChangeMon = false;
                    }
                }
                
            }


        }   
    }

    
    @Override
    public String toString() {
        String res = "";
        /* for (int i = 0; i < monList.size(); i++) {
            res +=  monList.get(i) + "\n" ;
        } */
        return super.toString() + "Enemy{ Enemy Mons=\n" + res + "}";
    }

}
   