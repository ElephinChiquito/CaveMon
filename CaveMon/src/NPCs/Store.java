package src.NPCs;

import stdlib.StdDraw;
import java.awt.Font;

import src.PJ.*;
import src.Pantallas.*;
import src.Items.*;

public class Store extends NPC{ // estaria bien poner que enemy sea hijo de NPC    

    /* private String name; Comentado por ser ahora hijo*/
    
    private String[] pictures; //player and Store pictures
    private boolean buying;

    public Store(int x, int y, String[] dialog, String name, Map mapa, String[] pictures) {

        super(x, y, dialog, name, mapa);
        this.pictures = pictures;
        mapa.changeTile(x, y, 11);

    }

    public String[] pictures(){ return pictures; }
    public boolean buying(){ return buying; }
    

    @Override
    public void interact(){

        buying = true;
        String[] res = new String[dialog.length+1];
        for(int i = 0; i < dialog.length; i++){ res[i] = dialog[i]; }
        res[dialog.length] = "Presiona la 'e' para continuar.";
        Message.showInMenuAndWait(res); //(100, 30) - (165, 0)
        Battle storeMap = new Battle();

        for(int i = 0; i < 3; i++){
            
            storeMap.draw();
            storeMap.escribirProductosTienda();
            storeMap.initializeShopMenu();
            StdDraw.show();
        }
        Message.showInMenu(new String[] {"¿Que item quieres comprar?", 
            "1. MonBalls (x5)                 30 coins", 
            "2. Health Potion                 45 coins", 
            "3. Strength Potion            50 coins", 
            "4. Salir al mapa"});

        draw();
        StdDraw.show();

        while(buying){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                boolean refrescar = false;

                switch (key) {
                    case '1': //Ataque
                        buyItem(0);
                        refrescar = true;
                        break;
                    case '2':
                        buyItem(1);
                        refrescar = true;
                        break;
                    case '3':
                        buyItem(2);
                        refrescar = true;
                        break;
                    case '4':
                        refrescar = true; 
                        buying = false;
                        break;
                }

                if(buying&&refrescar){
                    storeMap.restartGraphics();
                    draw();
                    storeMap.escribirProductosTienda();
                    Message.showInMenu(new String[] {"¿Que item quieres comprar?",
                                                     "1. MonBalls (x5)          30 coins",
                                                     "2. Health Potion          45 coins",
                                                     "3. Strength Potion        50 coins",
                                                     "4. Salir al mapa"});
                    StdDraw.show();
                }
                                   
            }
        }
        
    }
    /**
     * 
     * @param i is the index of the bought item. 0-MonBall 1-HelathPotion 2-StrengthPotion
     */
    public void buyItem(int i){

        if(i==0){//30 price for 5

            if(Inventory.getCoins().getAmount()>=30){
                Inventory.removeItem(3, 30);
                Inventory.addItem(new MonBall("MonBall", 5));
                Message.showInResponseAndWait(new String[]{"Has comprado 5 MonBalls", "Presiona 'e' para continuar"});
            }
            else{
                Message.showInResponseAndWait(new String[]{"NO tienes monedas suficientes", "Presiona 'e' para continuar"});
            }

        }
        if(i==1){//45 price

            if(Inventory.getCoins().getAmount()>=45){
                Inventory.removeItem(3, 45);
                Inventory.addItem(new HealthPotion("Health Potion", 1));
                Message.showInResponseAndWait(new String[]{"Has comprado 1 Helath Potion", "Presiona 'e' para continuar"});
            }
            else{
                Message.showInResponseAndWait(new String[]{"NO tienes monedas suficientes", "Presiona 'e' para continuar"});
            }

        }
        if(i==2){//50 price

            if(Inventory.getCoins().getAmount()>=50){
                Inventory.removeItem(3, 50);
                Inventory.addItem(new StrenghtPotion("Strength Potion", 1));
                Message.showInResponseAndWait(new String[]{"Has comprado 1 Strength Potion", "Presiona 'e' para continuar"});
            }
            else{
                Message.showInResponseAndWait(new String[]{"NO tienes monedas suficientes", "Presiona 'e' para continuar"});
            }
        }

    }


    /** Dibujar todo lo relacionado a la batalla que sean imagenes y la vida */
    public void draw(){
        // Imagenes
        StdDraw.picture(50, 50, pictures[1], 100, 100);
        drawSpacingLines();

        
        StdDraw.picture(Map.SCALE + 13, Map.SCALE - 48, "./Images/MonBall.png", 17, 17);
        StdDraw.picture(Map.SCALE + 32, Map.SCALE -50, "./Images/HealthPotion.png", 18, 18);
        StdDraw.picture(Map.SCALE + 51, Map.SCALE -49, "./Images/StrengthPotion.png", 17, 17);

        StdDraw.picture(Map.SCALE - 12, Map.SCALE - 8, "./Images/Coins.png", 13, 13);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 18));
        StdDraw.text(Map.SCALE + 18, Map.SCALE - 51, "x" + Inventory.getInventory().get(0).getAmount());
        StdDraw.text(Map.SCALE + 37, Map.SCALE - 51, "x" + Inventory.getInventory().get(1).getAmount());
        StdDraw.text(Map.SCALE + 56, Map.SCALE - 51, "x" + Inventory.getInventory().get(2).getAmount());
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 30));
        StdDraw.text(Map.SCALE - 24, Map.SCALE - 8.5, ""+Inventory.getInventory().get(3).getAmount());

    }

    public void drawSpacingLines(){

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(Map.SCALE+Map.SCALEMENU/2, 60.5, Map.SCALEMENU/2, 0.15);
        StdDraw.filledRectangle(100+50000/(750*2), 30, 50000/(750*2), 0.15);

    }

}
   