package src.Mons.NewMons;

import src.Mons.Attack;
import src.Mons.Mon;
import src.PJ.Player;

public class TortugaMon implements IMon {

    private Mon mon;
    private final int MAXHP = 1500;
    private final double DMG = 1.3;
    
    public TortugaMon(double difficultyModifier){
        //valores entre 75% y 125% de las estadisticas del MON, y esto a su vez modificado por la dificultad
        double hpModifier = (1.25-Player.rand.nextDouble()*0.5)*difficultyModifier;
        double dmgModifier = (1.25-Player.rand.nextDouble()*0.5)*difficultyModifier;

        this.mon = new Mon(MAXHP + (int) (hpModifier*MAXHP), DMG * dmgModifier, "Normal", "TortugaMon", 
            new Attack[] {new Attack("Patada Normal", 500, "Normal"), 
                          new Attack("Pistola Agua", 500, "Agua")},
                          "./Images/TortugaMon.png", "./Images/TortugaMonAtras.png");
    }

    public Mon getMon(){ return mon; }
}
