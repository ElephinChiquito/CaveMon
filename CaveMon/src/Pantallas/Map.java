package src.Pantallas;

import src.PJ.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import src.PJ.Player;
import src.NPCs.*;

import stdlib.*;

public abstract class Map {

    public static final int CANVAS = 750; 
    public static final int SCALE = 100;
    public static final int CANVASMENU = 500;
    public static final int SCALEMENU = CANVASMENU*SCALE/CANVAS; // regla de 3
    //para que sea automatico, no hay que hacer calculos distintos para la pos x y la pos y.
    //Lo que se hace es añadir y calcular de manera distinta el mapa y el CANVASMENU. Para usar el CANVASMENU 
    //hara falta para hacer el CANVASMENU usar SCALEMENU. 

    private static Clip bgMusicClip;
    private static long pausedPosition = 0;

    private int[][] map = new int[SCALE][SCALE+SCALEMENU]; // Array matrix Y X, counterintuitive, adress as [y][x]
     
    //Defino que numeros pertenecen a los NPCs. Esto se accede cuando se aprieta la 'e' para poder comprobar si es NPC
    //Se añade para no tener que al comprobar si es NPC no se tenga que comprobar n veces. n = numero de distintos hijos de npcs hay
    public final ArrayList<Integer> NPCSNUMBERS = new ArrayList<>(Arrays.asList(4, 5, 8, 9, 10, 11, 12, 13, 15, 16)); 
    public LinkedList<NPC> npcs;
    
    public Map(){

        StdDraw.setCanvasSize(CANVAS+CANVASMENU, CANVAS);
        StdDraw.setXscale(0, SCALE+SCALEMENU);  // Map width = 100 cells
        StdDraw.setYscale(0, SCALE);  // Map height = 100 cells
        StdDraw.enableDoubleBuffering(); // For smoother rendering

        initializeBackground(); // 0 For value of BLACK background
    }

    public static Clip playMusic(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            bgMusicClip = AudioSystem.getClip();
            bgMusicClip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) bgMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            bgMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgMusicClip.start();
            return bgMusicClip;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void getNPCs(LinkedList<NPC> npcs){
        this.npcs = npcs;
    }

    public static void pauseMusic() {
        if (bgMusicClip != null && bgMusicClip.isRunning()) {
            pausedPosition = bgMusicClip.getMicrosecondPosition();
            bgMusicClip.stop();
        }
    }

    public static void resumeMusic() {
        if (bgMusicClip != null && !bgMusicClip.isRunning()) {
            bgMusicClip.setMicrosecondPosition(pausedPosition);
            bgMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgMusicClip.start();
        }
    }
    
    /** 
     * @param x
     * @param y
     * getVal must be adressed as [x][y]
     * @return int
     */
    public int getVal(int x, int y){ return map[y][x]; }

    /**@param x
     * @param y
     * @param valor
     * 
     * changeTile must be adressed as [x][y]
     */
    public void changeTile(int x, int y, int valor){ map[y][x] = valor; }

    private void initializeBackground(){
        /* Definir mapa completo en blanco */
        //Used for default cases

        for(int j = 0; j < SCALE; j++){ //[y]
            for(int i = 0; i < SCALE + SCALEMENU; i++){ //[x]
                if(i%3 == 0 && j%3 ==0)
                    changeTile(i, j, 0); // change(x,y,0)   0: White Background
                else
                    changeTile(i, j, 14);
            }
        }
    }


    /**
     * Again must be addressed as [x][y]
     * @param x
     * @param y
     */
    public void addMuro(int x, int y){
        changeTile(x, y, 1); // Walls are 1.
    }
    

    /**
     * This method is used to create the side menu
     * 
     */
    public void initializeSideMenu(){        
        addRectangulo(SCALE+4, SCALE-20, SCALE+SCALEMENU-4, SCALE-4, 6);
        addRectangulo(SCALE+7, SCALE-6, SCALE+20, SCALE-18, 7);
        addRectangulo(SCALE+26, SCALE-6, SCALE+39, SCALE-18, 7);
        addRectangulo(SCALE+45, SCALE-6, SCALE+58, SCALE-18, 7);

        addRectangulo(SCALE + 3, 4, SCALE+SCALEMENU-4, 27, 6);
        addRectangulo(SCALE + 4, 5, SCALE+SCALEMENU-5, 26, 7);

        addRectangulo(SCALE+4, 33, SCALE+SCALEMENU-4, 47, 6);
        addRectangulo(SCALE+7, 35, SCALE+20, 45, 7);
        addRectangulo(SCALE+26, 35, SCALE+39, 45, 7);
        addRectangulo(SCALE+45, 35, SCALE+58, 45, 7);
        
    }
    public void initializeShopMenu(){

        addRectangulo(SCALE+4, SCALE-55, SCALE+SCALEMENU-4, SCALE-42, 6);
        addRectangulo(SCALE+7, SCALE-54, SCALE+20, SCALE-43, 7);
        addRectangulo(SCALE+26, SCALE-54, SCALE+39, SCALE-43, 7);
        addRectangulo(SCALE+45, SCALE-54, SCALE+58, SCALE-43, 7);

    }
    
    public void sideMenuDraw(){

        StdDraw.picture(SCALE + 13, SCALE - 12, "./Images/MonBall.png", 17, 17);
        StdDraw.picture(SCALE + 32, SCALE -14, "./Images/HealthPotion.png", 18, 18);
        StdDraw.picture(SCALE + 51, SCALE -13, "./Images/StrengthPotion.png", 17, 17);
        StdDraw.picture(SCALE + 15, SCALE -35, "./Images/Player.png", 20, 20);

        StdDraw.picture(SCALE + 43, SCALE - 40, "./Images/Coins.png", 7, 7);
        
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 18));
        StdDraw.text(SCALE + 18, SCALE - 16, "x" + Inventory.getInventory().get(0).getAmount());
        StdDraw.text(SCALE + 37, SCALE - 16, "x" + Inventory.getInventory().get(1).getAmount());
        StdDraw.text(SCALE + 56, SCALE - 16, "x" + Inventory.getInventory().get(2).getAmount());


        //anadir lso mons en el menu      for(int i = 0)
        for(int i = 0; i < Player.playerMonsList().size(); i++){

            //Para marcar el mon activo
            if(Player.getActiveMonIndex() == i){ 
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.rectangle((SCALE + 7 + 0.5 + (19*i) + SCALE+20+(19*i))/2, (35 + 0.5 + 45)/2 , 13/2 + 1, 5 +0.5); 
            }

            StdDraw.picture(SCALE + 13 + (19*i), SCALE - 59, Player.getMon(i).getImageFront(), 10, 10);
            StdDraw.text(SCALE + 13 + (19*i), SCALE - 64, Player.getMon(i).getName());
                
            StdDraw.picture(SCALE + 19 + (19*i), SCALE - 56, Player.getMon(i).getTypeImage(), 4, 4);
            Player.getMon(i).drawHealthBar(SCALE + 13.75 + (19*i), SCALE - 66);
        }
    

        
        StdDraw.text(SCALE + 40, SCALE - 30, "Player , Lvl " + Player.getLvl());
        StdDraw.text(SCALE + 40, SCALE - 30, "");
        StdDraw.text(SCALE + 11, SCALE - 49, " Your Mons: ");

        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 24));
        StdDraw.text(SCALE + 36, SCALE - 40.5, "" + Inventory.getInventory().get(3).getAmount());
        

        //QUEDA HACER MONEDAS
    }

    /**
     * Change in map matrix to 1s in a filled rectangle with the two opposing vertices defined
     * @param xIni position on the x-axis of the first vertice
     * @param yIni position on the y-axis of the first vertice
     * @param xFin position on the x-axis of the last vertice
     * @param yFin position on the y-axis of the last vertice
     */
    public void addRectangulo(int xIni, int yIni, int xFin, int yFin, int val){
        //muro en pos inicial, y muro en pos final, si son el mismo, genera un solo muro
        if(yIni > yFin){
            int aux = yIni;
            yIni = yFin;
            yFin = aux;
        }
        if(xIni > xFin){
            int aux = xIni;
            xIni = xFin;
            xFin = aux;
        }

        for(int j = yIni; j <= yFin; j++){ //[y]
            for(int i = xIni; i <= xFin; i++){ //[x]
                changeTile(i, j, val);
            }
        }
    }

    /**
     * Crea una fila del valor correspondiente, pero solo sustituye en las posciones relativas al 33x33 
     * dentro del 100x100 establecido.
     * @param xIni
     * @param yIni
     * @param xFin
     * @param yFin
     * @param val
     */
    public void addRectangulo33x33(int xIni, int yIni, int xFin, int yFin, int val){
        if(yIni > yFin){
            int aux = yIni;
            yIni = yFin;
            yFin = aux;
        }
        if(xIni > xFin){
            int aux = xIni;
            xIni = xFin;
            xFin = aux;
        }

        for(int j = yIni; j <= yFin; j++){ //[y]
            for(int i = xIni; i <= xFin; i++){ //[x]
                if(i%3 == 0 && j%3 == 0)
                    changeTile(i, j, val);
            }
        }
    }

    /**This is an abstract method and therefore neccesary for each Level desinged, neccesary because each level has a different layout of walls.
     * 
     * By hand, one to one, defining a map with walls based on the desing decided.
     * 
     */
    public abstract void initializeWalls();

    public void addMenuBorder(int x, int y){ changeTile(x, y, 2); }
    public void addMenuInfill(int x, int y){ changeTile(x, y, 3); }
    /**This is an abstract method and therefore neccesary for each Level desinged, 
     * neccesary because each map could have a different layout for the menu.
     * 
     * default no menu */
    public abstract void initializeMenu();



        
    /*  No es necesario? los atributos son publicos. Accedes con Map.CANVAS
    
    public int canvas(){return CANVAS;}
 
        public int scale(){return SCALE;}
 
        public int canvasMenu(){return CANVASMENU;}
 
        public int scaleMenu(){return SCALEMENU;}
    */

    @Override
    public String toString(){
        String res = "";
        for(int j = 0; j < SCALE; j++){ //[y]
            res += "[";
            for(int i = 0; i < SCALE+SCALEMENU; i++){ //[x]
                res += getVal(i, j);
            }
            res += "] \n";
        }
        return res;
    }

    public void restartGraphics(){
        StdDraw.clear();
        for(int i = 0; i < 3; i++)
            draw();
    }

    /** Draws the map, reading the map's matrix and deciding the color 
     * based on the number in said slot. Here there is no check, it's only 
     * deciding the color of each tile and drawing it.
     * 
     * Something may draw on top of it, this is the base of the screen in terms of hierarchy
     */
    public void draw(){//Anado el inventario para poder mostrarlo en el menu

        boolean dibujar = true;
                // Loop through each cell in the map
        for (int i = 0; i < SCALE+SCALEMENU; i++) { //[y]
            for (int j = 0; j < SCALE; j++) { //[x]
                // Set color based on cell value
                dibujar = true;
                switch (getVal(i, j)) {
                    
                    case 0: 
                            dibujar = false;
                            StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3); // Example: 0 = White
                        break;
                    case 1: dibujar = false;
                            //StdDraw.setPenColor(new Color(200, 200, 200));
                            StdDraw.picture(i+0.5, j+1, "./Images/Muro.png", 3.4,3.4); // Example: 1 = GRAY walls
                        break;
                    case 2: StdDraw.setPenColor(StdDraw.WHITE); // Example: 2 = WHITE menu border
                        break;
                    case 3: StdDraw.setPenColor(StdDraw.BLACK); // Example: 3 = BLACK menu infill
                        break;
                    case 4:
                        dibujar = false;
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3);
                        StdDraw.picture(i+0.65, j+0.8, "./Images/NPC.png", 3, 3);
                        StdDraw.setPenColor(new Color(255, 0, 255)); // 4 = MAGENTA NPC
                        break;
                    case 5: 
                        StdDraw.setPenColor(new Color(218, 165, 32)  ); // 5= Chest // dorado (goldenrod)
                        dibujar = false;
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3);
                        StdDraw.picture(i+0.65, j+0.75, "./Images/Chest.png", 3.5, 3.5);
                        break;
                    case 6: StdDraw.setPenColor(StdDraw.DARK_GRAY); //Inventory bcakground
                        break;
                    case 7: StdDraw.setPenColor(50, 50, 50);
                        break;
                    case 8: 
                        dibujar = false;
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3);
                        StdDraw.picture(i+0.65, j+0.75, "./Images/Chest.png", 3.5, 3.5);
                        StdDraw.setPenColor(StdDraw.RED); //monchest
                        break;
                    case 9: 
                        dibujar = false;
                        StdDraw.setPenColor(StdDraw.GREEN); // Healer
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3);
                        StdDraw.picture(i+0.5, j+1, "./Images/Healer.png", 4.5, 4.5);
                        break;
                    case 10: 
                        dibujar = false;
                        StdDraw.setPenColor(StdDraw.RED); //Enemy
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3); // Example: 0 = White
                        StdDraw.picture(i+0.5, j+0.5, "./Images/Enemy1.png", 4.5, 4.5);
                        break;
                    case 11: 
                        StdDraw.setPenColor(StdDraw.ORANGE); //Store
                        dibujar = false;
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3);
                        StdDraw.picture(i+0.5, j+0.5, "./Images/StoreMap.png", 4.75, 5);
                        break;
                    case 12: 
                        dibujar = false;
                        StdDraw.setPenColor(new Color(165, 42, 42));//Puerta cerrada
                        StdDraw.picture(i+0.5, j+1, "./Images/Muro.png", 3.4,3.4); // Example: 1 = GRAY walls
                        StdDraw.picture(i+0.5, j+1, "./Images/Door.png", 3.4,3.4); // Example: 1 = GRAY walls
                        break;
                    case 13: 
                        StdDraw.setPenColor(new Color(222, 184, 135));//Puerta abierta
                        dibujar = false;
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3); // Example: 0 = White
                        break;
                    case 14: dibujar = false;
                        break;
                    case 15:
                        dibujar = false;
                        StdDraw.setPenColor(StdDraw.RED); //Enemy
                        StdDraw.picture(i+0.5, j+1, "./Images/Suelo.png", 3, 3); // Example: 0 = White
                        StdDraw.picture(i+0.5, j+0.755, "./Images/WildMon1.png", 4.25, 4.25);
                        break;
                    case 16:
                        dibujar = false;
                        StdDraw.picture(i+0.6, j+0.85, "./Images/Trampilla.png", 3.7, 3.7);
                        break;
                    default:
                        StdDraw.setPenColor(StdDraw.WHITE); // Example: Not defined.
                        break;
                }
                
                
                // Draw a filled square at (i, j) with size 1x1 (since scale is 100x100)
                if(dibujar) 
                    StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5); // +0.5 to center the square
            }
        }
    }
}
