package src.Mons;

import src.Mons.NewMons.*;
import src.PJ.Player;

public class RandomAccessMons {
    public static Mon randomMon(){
        int x = Player.rand.nextInt(10); // Numero de mons que estan en la pool
        switch(x){
            case 0:return new GatoMon(Player.difficultyModifier).getMon();
            case 1:return new PerroMon(Player.difficultyModifier).getMon();
            case 2:return new TortugaMon(Player.difficultyModifier).getMon();
            case 3:return new PesadillaMon(Player.difficultyModifier).getMon();
            case 4:return new AngelMon(Player.difficultyModifier).getMon();
            case 5:return new AquaMon(Player.difficultyModifier).getMon();
            case 6:return new IceHotMon(Player.difficultyModifier).getMon();
            case 7:return new KingMon(Player.difficultyModifier).getMon();
            case 8:return new NeuroMon(Player.difficultyModifier).getMon();
            case 9:return new GlitchMon(Player.difficultyModifier).getMon();
            default: return new GlitchMon(Player.difficultyModifier).getMon();
        }

    }

    public static Mon randomMon(double addedDifficulty){
        int x = Player.rand.nextInt(10); // Numero de mons que estan en la pool
        switch(x){
            case 0:return new GatoMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 1:return new PerroMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 2:return new TortugaMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 3:return new PesadillaMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 4:return new AngelMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 5:return new AquaMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 6:return new IceHotMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 7:return new KingMon(Player.difficultyModifier+addedDifficulty).getMon();
            case 8:return new NeuroMon(Player.difficultyModifier).getMon();
            case 9:return new GlitchMon(Player.difficultyModifier+addedDifficulty).getMon();
            default: return new GlitchMon(Player.difficultyModifier+addedDifficulty).getMon();
        }

    }
}
