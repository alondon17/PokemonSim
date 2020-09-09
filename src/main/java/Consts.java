import java.util.Random;

public class Consts {
    final static int compareToVal=1;
    final static int HP_LENGTH_PRINT = 30;
    final static int stsBurn = 1;
    final static int stsFreeze = 2;
    final static int stsParalysis = 3;
    final static int stsPoison = 4;
    final static int stsSleep = 5;
    final static int typeNormal = 1;
    final static int typeFight = 2;
    final static int typeFlying = 3;
    final static int typePoison = 4;
    final static int typeGround = 5;
    final static int typeRock = 6;
    final static int typeBug = 7;
    final static int typeGhost = 8;
    final static int typeSteel = 9;
    final static int typeFire = 10;
    final static int typeWater = 11;
    final static int typeGrass = 12;
    final static int typeElectric = 13;
    final static int typePsychic = 14;
    final static int typeIce = 15;
    final static int typeDragon = 16;
    final static int typeDark = 17;
    final static int typeFairy = 18;
    final static Random rnd = new Random();
    final static int aRenderDis = 9;
    final static int bRenderDis = 9;
    final static byte[][] tilemap1={
            {0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,2}};
    final static double[][] typeChart = {
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
    final static int MOVE_CODE = 1;
    final static int SWITCH_CODE = 2;
    final static int VIEW_CODE = 3;
    final static int BAG_CODE = 4;
    static EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> standardFunction=(attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0;
}
