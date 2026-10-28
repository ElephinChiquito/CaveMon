package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class GlitchMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1000;
    private final double DMG = 1.8;

    public GlitchMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Fuego", "GlitchMon",
            new Attack[] {
                new Attack("Explosión de Bits", 500, "Fuego"),
                new Attack("Salpicadura de Marea", 350, "Agua")
            },
            "./Images/GlitchMon.png", "./Images/GlitchMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
