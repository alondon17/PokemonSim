public class Move {
    private int id;
    private int type;
    private String name;
    private int pp;
    private int power;
    private boolean isPhys = false;
    private boolean isStatus = false;
    private int accuracy;
    private byte priority;
    private byte target;
    private MoveFunction function;
    private int effectChance;

    public Move(int id, int type, String name, int pp, int power, int PhysSpecStatCode, int accuracy, byte priority, byte target, MoveFunction function, int effectChance) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.pp = pp;
        this.power = power;
        switch (PhysSpecStatCode) {
            case 0:
                isPhys = true;
                break;
            case 1:
                break;
            case 2:
                isStatus = true;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + PhysSpecStatCode);
        }
        this.accuracy = accuracy;
        this.priority = priority;
        this.target = target;
        this.function = function;
        this.effectChance = effectChance;
    }

    public Move(int id, int type, String name, int pp, boolean isPhys, int accuracy) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.pp = pp;
        isPhys = isPhys;
        this.accuracy = accuracy;
        priority = 0;
        target = Target.singleNonUser;
        function = MoveFunction.NO_EFFECT;
    }

    public Move(int id, int type, String name, int pp, boolean isPhys, int accuracy, byte priority, MoveFunction function) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.pp = pp;
        isPhys = isPhys;
        this.accuracy = accuracy;
        this.priority = priority;
        this.function = function;
        target = Target.singleNonUser;
    }


    public int accuracy() {
        return accuracy;
    }

    public int id() {
        return id;
    }

    public boolean isPhys() {
        return isPhys;
    }

    public int type() {
        return type;
    }

    public String name() {
        return name;
    }

    public int pp() {
        return pp;
    }

    public int power() {
        return 0;
    }

    public byte priority() {
        return priority;
    }

    public boolean isRecoil() {
        return false;
    }

    public MoveFunction function() {
        return function;
    }

    public byte target() {
        return target;
    }

    public int effectChance() {
        return effectChance;
    }

    public boolean doesTargetIndividuals() {
        return switch (target) {
            case Target.bothSides, Target.opposeSide, Target.userSide -> false;
            default -> true;
        };
    }
}
