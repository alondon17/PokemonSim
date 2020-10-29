package Project.Battles.PokemonData.Moves;

import java.util.TreeMap;

public class MoveList {
    private static TreeMap<Integer, Move> list = new TreeMap<>();

    static {
        //TODO: FIZ ALL TO MOVE CONSTRUCTOR FORMAT
        Move move;
        move =new MoveNorm(1,1,"Pound",40,true,100,35);
        list.put(move.id(),move);
        move =new Move(2,2,"Karate Chop",25,50,0,100,0,Target.singleNonUser,MoveFunction.NO_EFFECT,100);
        list.put(move.id(),move);
        move =new MoveMultiHit(3,1,"Double Slap",15,true,85,10,0);
        list.put(move.id(),move);
        move =new MoveNorm(16,3,"Gust",40,false,100,35);
        list.put(move.id(),move);
        move =new MoveNorm(22,12,"Vine Whip",45,true,100,25);
        list.put(move.id(),move);
        move =new Move(24,2,"Double Kick",30,30,0,100,0,Target.singleNonUser,MoveFunction.HIT2NO_EFFECT,100);
        list.put(move.id(),move);
        move =new MoveNorm(33,1,"Tackle",40,true,100,35);
        list.put(move.id(),move);
        move =new MoveNorm(51,4,"Acid",40,false,100,30);
        list.put(move.id(),move);
        move =new MoveNorm(52,10,"Ember",40,false,100,25);
        list.put(move.id(),move);
        move =new Move(55,11,"Water Gun",25,40,1,100,0,Target.singleNonUser,MoveFunction.NO_EFFECT,100);
        list.put(move.id(),move);
        move =new Move(82,16,"Dragon Rage",10,0,1,100,(byte)0,Target.singleNonUser,MoveFunction.FIXED40,100);
        list.put(move.id(),move);
        move =new Move(336,1,"Howl",40,0, 2,101,(byte)0,
                Target.userAndAllies,MoveFunction.ATTACK1UP,100);
        list.put(move.id(),move);
    }
    public static Move getMove(int id){
        return list.get(id);
    }
}
