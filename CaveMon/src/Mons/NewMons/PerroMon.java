package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class PerroMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1350;
    private final double DMG = 1.3;

    public PerroMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Fuego", "PerroMon",
            new Attack[] {
                new Attack("Colmillo Ardiente", 450, "Fuego"),
                new Attack("Ola de Choque", 375, "Agua")
            },
            "./Images/PerroMon.png", "./Images/PerroMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
