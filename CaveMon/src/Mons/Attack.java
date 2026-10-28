package src.Mons;

public class Attack {
    
    private int dmg;
    private String attackName;
    private String attackType;

    public Attack(String attackName, int dmg,  String type){
        this.dmg = dmg;
        this.attackName = attackName;
        this.attackType = type;
    }

    public int dmg(){
        return dmg;
    }
    public String type(){
        return attackType;
    }
    public String attackName(){
        return attackName;
    }

    public String whatResists(){
        if(attackType.equals("Fuego"))
            return "Agua";
        else if(attackType.equals("Agua"))
            return "Hoja";
        else
            return "Fuego";
    }

    public String toString(){
        return attackName + " (Type: " + attackType + ", Damage: " + dmg + ")";
    }

    
}
