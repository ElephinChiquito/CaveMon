package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class KingMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1400;
    private final double DMG = 1.4;

    public KingMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Hoja", "KingMon",
            new Attack[] {
                new Attack("Raíces del Trono", 475, "Hoja"),
                new Attack("Fuego Noble", 400, "Fuego")
            },
            "./Images/KingMon.png", "./Images/KingMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
