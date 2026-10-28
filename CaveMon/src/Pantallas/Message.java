package src.Pantallas;

import stdlib.StdDraw;
import java.awt.Font;
import java.awt.Color;

public class Message {

    public static void show(String message, double x, double y, Color color, int fontSize) {
        
        StdDraw.setPenColor(193,199,201);
        StdDraw.filledRectangle(x, y, 40, 10);
        StdDraw.setPenColor(223, 225, 226);
        StdDraw.filledRectangle(x, y, 39, 9);
        StdDraw.setPenColor(color);
        StdDraw.setFont(new Font("Times new Roman", Font.BOLD, fontSize));
        StdDraw.text(x, y, message);
        StdDraw.show();
    }

    // Overload for default color and size
    public static void show(String message, double x, double y) {
        show(message, x, y, Color.RED, 24);
    }

    /**
     * 
     * @param message
     * @param x
     * @param y
     * @param color
     * @param fontSize
     */
    public static void showInMenu(String[] message) {
        
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Times new Roman", Font.BOLD, 21));
        
        for(int i = 0; i < message.length; i++)
            StdDraw.textLeft(105, 24-4.5*i, message[i]);
        StdDraw.show();
        StdDraw.setFont(new Font("Times new Roman", Font.BOLD, 24));
    }

    /**
     * Shows message each row on its own and awaits till the player presses 'e'.
     * This allows for esay access as it's static, and allows for less writing for when showing textboxes
     * @param message
     */
    public static void showInMenuAndWait(String[] message){
        showInMenu(message);
        boolean pressedE = false;

        while(!pressedE){
            if(StdDraw.hasNextKeyTyped()){
                pressedE = StdDraw.nextKeyTyped() == 'e';
            }
        }
    }

    public static void writeInButton(int i, String message){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("Times new Roman", Font.BOLD, 24));
        System.out.println("Escribo boton en " + i);
        switch (i) {
            case 1:
                StdDraw.text(117.0, 87.5, message);
                break;
            case 2:
                StdDraw.text(148.0, 87.5, message);
                break;
            case 3:
                StdDraw.text(117.0, 70, message);
                break;
            case 4:
                StdDraw.text(148.0, 70, message);
                break;
        
            default:
                StdDraw.text(117.0, 87.5, "ERROR during text writing in button " + i);
                break;
        }
        
        StdDraw.show();
    }

    public static void waitForPressE(){
        
        boolean pressedE = false;
        StdDraw.show();

        while(!pressedE){
            if(StdDraw.hasNextKeyTyped()){
                pressedE = StdDraw.nextKeyTyped() == 'e';
            }
        }
    }

    public static void showInResponseAndWait(String[] msg){
        StdDraw.setPenColor(StdDraw.YELLOW);
        for(int i = 0; i < msg.length; i++){ StdDraw.textLeft(103, 41-4*i, msg[i]); }
        StdDraw.textLeft(103, 41-4*msg.length-1, "Pulsa 'E' para continuar");
        waitForPressE();
    }
}