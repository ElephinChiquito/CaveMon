package src.Mons;

import java.awt.Color;
import java.util.ArrayList;

import src.PlayAudio;
import stdlib.StdDraw;

/* En la batalla, estaria bien para implementar una Queue con las acciones de cada uno (enemigo y tu). 
 * Esta se mostraria en el lado derecho, y que solo guarde info dos turnos, es decir, que solo aparezcan 
 * dos turnos, y si ocurre otro haces pop del primero (Stack), y añades add (Stack) el siguiente FIFO.
 */
public class Mon{
    
    protected int hp;
    protected double dmg; //dmg multiplier, the dmg of an attack equals dmg from the attack itself multiplied by the dmg modifier of the Mon
    protected String type; 
    protected String name;
    protected final int MAXHP;
    protected boolean strPotion;
    protected String imageFront;
    protected String imageBack;
    protected ArrayList<Attack> attackList;

    /**
     * Constructor for the Mon class.
     * @param hp The health points of the mon
     * @param dmg The damage multiplier of the mon
     * @param type The type of the mon
     * @param name The name of the mon
     * @param attacks The attacks of the mon
     */
    public Mon(int MAXHP, double dmg, String type, String name, Attack[] attacks, String imageFront, String imageBack){

        this.MAXHP = MAXHP;
        hp = MAXHP;
        this.dmg = dmg; 
        this.type = type;
        this.name = name;
        this.attackList = new ArrayList<>();

        for(int i = 0; i < attacks.length; i++){
            attackList.add(attacks[i]);
        }
        this.imageFront = imageFront;
        this.imageBack = imageBack;

    }

    public String getName(){ return name; }
    public String getImageFront(){ return imageFront; }
    public String getImageBack(){ return imageBack; }

    public boolean strPotion(){ return strPotion; }
    public void setStrPotion(boolean condition){ strPotion = condition; }

    /**
     * Method to get the attack list of the mon.
     * @return The attack list of the mon
     */
    public ArrayList<Attack> attackList(){ return attackList; }

    /**
     * Method to get the hp of the mon.
     * @return The health points of the mon
     */
    public int hp(){ return hp; }
    public int maxHP(){ return MAXHP; }

    /**
     * Method to substract hp of the mon.
     * @param hp The health points to substract
     */
    public void takeDmg(int dmg){ 
        
        if(hp <= dmg)
            hp = 0;
        else
            hp -= dmg; 
    
    }

    /**
     * Method to heal the mon.
     * @param hph The amount of health points to heal
     */
    public void heal(int hph){ 

        if(hp >= MAXHP-hph) //Curar por completo
            hp = MAXHP;
        else
            hp += hph;
    }

    /**
     * Method to check if the mon is alive.
     * @return true if the mon is alive, false otherwise
     */
    public boolean isAlive(){ return hp > 0; }

    /**
     * Method to get the dmg multiplier of the mon.
     * @return dmg multiplier of the mon
     */
    public double dmg(){ return dmg; }
    /**
     * Method to get the type of the mon.
     * @return type of the mon
     */
    public String type(){ return type; }

    /**
     * Method to get the name of the mon.
     * @return name of the mon
     */
    public String name(){ return name; }

/*     private void playSound(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } */

    public void receiveAttack(Attack attack, Mon attacker){
        String whatCrits = whatCrits(); 
        int resDmg = (int) (attack.dmg() * attacker.dmg());
        if(attack.type().equals(whatCrits))
            resDmg = resDmg * 2;
        else if(attack.whatResists().equals(type))
            resDmg = resDmg / 2;
        
        if(attacker.strPotion){
            resDmg = resDmg*2;
            attacker.setStrPotion(false);
        }

        takeDmg(resDmg);
        PlayAudio.playSound("Audio\\HitSound.wav", -20.00f);

    }

    
    public String whatCrits(){
        if(type.equals("Fuego"))
            return "Agua";
        else if(type.equals("Agua"))
            return "Hoja";
        else if(type.equals("Hoja"))
            return "Fuego";
        else
            return "NoCritico";
    }

    public String getTypeImage(){
        String typeImage = "";
        if(type().equals("Fuego"))
                typeImage = "./Images/Fuego.png";
        if(type().equals("Agua"))
                typeImage = "./Images/Agua.png";
        if(type().equals("Hoja"))
                typeImage = "./Images/Hoja.png";
        if(type().equals("Normal"))
                typeImage = "./Images/Normal.png";
        return typeImage;
    }

    public void drawHealthBar(double x, double y){

        Color prevColor = StdDraw.getPenColor();

        double barWidth = 12; 
        double barHeight = 1;

        double hpPerc = (double) hp / MAXHP; //porcentaje de vida

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(x, y, barWidth / 2, barHeight / 2);

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(x - (barWidth * (1 - hpPerc)) / 2, y, (barWidth * hpPerc) / 2, barHeight / 2);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(x, y, barWidth / 2, barHeight / 2);

        StdDraw.setPenColor(prevColor);
    }

    public String toString(){

        return "\tName: " + name + "\n" +
               "\tHP: " + hp + "\n" +
               "\tDamage: " + dmg + "\n" +
               "\tType: " + type + "\n" + attackList();
        
    }
}
