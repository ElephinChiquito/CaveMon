package src.PJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import src.Mons.Mon;
import src.Mons.NewMons.GlitchMon;
import src.Pantallas.Map;
import src.Pantallas.Message;
import stdlib.StdDraw;


public class Player {

    private static int posX;
    private static int posY;
    private boolean checkCollision = true;
    private ArrayList<Integer> collisionNumbers = new ArrayList<>(); //Lista de valor de las casillas que SI tienen colision
    private static ArrayList<Mon> playerMonsList; //Lista de los mons que tiene el jugador
    private static int activeMon;

    public static boolean inLvl1;
    public static Random rand = new Random();
    public static double difficultyModifier = 1;
    private String looking = "Arriba";
    private static int lvl = 1;

    public Player(int x, int y){
        posX = x; posY = y;
        //Aqui hay que definir Mon

        //Definir valores con los que hay colision.
        collisionNumbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16));
        playerMonsList = new ArrayList<> ();
        /* playerMonsList.add(new TortugaMon(difficultyModifier).getMon());
        playerMonsList.add(new GatoMon(difficultyModifier).getMon());
        playerMonsList.add(new PerroMon(difficultyModifier).getMon());
        //Mons */
        /* this.monsList = monsList; */
        activeMon = 0;

        //StdDraw.picture(20, 20, pictures[2], 35, 35);
    }

 
    public int posX(){ return posX; }
    public int posY(){ return posY; }
    public boolean checkCollision(){ return checkCollision; }
    public void invertCheckCollision(){ checkCollision = !checkCollision; }
    public static ArrayList<Mon> playerMonsList(){ return playerMonsList; }

    /**
     * Get the mon number i
     * @param i the number of the mon
     */
    public static Mon getMon(int i){ return playerMonsList.get(i); }
    public static Mon getActiveMon(){ return playerMonsList.get(activeMon); }
    public static int getActiveMonIndex(){ return activeMon; }
    public static void setActiveMon(int i){ activeMon = i; }
    public static int getLvl(){ return lvl; }
    public static void addLvl(){ lvl++; }
    
    /**
     * adds mon to playerMonsList
     * @param mon
     */
    public static void addMon(Mon mon){ 
        if(playerMonsList.size()>=3){
            Message.showInResponseAndWait(new String[] {"","","No funciona si tienes 3 Mons!!!"});
        }
        else{
            playerMonsList.add(mon); 
            for(int i = 0; i < playerMonsList.size(); i++){
                if(getMon(i).isAlive()){
                    setActiveMon(i);
                }
            }
        }
        
    }

    public static boolean allDead(){
        boolean allDead = true;
        for(int i = 0; i < playerMonsList.size() && allDead; i++){
            allDead = !playerMonsList.get(i).isAlive();
        }
        return allDead;
    }

    public void mover(int dx, int dy, Map mapa){
        //Collision with walls
        boolean hayMuro = false;
        System.out.print("Intento ir a: " + mapa.getVal(posX+dx, posY+dy));

        /* Esto esta al reves pq el mapa de numeros esta traspuesto */
        hayMuro = collisionNumbers.contains(mapa.getVal(posX+dx+2*dx, posY+dy+2*dy)) || 
                  collisionNumbers.contains(mapa.getVal((posX+dx+2*dx)+(1*dy), (posY+dy+2*dy)+(1*dx))) ||
                  collisionNumbers.contains(mapa.getVal((posX+dx+2*dx)-(1*dy), (posY+dy+2*dy)-(1*dx))) ||
                  collisionNumbers.contains(mapa.getVal((posX+dx+2*dx)+(2*dy), (posY+dy+2*dy)+(2*dx))) ||
                  collisionNumbers.contains(mapa.getVal((posX+dx+2*dx)-(2*dy), (posY+dy+2*dy)-(2*dx))); //Si se quieren añadir mas colisiones, pensarlo y hacerlo, esto es solo para muros de momento

        if(!hayMuro || !checkCollision){ posX += dx; posY += dy; }

        System.out.println(" || Posicion actual: " + posX + ", " + posY);
        if(dx == 1)
            looking = "Derecha";
        if(dx == -1)
            looking = "Izquierda";
        if(dy == 1)
            looking = "Arriba";
        if(dy == -1)
            looking = "Abajo";
    }

    public static void setPos(int x, int y){
        posX = x;
        posY = y;
    }

    public void draw(){
        //StdDraw.setPenColor(new Color(0, 255, 0));
        //StdDraw.filledCircle(posX+0.5, posY+0.5, 0.5);
        switch (looking) {
            case "Arriba":
                StdDraw.picture(posX+0.5, posY+0.755, "./Images/PlayerAtras.png", 4.5, 4.5);
                break;
            case "Abajo":
                StdDraw.picture(posX+0.5, posY+0.755, "./Images/Player.png", 4, 4);
                break;
            case "Derecha":
                StdDraw.picture(posX+0.5, posY+0.755, "./Images/PlayerDerecha.png", 4.5, 4.5);
                break;
            case "Izquierda":
                StdDraw.picture(posX+0.5, posY+0.755, "./Images/PlayerIzquierda.png", 4.5, 4.5);
                break;
            default:
                StdDraw.picture(posX+0.5, posY+0.5, new GlitchMon(1).getMon().getImageFront(), 4.5 ,4.5);
                break;
        }
        /** Escribir información importante */
        //System.out.print("|pos = (" + posX + ", " + posY + ")");
    } 

    public static void drawMonHP(){
        //Vida !!!!Cambiar 0 por manera de acceder al mon que sea
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(104, 49, "Vida de tu Mon (" + playerMonsList.get(activeMon).hp() + " / " + playerMonsList.get(activeMon).maxHP() + ")");
        StdDraw.picture(Map.SCALE + 59, Map.SCALE - 50.5, Player.getActiveMon().getTypeImage(), 4, 4);
        Mon monActual = playerMonsList.get(activeMon);
        
        int pixelHP = monActual.maxHP()/57;
        for(int i = 0; i < 57; i++){
            if(pixelHP*i < monActual.hp())
                StdDraw.setPenColor(StdDraw.GREEN);
            else
                StdDraw.setPenColor(StdDraw.RED);
            
            StdDraw.filledSquare(104 + i + 0.5, 46 + 0.5, 0.5);
        }
    }

    public static void drawInBattle(){
        StdDraw.picture(25, 25, getActiveMon().getImageBack(), 35, 35);
    }
    
}
