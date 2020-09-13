package Project;

public class IV {
    private final byte HP;
    private final byte ATTACK;
    private final byte DEFENCE;
    private final byte SP_ATTACK;
    private final byte SP_DEFENCE;
    private final byte SPEED;

    public IV() {
        this.HP=(byte)Methoder.random(32);
        this.ATTACK=(byte)Methoder.random(32);
        this.DEFENCE=(byte)Methoder.random(32);
        this.SP_ATTACK=(byte)Methoder.random(32);
        this.SP_DEFENCE=(byte)Methoder.random(32);
        this.SPEED=(byte)Methoder.random(32);
    }

    public byte HP() {
        return HP;
    }

    public byte ATTACK() {
        return ATTACK;
    }

    public byte DEFENCE() {
        return DEFENCE;
    }

    public byte SP_ATTACK() {
        return SP_ATTACK;
    }

    public byte SP_DEFENCE() {
        return SP_DEFENCE;
    }

    public byte SPEED() {
        return SPEED;
    }
}
