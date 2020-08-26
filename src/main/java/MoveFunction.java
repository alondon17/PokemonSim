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
    BPSN((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    BRN((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    FRZ((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    NO_EFFECT((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    PAR((a, b, c, d, e) -> !a.isAlive()),
    PSN((a, b, c, d, e) -> !a.isAlive()),
    SLP((a, b, c, d, e) -> !a.isAlive()),
    ATTACK1UP((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0);
    EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> effectApplicator;

    MoveFunction(EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> effectApplicator) {
        this.effectApplicator = effectApplicator;
    }

    public EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> getMoveDoer() {
        return effectApplicator;
    }
}
