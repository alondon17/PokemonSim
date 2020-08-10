import java.sql.Array;

public class BaseStats {
    private int hp;
    private int physAttack;
    private int physDef;
    private int spAttack;
    private int spDef;
    private int speed;

    public BaseStats(int hp, int physAttack, int physDef, int spAttack, int spDef, int speed) {
        this.hp = hp;
        this.physAttack = physAttack;
        this.physDef = physDef;
        this.spAttack = spAttack;
        this.spDef = spDef;
        this.speed = speed;
    }
    public BaseStats(int[] array) {
        this.hp = array[0];
        this.physAttack = array[1];
        this.physDef = array[2];
        this.spAttack = array[3];
        this.spDef = array[4];
        this.speed = array[5];
    }

    public BaseStats(Array baseStats) {

    }

    public int[] arr() {
        return new int[]{hp,physAttack,physDef,spAttack,spDef,speed};
    }
    public int getHp() {
        return hp;
    }

    public int getPhysAttack() {
        return physAttack;
    }

    public int getPhysDef() {
        return physDef;
    }

    public int getSpAtk() {
        return spAttack;
    }

    public int getSpDef() {
        return spDef;
    }

    public int getSpeed() {
        return speed;
    }
}
