package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class NeuroMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1300;
    private final double DMG = 1.5;

    public NeuroMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Normal", "NeuroMon",
            new Attack[] {
                new Attack("Golpe Psíquico", 400, "Normal"),
                new Attack("Llama Neuronal", 450, "Fuego"),
                new Attack("Estallido Mental", 500, "Normal")
            },
            "./Images/NeuroMon.png", "./Images/NeuroMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
