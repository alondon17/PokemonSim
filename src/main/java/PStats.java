public class PStats {
    private int hp;
    private  int Attack;
    private int Defence;
    private int SpAttack;
    private int SpDefence;
    private int Speed;
    public PStats(EV evs,IV ivs,Species species,int level,Nature nature){
        calcStats(evs, ivs, species, level,nature);
    }

    public void calcStats(EV evs, IV ivs, Species species, int level,Nature nature) {
        double[] natureEffect=nature.toArr();
        this.hp= (int) Math.floor((((species.baseHP()+ivs.HP())*2+Math.sqrt(evs.hp()))*level/100.0)+level+10);

        this.Attack= (int) Math.floor(((((species.baseAtk()+ivs.ATTACK())*2+Math.sqrt(evs.atk()))*level/100.0)+5)*natureEffect[0]);
        this.Defence= (int) Math.floor(((((species.baseDef()+ivs.DEFENCE())*2+Math.sqrt(evs.def()))*level/100.0)+5)*natureEffect[1]);
        this.SpAttack= (int) Math.floor(((((species.baseSpAtk()+ivs.SP_ATTACK())*2+Math.sqrt(evs.spAtk()))*level/100.0)+5)*natureEffect[3]);
        this.SpDefence= (int) Math.floor(((((species.baseSpDef()+ivs.SP_DEFENCE())*2+Math.sqrt(evs.spDef()))*level/100.0)+5)*natureEffect[4]);
        this.Speed= (int) Math.floor(((((species.baseSpeed()+ivs.SPEED())*2+Math.sqrt(evs.speed()))*level/100.0)+5)*natureEffect[2]);
    }

    public int getHp() {
        return hp;
    }

    public int getPhysAttack() {
        return Attack;
    }

    public int getPhysDef() {
        return Defence;
    }

    public int getSpAttack() {
        return SpAttack;
    }

    public int getSpDef() {
        return SpDefence;
    }

    public int getSpeed() {
        return Speed;
    }
}
