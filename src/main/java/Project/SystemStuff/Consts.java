package Project.SystemStuff;

import Project.Battles.AtackMngr;
import Project.Battles.BattleManager;
import Project.Battles.TurnChoice;
import Project.Battles.PokemonData.Moves.EffectApplicator;
import Project.Battles.PokemonData.Moves.Move;
import Project.Battles.PokemonData.Pokemon;

import java.util.Random;

public class Consts {
    public final static int compareToVal=1;
    public final static int HP_LENGTH_PRINT = 30;
    public final static int stsBurn = 1;
    public final static int stsFreeze = 2;
    public final static int stsParalysis = 3;
    public final static int stsPoison = 4;
    public final static int stsSleep = 5;
    public final static int typeNormal = 1;
    public final static int typeFight = 2;
    public final static int typeFlying = 3;
    public final static int typePoison = 4;
    public final static int typeGround = 5;
    public final static int typeRock = 6;
    public final static int typeBug = 7;
    public final static int typeGhost = 8;
    public final static int typeSteel = 9;
    public final static int typeFire = 10;
    public final  static int typeWater = 11;
    public final static int typeGrass = 12;
    public final static int typeElectric = 13;
    public final static int typePsychic = 14;
    public final static int typeIce = 15;
    public final static int typeDragon = 16;
    public final static int typeDark = 17;
    public final static int typeFairy = 18;
    public final static Random rnd = new Random();
    public final static int aRenderDis = 9;
    public final static int bRenderDis = 9;
    public final static byte[][] tilemap1={
            {0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,2}};
    public final  static double[][] typeChart = {
            {1, 1, 1, 1, 1, .5, 1, 0, 0.5, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 1, .5, .5, 1, 2, .5, 0, 2, 1, 1, 1, 1, 0.5, 2, 1, 2, 0.5},
            {1, 2, 1, 1, 1, .5, 2, 1, .5, 1, 1, 2, 0.5, 1, 1, 1, 1, 1},
            {1, 1, 1, .5, .5, .5, 1, .5, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2},
            {1, 1, 0, 2, 1, 2, .5, 1, 2, 2, 1, .5, 2, 1, 1, 1, 1, 1},
            {1, .5, 2, 1, .5, 1, 2, 1, .5, 2, 1, 1, 1, 1, 2, 1, 1, 1},
            {1, .5, .5, .5, 1, 1, 1, .5, .5, .5, 1, 2, 1, 2, 1, 1, 2, .5},
            {0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, 1},
            {1, 1, 1, 1, 1, 2, 1, 1, .5, .5, .5, 1, .5, 1, 2, 1, 1, 2},
            {1, 1, 1, 1, 1, .5, 2, 1, 2, .5, .5, 2, 1, 1, 2, .5, 1, 1},
            {1, 1, 1, 1, 2, 2, 1, 1, 1, 2, .5, .5, 1, 1, 1, .5, 1, 1},
            {1, 1, .5, .5, 2, 2, .5, 1, .5, .5, 2, .5, 1, 1, 1, .5, 1, 1},
            {1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 2, .5, .5, 1, 1, .5, 1, 1},
            {1, 2, 1, 2, 1, 1, 1, 1, .5, 1, 1, 1, 1, .5, 1, 1, 0, 1},
            {1, 1, 2, 1, 2, 1, 1, 1, .5, .5, .5, 2, 1, 1, .5, 2, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, .5, 1, 1, 1, 1, 1, 1, 2, 1, 0},
            {1, .5, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, .5},
            {1, 2, 1, .5, 1, 1, 1, 1, .5, .5, 1, 1, 1, 1, 1, 2, 2, 1}
    };
    final public static int MOVE_CODE = 1;
    final public static int SWITCH_CODE = 2;
    final public static int VIEW_CODE = 3;
    final public static int BAG_CODE = 4;
    final public static int xTileSize=32;
    final public static int yTileSize=32;
    final public static int DEFAULT_LEVEL = 12;
    final public static EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> standardFunction=(attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0;
}
