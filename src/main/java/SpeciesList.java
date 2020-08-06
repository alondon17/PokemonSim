import java.util.*;

public class SpeciesList {
    private static final int DEFAULT_LEVEL =5;
    private static final TreeMap<Integer, Species> list = new TreeMap<>();
    public static MoveList mList = new MoveList();
    private static Random rnd = new Random();

    static {
        Species pkmn;
        pkmn = new Species(1, "Bulbasaur", new BaseStats(49, 65, 49, 65, 65, 45), 64, 12, 4, new Move[]{MoveList.getMove(22), MoveList.getMove(33), MoveList.getMove(51)});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(4, "Charmander", new BaseStats(39, 52, 43, 60, 50, 65), 62, 10, 0, new Move[]{MoveList.getMove(52), MoveList.getMove(33), MoveList.getMove(51)});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(7, "Squirtle", new BaseStats(44, 48, 65, 50, 64, 43), 63, 11, 0, new Move[]{MoveList.getMove(55), MoveList.getMove(33)});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(39, "Jigglypuff",new BaseStats(115,45,20,45,25,20),95, 1, 18, new Move[]{MoveList.getMove(3), MoveList.getMove(1)});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(66, "Machop", new BaseStats(70,80,50,35,35,35),61, 2, 0, new Move[]{MoveList.getMove(2), MoveList.getMove(24)});
        list.put(pkmn.id(), pkmn);
        insertDBSpecies();
    }

    public static Species getSpecies(int id) {
        if (list.containsKey(id))
            return list.get(id);
        return null;
    }
    private static void insertDBSpecies() {
        for (Species species:DBManager.getSpecies()) {
            list.put(species.id(),species);
        }
    }

    public static void printList() {
        System.out.println("List of Pokemons\n");
        for (Species pkmn : list.values()) {
            System.out.println(pkmn.id() + ": " + pkmn.printSpecies());
        }
        System.out.println();
    }

    public static Pokemon randomPoke() {
        return new Pokemon(getSpecies((int) (list.keySet().toArray()[rnd.nextInt(list.size())])),5);
    }

    public static Pokemon getPoke(int userChoice,int level) {
        Species spec=getSpecies(userChoice);
        if(spec!=null){
            return new Pokemon(spec, level);
        }
        return null;
    }
    public static Pokemon getPoke(int userChoice) {
        return getPoke(userChoice,DEFAULT_LEVEL);
    }
}

