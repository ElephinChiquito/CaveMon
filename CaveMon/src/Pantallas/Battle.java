package src.Pantallas;

import stdlib.StdDraw;
import src.Mons.Mon;
import src.PJ.*;
import java.awt.Font;



public class Battle extends Map{
    
    
    public Battle(){
        super();
        /** Definir los muros */
        initializeWalls();
        /** Definir el Menu */
        initializeMenu();

        initializeButtons();

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
        //No hay muros interiores, solo el borde.
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

        addRectangulo(SCALE + 3, 3, SCALE+SCALEMENU-4, 27, 6);
        addRectangulo(SCALE + 4, 4, SCALE+SCALEMENU-5, 26, 7);

    }

    

    public void initializeButtons(){
        addRectangulo(104, 95, 130, 80, 7);
        addRectangulo(135, 95, 161, 80, 7);
        addRectangulo(104, 77, 130, 62, 7);
        addRectangulo(135, 77, 161, 62, 7);
    }

    public void escribirTurnoInicial(){
        Message.writeInButton(1, "1.- Ataque");
        Message.writeInButton(2, "2.- Items");
        Message.writeInButton(3, "3.- Cambio");
        Message.writeInButton(4, "4.- Huir");
    }

    public void escribirProductosTienda(){
        Message.writeInButton(1, "1. MonBall (x5)");
        Message.writeInButton(2, "2. Helath Potion");
        Message.writeInButton(3, "3. Strength Potion");
        Message.writeInButton(4, "4. Salir");
    }

    
    public void escribirTurnoAtaque(){
        for(int i = 0; i < Player.getActiveMon().attackList().size(); i++){
            Message.writeInButton(i+1, (i+1) + ".- " + Player.getActiveMon().attackList().get(i).attackName()); //Cambiar!!!! no debe ser referencia estatica
        }
        Message.writeInButton(4, "4.- Volver");
    }

    public void escribirTurnoItems(){
        Message.writeInButton(1, "1.- Pocion de Vida");
        Message.writeInButton(2, "2.- Pocion de Fuerza");
        Message.writeInButton(3, "3.- MonBall");
        Message.writeInButton(4, "4.- Volver");
    }

    public void escribirDiscardMon(Mon newMon){
        for(int i = 0; i < Player.playerMonsList().size(); i++)
            Message.writeInButton(i+1, Player.getMon(i).name());
        Message.writeInButton(4, newMon.name());
    }

    public void escribirTurnoChangeMon(){
        for(int i = 0; i < Player.playerMonsList().size(); i++){
            Message.writeInButton(i+1, i+1 + ".- " + Player.getMon(i).name());
        }
        if(Player.playerMonsList().size() >= 1)
            StdDraw.picture(128.0, 82.5, Player.getMon(0).getTypeImage(), 4, 4);
        if(Player.playerMonsList().size() >= 2)
            StdDraw.picture(159.0, 82.5, Player.getMon(1).getTypeImage(), 4, 4);
        if(Player.playerMonsList().size() >= 3)
            StdDraw.picture(128.0, 64.5, Player.getMon(2).getTypeImage(), 4, 4);
        Message.writeInButton(4, "4.- Volver");
    }

    public void sideMenuItemsDraw(){

        /* addRectangulo(104, 95, 130, 80, 7);
           addRectangulo(135, 95, 161, 80, 7);
           addRectangulo(104, 77, 130, 62, 7);
           addRectangulo(135, 77, 161, 62, 7); */
        StdDraw.picture(SCALE + 13-4, 69.5-4, "./Images/MonBall.png", 10, 10);
        StdDraw.picture(SCALE + 13-4, SCALE - 12.5-5, "./Images/HealthPotion.png", 11, 11);
        StdDraw.picture(SCALE + 48-8, SCALE - 12.5-5, "./Images/StrengthPotion.png", 10, 10);
        
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 18));
        StdDraw.text(SCALE + 15-4, 67.5-4, "x" + Inventory.getInventory().get(0).getAmount());
        StdDraw.text(SCALE + 15-4, SCALE - 14.5-4, "x" + Inventory.getInventory().get(1).getAmount());
        StdDraw.text(SCALE + 50-8, SCALE - 14.5-4, "x" + Inventory.getInventory().get(2).getAmount());
    }

}
