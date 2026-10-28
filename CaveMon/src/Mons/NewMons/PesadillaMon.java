package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class PesadillaMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1600;  // Ligeramente mayor
    private final double DMG = 1.7;  // Ligeramente mayor

    public PesadillaMon(double difficultyModifier){
        double hpModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;
        double dmgModifier = (1.25 - Player.rand.nextDouble() * 0.5) * difficultyModifier;

        this.mon = new Mon(MAXHP + (int)(hpModifier * MAXHP), DMG * dmgModifier, "Fuego", "PesadillaMon",
            new Attack[] {
                new Attack("Llama del Terror", 525, "Fuego"),
                new Attack("Espinas del Olvido", 475, "Hoja"),
                new Attack("Pesadilla Absoluta", 500, "Normal")
            },
            "./Images/PesadillaMon.png", "./Images/PesadillaMonAtras.png");
    }

    public Mon getMon() { return mon; }
}
