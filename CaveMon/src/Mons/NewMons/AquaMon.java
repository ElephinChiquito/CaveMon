package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class AquaMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1200;
    private final double DMG = 1.6;
    
    public AquaMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Agua", "AquaMon",
            new Attack[] {
                new Attack("Ola Rompedora", 450, "Agua"),
                new Attack("Látigo Enredado", 400, "Hoja")
            },
            "./Images/AquaMon.png", "./Images/AquaMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
