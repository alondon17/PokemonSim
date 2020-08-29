import java.util.Random;

public class AtackMngr {
    final static Random rnd = new Random();
    private static BattleManager battleManager;

    public static void initialize(BattleManager battleManager){

    }
    public static int attack(Pokemon attacker, Pokemon[] attacked, TurnChoice turnChoice) {
        int thisHit = 0;
        int damage = 0;
        boolean hasHit=false;
        Move move = turnChoice.getMove();
//        if (attacker != attacked) {
        System.out.println(attacker.name() + " used " + move.name());
        if (move.doesTargetIndividuals())
            for (Pokemon target : getTargets(attacked, turnChoice))
                if (checkIfMoveHits(target, attacker, turnChoice))
                    if (move.function().getMoveDoer().apply(attacker, PokeMain.battleManager.getpOut(), turnChoice, PokeMain.battleManager, move))
                        hasHit=true;
//            if (move instanceof MoveMultiHit) {
//                thisHit = multiHitAttack(attacked, attacker, (MoveMultiHit) move);
//            } else if (move instanceof MoveNorm) {
//                thisHit = normalAttack(attacked, attacker, move);
//
//            }

        damage += thisHit;

//        }
        if (!hasHit) System.out.println("Move missed fic this");
        return damage;
    }

    private static Pokemon[] getTargets(Pokemon[] attacked, TurnChoice turnChoice) {
        switch (turnChoice.getMove().target()) {
            case Target.target1NonUser:return new Pokemon[]{attacked[turnChoice.getTargetloc()]};
            //TODO make case
  //          case Target.targetUserAndAllies:return new Pokemon[]{Arrays.copyOfRange(attacked,)]};
            default:throw new IllegalArgumentException("Not programed yet oopse");
        }
    }

    private static int normalAttack(Pokemon attacked, Pokemon attacker, Move move, TurnChoice turnChoice) {
        return receiveHit(attacked, attacker, move,turnChoice);
    }

    private static int multiHitAttack(Pokemon attacked, Pokemon attacker, MoveMultiHit move, TurnChoice turnChoice) {
        int numOfTimes = move.getNumOfHits();
        int totalDamage = 0;
        int thisHit;
        int j = 0;
        for (int i = 0; i < numOfTimes && attacked.isAlive(); i++) {
            thisHit = normalAttack(attacked, attacker, move, turnChoice);
            totalDamage += thisHit;
            if (thisHit > 0) j++;
            if (i != numOfTimes - 1) {
                attacked.battlePrint();
                System.out.println();
            }

        }
        System.out.println("This move hit " + j + " times!");
        return totalDamage;
    }


    public static int receiveHit(Pokemon attacked, Pokemon attacker, Move move, TurnChoice turnChoice) {
        int damage = 0;
        if (checkIfMoveHits(attacked, attacker, turnChoice)) {

            damage = damageCalc(attacked, attacker, move);

            attacked.setCurrHp(attacked.currHp() - damage);
            if (attacked.currHp() < 0)
                attacked.setCurrHp(0);

        }
        return damage;
    }

    private static boolean checkIfMoveHits(Pokemon attacked, Pokemon attacker, TurnChoice turnChoice) {
        /// TODO text for paralize and flinch here
        Move move = turnChoice.getMove();
        if (move.accuracy() > 100)
            return true;
        if (rnd.nextInt(100) <= move.accuracy() * accuracyChanges(attacked, attacker))
            return true;
        System.out.println(move.name() + " missed");
        return false;
    }

    private static double accuracyChanges(Pokemon attacked, Pokemon attacker) {
        return attacker.accuracyMod() / attacked.evasionMod();
    }

    private static int damageCalc(Pokemon attacked, Pokemon attacker, Move move) {
        double damage = 0;
        switch (move.function()) {
            //dragon rage
            case FIXED40:
                return 40;
            default:
                int power=switch (move.function()){
                    default -> move.power();
                };
                double modifier = STAB(attacker, move) * typeAdv(attacked, move);
                damage = ((2 * attacker.level() + 10) / 250.0);
                damage *= (move.isPhys() ? (double) attacker.physAttack() / attacked.physDef() : (double) attacker.spAttack() / attacked.spDef());
                damage = damage * power + 2;
                damage *= modifier;

        }
        return (int) damage;
    }

    private static double STAB(Pokemon attacker, Move move) {
        return move.type() == attacker.type1() || move.type() == attacker.type2() ? 1.5 : 1;

    }

    private static double typeAdv(Pokemon attacked, Move move) {
        double multiplier = 1;
        multiplier *= Consts.typeChart[move.type() - 1][attacked.type1() - 1];
        if (attacked.type2() != 0)
            multiplier *= Consts.typeChart[move.type() - 1][attacked.type2() - 1];
        if (multiplier > 1)
            System.out.println("It's super effective");
        else if (multiplier == 0)
            System.out.println("It doesn't affect " + attacked.name());
        else if (multiplier < 1)
            System.out.println("It's not very effective");
        return multiplier;

    }

}
