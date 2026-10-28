package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class AngelMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1650;  // Ligeramente mayor
    private final double DMG = 1.6;  // Ligeramente mayor

    public AngelMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Normal", "AngelMon",
            new Attack[] {
                new Attack("Luz Divina", 525, "Normal"),
                new Attack("Oleada Ardiente", 475, "Fuego"),
                new Attack("Juicio Celestial", 500, "Agua")
            },
            "./Images/AngelMon.png", "./Images/AngelMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
