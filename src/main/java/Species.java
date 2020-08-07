import java.lang.reflect.Array;
import java.util.*;

public class Species {
    private int specID;
    private String specName;
    private BaseStats baseStats;
    private final int expYield;
    private final int[] ivYield;
    private int type1;
    private int type2;
    ///TODO: evolve method
    ///TODO exp decider: exp group and exp to level calculator
    private final TreeMap<Integer, Move[]> learnSet = new TreeMap<>();

    public Species(int specID, String specName, BaseStats baseStats, int expYield, int[] ivYield, int type1, int type2, Move[] moves) {
        this.specID = specID;
        this.specName = specName;
        this.baseStats = baseStats;
        this.expYield = expYield;
        this.type1 = type1;
        this.type2 = type2;
        this.ivYield = ivYield;
        if (moves != null)
            learnSet.put(0, moves);


    }

    public Species(int specID, String specName, BaseStats baseStats, int expYield, int type1, int type2, Move[] moves) {
        this(specID, specName, baseStats, expYield, new int[]{0, 0, 0, 0, 0, 0}, type1, type2, moves);

    }

    public Species(int specID, String specName, BaseStats baseStats, int expYield, int[] ivYield, int type1, int type2, int[] learnSet) {
        this.specID = specID;
        this.specName = specName;
        this.baseStats = baseStats;
        this.expYield = expYield;
        this.type1 = type1;
        this.type2 = type2;
        this.ivYield = ivYield;
        toLearnSet(learnSet);

    }

    private void toLearnSet(int[] learnSet) {
        if (learnSet == null)
            throw new InputMismatchException("null learnset given");
        if (learnSet.length % 2 == 1)
            throw new InputMismatchException("illegal number of learnset length: non even number");
        int index = 0;
        ArrayList<Move> sameLevelMoves = new ArrayList<>();
        int currLevel = learnSet[0];

        while (index < learnSet.length) {
            if (learnSet[index] != currLevel) {
                if (sameLevelMoves.size() > 0)
                    this.learnSet.put(currLevel, sameLevelMoves.toArray(new Move[0]));
                sameLevelMoves.clear();
                currLevel = learnSet[index];
            }
            Move move = MoveList.getMove(learnSet[index + 1]);
            if (move != null)
                sameLevelMoves.add(move);
            index += 2;
        }
        if (sameLevelMoves.size() > 0)
            this.learnSet.put(currLevel, sameLevelMoves.toArray(new Move[0]));
    }

    public Move[] getMoves(int level) {
        if (learnSet.containsKey(level))
            return learnSet.get(level);
        return null;
    }

    public int id() {
        return specID;
    }

    public String name() {
        return specName;
    }

    public BaseStats baseStats() {
        return baseStats;
    }

    public int type1() {
        return type1;
    }

    public int type2() {
        return type2;
    }

    public int levelToExp(int level) {
        //TODO : complete method
        return 0;
    }

    public String printSpecies() {
        return name() + " Type: " + Pokemon.typeToString(type1) + (type2 == 0 ? "" : ", " + Pokemon.typeToString(type2));
    }

    public int baseHP() {
        return baseStats().getHp();
    }

    public int baseAtk() {
        return baseStats().getPhysAttack();
    }

    public int baseDef() {
        return baseStats().getPhysDef();
    }

    public int baseSpAtk() {
        return baseStats().getSpAtk();
    }

    public int baseSpDef() {
        return baseStats().getSpDef();
    }

    public int baseSpeed() {
        return baseStats().getSpeed();
    }

    public TreeMap<Integer, Move[]> getLearnSet() {
        return (TreeMap<Integer, Move[]>) learnSet.clone();
    }
}
