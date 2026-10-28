package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class IceHotMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1400;
    private final double DMG = 1.35;

    public IceHotMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Agua", "IceHotMon",
            new Attack[] {
                new Attack("Chorro Helado", 475, "Agua"),
                new Attack("Corte Vegetal", 400, "Hoja")
            },
            "./Images/IceHotMon.png", "./Images/IceHotMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
