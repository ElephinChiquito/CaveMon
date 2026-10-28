package src.Pantallas;

import src.Items.Coins;
import src.Items.HealthPotion;
import src.Items.Items;
import src.Items.MonBall;
import src.Items.StrenghtPotion;
import src.Mons.Mon;
import src.Mons.RandomAccessMons;
import src.NPCs.Chest;
import src.NPCs.Door;
import src.NPCs.Enemy;
import src.NPCs.Healer;
import src.NPCs.NPC;
import src.NPCs.Professor;
import src.NPCs.Store;
import src.NPCs.Trampilla;
import src.NPCs.WildMon;
import src.NPCs.MonChest;
import src.PJ.Player;
import java.util.LinkedList;

public class Lvl1 extends Map{

    private LinkedList<NPC> npcs;
    private double difficultyModifier;

    public Lvl1(){
        super();
        /** Definir los muros */
        initializeWalls();
        /** Definir el Borde Derecho */
        initializeMenu();
        /** Define el Menu de objetos */
        initializeSideMenu();

        npcs = new LinkedList<>();
        Map.playMusic("Audio\\lvl1Music.wav");
        //Esto se debe borrar e implementar una forma variable de cambiar el Mon, y en el futuro cambiar 
        
        difficultyModifier = Player.difficultyModifier; 
        
        //door se debe hacer antes que Enemy asociado
        NPC door1 = new Door(12, 21, new String[]{"Una puerta cerrada"}, "Door 1", this, false);
        npcs.add(door1);
        NPC door2 = new Door(21, 12, new String[]{"Una puerta cerrada"}, "Door 2", this, false);
        npcs.add(door2);
        NPC door3 = new Door(42, 18, new String[]{"Una puerta cerrada"}, "Door 3", this, false);
        npcs.add(door3);
        NPC door4 = new Door(57, 21, new String[]{"Una puerta cerrada"}, "Door 4", this, false);
        npcs.add(door4);
        NPC doorHabitacionGrande = new Door(81, 21, new String[]{"Una puerta cerrada"}, "Door 5", this, false);
        npcs.add(doorHabitacionGrande);
        NPC door5 = new Door(42, 33, new String[]{"Una puerta cerrada"}, "Door 6", this, false);
        npcs.add(door5);
        NPC door6 = new Door(30, 42, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(door6);
        NPC doorZigZag = new Door(21, 39, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(doorZigZag);
        NPC door7 = new Door(39, 54, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(door7);
        NPC challenge1 = new Door(72, 57, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(challenge1);
        NPC challenge2 = new Door(72, 60, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(challenge2);
        NPC challenge3 = new Door(69, 60, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(challenge3);
        NPC challenge4 = new Door(66, 60, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(challenge4);
        NPC challenge5 = new Door(66, 63, new String[]{"Una puerta cerrada"}, "Door 7", this, false);
        npcs.add(challenge5);
        
        
        /* Enemies *//* DifficultyModifier*DifficultyModifier para crecimiento explonencial */
        NPC enemy1 = new Enemy(27, 6, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
                        "./Images/Enemy1.png", 100, (Door) door1);
        npcs.add(enemy1);

        NPC enemy2 = new Enemy(78, 12, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
                        "./Images/Enemy1.png", 100);
        npcs.add(enemy2);

        NPC enemy3 = new Enemy(90, 6, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
                        "./Images/Enemy1.png", 100, (Door) doorHabitacionGrande);
        npcs.add(enemy3);

        NPC enemy4 = new Enemy(60, 36, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
            "./Images/Enemy1.png", 100, (Door) doorZigZag);
        npcs.add(enemy4);

        NPC enemy5 = new Enemy(51, 33, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
            "./Images/Enemy1.png", 100, (Door) door5);
        npcs.add(enemy5);

        NPC campeon1 = new Enemy(63, 51, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
            "./Images/Campeon1.png", 100, (Door) challenge1);
        npcs.add(campeon1);

        NPC campeon2 = new Enemy(63+6, 51, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
            "./Images/Campeon2.png", 100, (Door) challenge2);
        npcs.add(campeon2);

        NPC campeon3 = new Enemy(63+12, 51, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
            "./Images/Campeon3.png", 100, (Door) challenge3);
        npcs.add(campeon3);

        NPC campeon4 = new Enemy(63+18, 51, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
            "./Images/Campeon4.png", 100, (Door) challenge4);
        npcs.add(campeon4);

        NPC campeon5 = new Enemy(63+24, 51, new String[] {"Jamás podras vencer a mi pokemon", "MUAJAJJAJAJAJJAJA", "Preparate para perder"}, 
            "Enemigo 1", this, new Mon[] {
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
            RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)},
            "./Images/OldMan.png", 100, (Door) challenge5);
        npcs.add(campeon5);




        /* Mons salvajes */
        NPC wildMonn = new WildMon(33, 15, new String[] {"GUAU GUAU", "WOOF WOOF", "GRRRR"}, 
            "PerroMOn salvaje", this, new Mon[] {RandomAccessMons.randomMon(difficultyModifier)}, 
                    "./Images/Vacio.png", 100, (Door) door3);
        npcs.add(wildMonn);

        NPC wildMon2 = new WildMon(30, 30, new String[] {"~`|^{}[]@#"},
            "DevoraMundosMon", this, new Mon[] {RandomAccessMons.randomMon(difficultyModifier)},
            "./Images/Vacio.png", 50, (Door) door6);
        npcs.add(wildMon2);

        NPC wildMon4 = new WildMon(66, 9, new String[] {"Has encotrado un Mon salvaje!!!"}, 
            "Encuentro salvaje", this, 
            new Mon[]{RandomAccessMons.randomMon(difficultyModifier)}, "./Images/Vacio.png", 150, (Door) door4);
        npcs.add(wildMon4);

        NPC wildMonHabitacion6= new WildMon(21, 48, new String[] {"Has encotrado un Mon salvaje!!!"}, 
            "Encuentro salvaje", this, 
            new Mon[]{RandomAccessMons.randomMon(difficultyModifier)}, "./Images/Vacio.png", 50, (Door) door7);
        npcs.add(wildMonHabitacion6);


        /* Cofres */
        NPC habitacionSecreta1 = new Chest(3, 24, new String[]{""}, "Cofre", this,
                    new Items[]{new MonBall("Monball", 2),
                    new Coins("Coins", 160)}, false);
        npcs.add(habitacionSecreta1);
        NPC habitacionSecreta2 = new Chest(18, 24, new String[]{""}, "Cofre", this,
                    new Items[]{new HealthPotion("Health Potion", 3), 
                    new StrenghtPotion("Strenght Potion", 1)}, false);
        npcs.add(habitacionSecreta2);
        NPC habitacionGrande1 = new Chest(72, 33, new String[]{""}, "Cofre", this,
                    new Items[]{new Coins("Coins", 100)}, false);
        npcs.add(habitacionGrande1);
        NPC habitacionGrande2 = new Chest(75, 27, new String[]{""}, "Cofre", this,
                    new Items[]{new Coins("Coins", 100)}, false);
        npcs.add(habitacionGrande2);
        NPC habitacionGrande3 = new Chest(87, 27, new String[]{""}, "Cofre", this,
                    new Items[]{new HealthPotion("Health Potion", 2)}, false);
        npcs.add(habitacionGrande3);
        NPC habitacionGrande4 = new Chest(90, 33, new String[]{""}, "Cofre", this,
                    new Items[]{new HealthPotion("Health Potion", 1)}, false);
        npcs.add(habitacionGrande4);
        NPC habitacionGrande5 = new Chest(96, 39, new String[]{""}, "Cofre", this,
                    new Items[]{new StrenghtPotion("Strenght Potion", 3)}, false);
        npcs.add(habitacionGrande5);
        NPC habitacionZigZag = new Chest(3, 30, new String[]{""}, "Cofre", this,
                    new Items[]{new StrenghtPotion("Strenght Potion", 2), new Coins("Coins", 250)}, false);
        npcs.add(habitacionZigZag);
        

        NPC monChest1 = new MonChest(3, 15, 
            new String[]{"You opened a chest!"}, "",
            this, false, new Mon[]{
                RandomAccessMons.randomMon(difficultyModifier*difficultyModifier),
                RandomAccessMons.randomMon(difficultyModifier*difficultyModifier)}, (Door) door2);
        npcs.add(monChest1);

        NPC store = new Store(18, 18,
                    new String[]{"TIENDA MON", "¿Necesitas comprar items?", "Solo gangaaaaAAAs"},
                    "Store",
                    this,
                    new String[]{"", "./Images/Store.png"});
        npcs.add(store);

        /* Carteles */
        NPC tutorial = new Professor(6, 3, new String[] {"Este mundo se rige por estas normas", "Fuego > Hoja", "Hoja > Agua", "Agua > Fuego"}, "Tutorial", this);
        npcs.add(tutorial);
        NPC tutorial2 = new Professor(15, 3, new String[] {"Debes alcanzar la trampilla!", "Derrota a los enemigos", "Eso desbloqueara las puertas", "Disfruta"}, "Tutorial", this);
        npcs.add(tutorial2);
        NPC tutorial3 = new Professor(9, 18, new String[] {"MUCHA SUERTE"},  "Tutorial", this);
        npcs.add(tutorial3);
        NPC tutorial4 = new Professor(24, 18, new String[] {"Los ogros esos feos y rojos", "Representan los Mons salvajes", "Matalos, o debilitalos y capturalos", "Para capturar usa las MonBalls"},  "Tutorial", this);
        npcs.add(tutorial4);
        NPC tutorial5 = new Professor(24, 18, new String[] {"Supongo que la gracia de esta ", "habitación es escoger a ver", "cual abre la puerta.", "Quién abre cual, no lo se..."},  "Tutorial", this);
        npcs.add(tutorial5);
        NPC tutorial6 = new Professor(42, 51, new String[] {"Bien hecho! Has llegado lejos", "Podrás superar todos estos combates", "Que para nada copian algo patentado...", "Muchas suerte!"},  "Tutorial", this);
        npcs.add(tutorial6);
        

        NPC curandero = new Healer(3, 9, new String[] {"Yo curaré a tus Mons", "Paga 100 Monedas"}, "Curandero", this);
        npcs.add(curandero);

        NPC trampilla = new Trampilla(51, 72, new String[]{"PASA DE NIVEL!"}, "", this);
        npcs.add(trampilla);

        System.out.println(npcs);
        
        super.getNPCs(npcs);

    }

    /** By hand, one to one, defining a map with walls based on the desing decided.*/
    @Override
    public void initializeWalls(){
        /* Definir los muros exteriores */
        for(int i = 0; i < SCALE; i++){
            if(i%3 == 0){
                addMuro(0, i);
                addMuro(SCALE-1, i);
                addMuro(i, 0);
                addMuro(i, SCALE-1);
            }else{
                changeTile(0, i, 14);
                changeTile(SCALE-1, i,14);
                changeTile(i, 0, 14);
                changeTile(i, SCALE-1,14); 
            }
        }
        /* Definir los muros interiores */
        addRectangulo33x33(3, 21, 96, 21, 1);
        addRectangulo33x33(21, 27, 21, 3, 1);
        addRectangulo33x33(21, 27, 3, 27, 1);
        addRectangulo33x33(42, 15, 42, 3, 1);
        addRectangulo33x33(21, 27, 3, 27, 1);
        addRectangulo33x33(42, 24, 42, 42, 1);
        addRectangulo33x33(69, 42, 69, 24, 1);
        addRectangulo33x33(99, 42, 3, 42, 1);
        addRectangulo33x33(81, 42, 3, 42, 1);
        addRectangulo33x33(3, 63, 96, 63, 1);
        addRectangulo33x33(21, 30, 21, 36, 1);
        addRectangulo33x33(3, 63, 96, 63, 1);
        addRectangulo33x33(15, 33, 15, 39, 1);
        addRectangulo33x33(9, 30, 9, 36, 1);
        addRectangulo33x33(3, 39, 3, 33, 1);
        addRectangulo33x33(39, 60, 39, 45, 1);
        addRectangulo33x33(42, 60, 96, 57, 1);
        addRectangulo33x33(42, 45, 96, 48, 1);


        //Final room
        addRectangulo33x33(42, 72, 60, 72, 1);

        addRectangulo33x33(39, 75, 63, 75, 1);

        addRectangulo33x33(36, 78, 66, 78, 1);

        addRectangulo33x33(69, 81, 33, 90, 1);

        addRectangulo33x33(30, 93, 72, 96, 1);
    }

    /** Definir la text box del CANVASMENU */
    @Override
    public void initializeMenu(){
        for(int i = SCALE; i < SCALE+SCALEMENU; i++){
            for(int j = 0; j < SCALE; j++){
                if(j == 0 || j == SCALE-1 || i == SCALE || i == SCALE+SCALEMENU-1)
                    addMenuBorder(i, j); //Solo dibujar, la info del CANVASMENU se guarda localmente, esto es el fondo del CANVASMENU que se dibuja con el mapa
                else
                    addMenuInfill(i, j); //Dibujar negro
                
            }
        } 
    }

    public LinkedList<NPC> initializeGlobalNpcs(){return npcs;}


}