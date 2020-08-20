import java.util.HashMap;

public class Pokemon implements Comparable<Pokemon> {
    private Species species;
    private int level;
    private int exp;
    private PStats stats;
    private Nature nature = new Nature(Methoder.random(25));
    private EV evs;
    private final IV ivs = new IV();
    private int currHp;
    private byte statusCondition = 0;
    private String nickname;
    private MoveSet moveSet;
    private final StatChanges statChanges = new StatChanges();
    //private final HashMap< EffectType,Effect> effects=new HashMap<>();

    public Pokemon(Species species, int level, Move[] moves) {
        this.species = species;
        this.moveSet = new MoveSet(moves);
        this.level = level;
        this.exp = species.levelToExp(level);
        this.evs = new EV();
        calcStats();
        currHp = pkmnHp();

    }

    public Pokemon(Species species, int level) {
        this.species = species;
        this.moveSet = new MoveSet(species, level);
        this.level = level;
        this.exp = species.levelToExp(level);

        this.evs = new EV();
        calcStats();
        currHp = pkmnHp();

    }

    public void calcStats() {
        if (stats == null) {
            stats = new PStats(evs, ivs, species, level, nature);
        }
        stats.calcStats(evs, ivs, species, level, nature);

    }

    public boolean isAlive() {
        return this.currHp() > 0;

    }

    public void printVisualHP() {
        int i = 0;
        int stop = (int) Math.ceil(((double) currHp / pkmnHp()) * Consts.HP_LENGTH_PRINT);
        System.out.print("*");
        for (; i < stop; i++)
            System.out.print((char) 9632);
        for (; i < Consts.HP_LENGTH_PRINT; i++)
            System.out.print((char) 9633);
        System.out.print("*");
        System.out.println();

    }

    public String statusCondToText() {
        switch (statusCondition) {
            case Consts.stsBurn:
                return "Burned";
            case Consts.stsFreeze:
                return "Frozen";
            case Consts.stsParalysis:
                return "Paralysed";
            case Consts.stsPoison:
                return "Poisoned";
            case Consts.stsSleep:
                return "Sleeping";
            default:
                return "";
        }
    }

    public String printPkmn() {
        String output = "";
        output += name() + " Type: " + typeToString(type1());
        if (type2() != 0)
            output += ", " + typeToString(type2());
        return output;
    }

    public boolean existsMoveInPkmn(int index) {
        return !(index < 0 || index > moveSet.length() - 1);
    }

    //<editor-fold desc="*** Getters and setters ***">
    public int id() {
        return species.id();
    }

    public int level() {
        return level;
    }

    private void setLevel(int m_nLevel) {
        this.level = m_nLevel;
    }

    // base
    public int baseSpAttack() {
        return stats.getSpAttack();
    }

    public int basePhysDef() {
        return stats.getPhysDef();
    }

    public int baseSpDef() {
        return stats.getSpDef();
    }

    public int basePhysAttack() {
        return stats.getPhysAttack();
    }

    public int baseSpeed() {
        return stats.getSpeed();
    }

    // battle
    public int spAttack() {
        return (int) (baseSpAttack() * statChanges.spatkMod());
    }

    public int physDef() {
        return (int) (basePhysDef() * statChanges.defMod());
    }

    public int spDef() {
        return (int) (baseSpDef() * statChanges.spdefMod());
    }

    public int physAttack() {
        return (int) (basePhysAttack() * statChanges.atkMod());
    }

    public int speed() {
        return (int) (baseSpeed() * statChanges.speMod());
    }

    public double accuracyMod() {
        return statChanges.accMod();
    }

    public double evasionMod() {
        return statChanges.evaMod();
    }


    public int currHp() {
        return currHp;
    }


    protected void setCurrHp(int m_nHitPoints) {
        this.currHp = m_nHitPoints;
    }

    public int pkmnHp() {
        return this.stats.getHp();
    }

    public String name() {
        return nickname == null ? species.name() : nickname;
    }

    public void setName(String m_strName) {
        this.nickname = m_strName;
    }

    public int type1() {
        return species.type1();
    }

    public int type2() {
        return species.type2();
    }

    public int statCond() {
        return statusCondition;
    }

    public void setStatusCondition(byte statusCondition) {
        this.statusCondition = statusCondition;

    }

    public void startOrEndBattleReset(){
        statChanges.resetChanges();
    }
    public Move[] arrMoves() {
        return moveSet.arrMoves();
    }

    public MoveSet moveset() {
        return moveSet;
    }

    public Move move(int index) {
        if (moveSet.length() <= index)
            return null;

        return moveSet.get(index);
    }
    //private HashMap<EffectType,Effect> effects(){return effects;};
    //</editor-fold>

    public void battleStartReset(){
    //    EffectManager.battleStartReset(effects);
    }

    public static String typeToString(int type) {
        switch (type) {
            case Consts.typeNormal:
                return "Normal";
            case Consts.typeFight:
                return "Fighting";
            case Consts.typeFlying:
                return "Flying";
            case Consts.typePoison:
                return "Poison";
            case Consts.typeGround:
                return "Ground";
            case Consts.typeRock:
                return "Rock";
            case Consts.typeBug:
                return "Bug";
            case Consts.typeGhost:
                return "Ghost";
            case Consts.typeSteel:
                return "Steel";
            case Consts.typeFire:
                return "Fire";
            case Consts.typeWater:
                return "Water";
            case Consts.typeGrass:
                return "Grass";
            case Consts.typeElectric:
                return "Electric";
            case Consts.typePsychic:
                return "Psychic";
            case Consts.typeIce:
                return "Ice";
            case Consts.typeDragon:
                return "Dragon";
            case Consts.typeDark:
                return "Dark";
            case Consts.typeFairy:
                return "Fairy";
        }
        return "Error";
    }

    @Override
    public int compareTo(Pokemon o) {
        return id() - o.id();
    }

    public void battlePrint() {
        System.out.println(this.name() + " " + this.currHp() + "HP " + this.statusCondToText());
        this.printVisualHP();
    }

    public String printStats() {
        this.calcStats();
            return "HP: " + stats.getHp() + "    Attack: " + stats.getPhysAttack() + "    Defence: " + stats.getPhysDef() +
                "\nSpecial Attack: " + stats.getSpDef() + "    Special Defence: " + stats.getSpDef() + "    Speed: " + stats.getSpeed()
                +"\nNature: "+nature.toString()+"    Moves: "+moveset().moveSetString();
    }
}
