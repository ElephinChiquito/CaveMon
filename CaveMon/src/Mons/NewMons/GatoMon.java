package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class GatoMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1100;
    private final double DMG = 1.7;

    public GatoMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Hoja", "GatoMon",
            new Attack[] {
                new Attack("Zarpazo Floral", 500, "Hoja"),
                new Attack("Llamarada Rápida", 400, "Fuego")
            },
            "./Images/GatoMon.png", "./Images/GatoMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
