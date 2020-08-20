public class Move {
    private int id;
    private int type;
    private String name;
    private int pp;
    private boolean IsPhys;
    private int accuracy;
    private byte priority;
    private MoveFunction function=null ;

    public Move(int id, int type, String name, int pp, boolean isPhys, int accuracy) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.pp = pp;
        IsPhys = isPhys;
        this.accuracy = accuracy;
        priority=0;
        function=MoveFunction.NO_EFFECT;
    }

    public Move(int id, int type, String name, int pp, boolean isPhys, int accuracy, byte priority, MoveFunction function) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.pp = pp;
        IsPhys = isPhys;
        this.accuracy = accuracy;
        this.priority = priority;
        this.function=function;
    }

    public int accuracy() {
        return accuracy;
    }

    public int id() {
        return id;
    }

    public boolean isPhys() {
        return IsPhys;
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

}
