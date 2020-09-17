package Project.Battles.PokemonData.Moves;//public class Effect {
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

import Project.Battles.AtackMngr;
import Project.Battles.BattleManager;
import Project.Battles.PokemonData.Pokemon;
import Project.SystemStuff.Consts;
import Project.Battles.TurnChoice;

//    public EffectType eCode() {
//        return eCode;
//    }
//}
public enum MoveFunction {
//TODO This is the additional eefect idiot
    BPSN((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    BRN((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    FRZ((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    NO_EFFECT(Consts.standardFunction),
    PAR((a, b, c, d, e) -> !a.isAlive()),
    PSN((a, b, c, d, e) -> !a.isAlive()),
    SLP((a, b, c, d, e) -> !a.isAlive()),
    ATTACK1UP((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0),
    FIXED40(Consts.standardFunction),
    HIT2NO_EFFECT((attacker, pokemons, turnChoice, battleManager, move) -> AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)+AtackMngr.receiveHit(pokemons[turnChoice.getTargetloc()],attacker,move,turnChoice)>0);


    EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> effectApplicator;

    MoveFunction(EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> effectApplicator) {
        this.effectApplicator = effectApplicator;
    }

    public EffectApplicator<Pokemon, Pokemon[], TurnChoice, BattleManager, Move, Boolean> getMoveDoer() {
        return effectApplicator;
    }
}
