import java.util.TreeMap;

public class MoveList {
    private static TreeMap<Integer, Move> list = new TreeMap<>();

    static {
        Move move;
        move =new MoveNorm(1,1,"Pound",40,true,100,35);
        list.put(move.id(),move);
        move =new MoveNorm(2,2,"Karate Chop",50,true,100,25);
        list.put(move.id(),move);
        move =new MoveMultiHit(3,1,"Double Slap",15,true,85,10,0);
        list.put(move.id(),move);
        move =new MoveNorm(16,3,"Gust",40,false,100,35);
        list.put(move.id(),move);
        move =new MoveNorm(22,12,"Vine Whip",45,true,100,25);
        list.put(move.id(),move);
        move =new MoveMultiHit(24,2,"Double Kick",30,true,100,30,2);
        list.put(move.id(),move);
        move =new MoveNorm(33,1,"Tackle",40,true,100,35);
        list.put(move.id(),move);
        move =new MoveNorm(51,4,"Acid",40,false,100,30);
        list.put(move.id(),move);
        move =new MoveNorm(52,10,"Ember",40,false,100,25);
        list.put(move.id(),move);
        move =new MoveNorm(55,11,"Water Gun",40,false,100,25);
        list.put(move.id(),move);
        move =new MoveNorm(82,16,"Dragon Rage",0,false,100,10);
        list.put(move.id(),move);
        move =new Move(336,1,"Howl",40,0, 2,101,(byte)0,
                Target.targetUserAndAllies,MoveFunction.ATTACK1UP,100);
        list.put(move.id(),move);
    }
    public static Move getMove(int id){
        return list.get(id);
    }
}
