import java.util.Random;

public class AtackMngr {
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
    final static Random rnd = new Random();

    public static int attack(Pokemon attacked, Pokemon attacker, Move move) {
        int thisHit = 0;
        int damage = 0;
        if (attacker != attacked) {
            System.out.println(attacker.name() + " used " + move.name() + " on " + attacked.name());
            if (move instanceof MoveMultiHit) {
                thisHit = multiHitAttack(attacked, attacker, (MoveMultiHit) move);
            } else if (move instanceof MoveNorm) {
                thisHit = normalAttack(attacked, attacker, move);

            }

            damage += thisHit;

        }
        return damage;
    }

    private static int normalAttack(Pokemon attacked, Pokemon attacker, Move move) {
        return receiveHit(attacked, attacker, move);
    }

    private static int multiHitAttack(Pokemon attacked, Pokemon attacker, MoveMultiHit move) {
        int numOfTimes = move.getNumOfHits();
        int totalDamage = 0;
        int thisHit;
        int j = 0;
        for (int i = 0; i < numOfTimes && attacked.isAlive(); i++) {
            thisHit = normalAttack(attacked, attacker, move);
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


    private static int receiveHit(Pokemon attacked, Pokemon attacker, Move move) {
        int damage = 0;
        if (checkIfMoveHits(attacked, attacker, move)) {

            damage = damageCalc(attacked, attacker, move);

            attacked.setCurrHp(attacked.currHp() - damage);
            if (attacked.currHp() < 0)
                attacked.setCurrHp(0);

        }
        return damage;
    }

    private static boolean checkIfMoveHits(Pokemon attacked, Pokemon attacker, Move move) {
        /// TODO text for paralize and flinch here
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
        double damage=0;
        switch (move.id()) {
            //dragon rage
            case 82:
                break;
            default:
                double modifier = STAB(attacker, move) * typeAdv(attacked, move);
                damage = ((2 * attacker.level() + 10) / 250.0);
                damage *= (move.isPhys() ? (double) attacker.physAttack() / attacked.physDef() : (double) attacker.spAttack() / attacked.spDef());
                damage = damage * move.power() + 2;
                damage *= modifier;

        }
        return (int) damage;
    }

    private static double STAB(Pokemon attacker, Move move) {
        return move.type() == attacker.type1() || move.type() == attacker.type2() ? 1.5 : 1;

    }

    private static double typeAdv(Pokemon attacked, Move move) {
        double multiplier = 1;
        multiplier *= typeChart[move.type() - 1][attacked.type1() - 1];
        if (attacked.type2() != 0)
            multiplier *= typeChart[move.type() - 1][attacked.type2() - 1];
        if (multiplier > 1)
            System.out.println("It's super effective");
        else if (multiplier == 0)
            System.out.println("It doesn't affect " + attacked.name());
        else if (multiplier < 1)
            System.out.println("It's not very effective");
        return multiplier;

    }

}
