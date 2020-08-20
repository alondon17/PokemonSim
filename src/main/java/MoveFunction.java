//public class Effect {
//    private EffectType eCode;
//    private int eChance;
//    private int statusStage;
//    public Effect(EffectType eCode, int eChance, int statusStage){
//        this.eCode =eCode;
//        this.eChance = eChance;
//        this.statusStage=statusStage;
//    }
//    public Effect(EffectType eCode, int eChance){
//        this.eCode =eCode;
//        this.eChance = eChance;
//        this.statusStage=0;
//    }

//    public EffectType eCode() {
//        return eCode;
//    }
//}
enum MoveFunction {
    NO_EFFECT((a,b,c,d,e)->{d.printPkmn(b);return true;}),
    BRN((a,b,c,d,e)->a.isAlive()),
    FRZ((a,b,c,d,e)->!a.isAlive()),
    PAR((a,b,c,d,e)->!a.isAlive()),
    PSN((a,b,c,d,e)->!a.isAlive()),
    BPSN((a,b,c,d,e)->!a.isAlive()),
    SLP((a,b,c,d,e)->!a.isAlive());
    EffectApplicator<Pokemon,Pokemon[],TurnChoice,BattleManager,Move,Boolean> effectApplicator;
    MoveFunction(EffectApplicator<Pokemon,Pokemon[],TurnChoice,BattleManager,Move, Boolean> effectApplicator){
        this.effectApplicator=effectApplicator;
    }

    public EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> getMoveDoer() {
        return effectApplicator;
    }
}
