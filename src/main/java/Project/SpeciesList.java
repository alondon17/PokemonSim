package Project;

import java.sql.SQLException;
import java.util.*;

public class SpeciesList {
    private static final int DEFAULT_LEVEL = 5;
    private static final TreeMap<Integer, Species> list = new TreeMap<>();
    private static Random rnd = new Random();

    static {
        Species pkmn;
        pkmn = new Species(0, "Test", new BaseStats(49, 65, 49, 65, 65, 45), 64, new int[]{0, 0, 0, 1, 0, 0}, 12, 4, new int[]{1, 336});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(1, "Bulbasaur", new BaseStats(49, 65, 49, 65, 65, 45), 64, new int[]{0, 0, 0, 1, 0, 0}, 12, 4, new int[]{1, 45, 1, 33, 3, 22, 6, 74, 9, 73, 12, 75, 15, 77, 15, 79, 18, 402, 21, 36, 24, 230, 27, 235, 30, 388, 33, 38, 36, 76});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(2, "Ivysaur", new BaseStats(60,62,63,80,80,60),142, new int[]{0,0,0,1,1,0}, 12, 4, new int[]{1,33,1,22});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(3, "Venusaur", new BaseStats(80,82,83,100,100,80),236, new int[]{0,0,0,2,1,0}, 12, 4, new int[]{1,33,1,22});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(4, "Charmander", new BaseStats(39, 52, 43, 60, 50, 65), 62, new int[]{0, 0, 0, 0, 0, 1}, 10, 0, new Move[]{MoveList.getMove(52), MoveList.getMove(33), MoveList.getMove(51)});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(7, "Squirtle", new BaseStats(44, 48, 65, 50, 64, 43), 63, new int[]{0, 0, 1, 0, 0, 0}, 11, 0, new Move[]{MoveList.getMove(55), MoveList.getMove(33)});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(39, "Jigglypuff", new BaseStats(115, 45, 20, 45, 25, 20), 95, new int[]{2, 0, 0, 0, 0, 0}, 1, 18, new Move[]{MoveList.getMove(3), MoveList.getMove(1)});
        list.put(pkmn.id(), pkmn);
        pkmn = new Species(66, "Machop", new BaseStats(70, 80, 50, 35, 35, 35), 61, new int[]{0, 1, 0, 0, 0, 0}, 2, 0, new Move[]{MoveList.getMove(2), MoveList.getMove(24)});
        list.put(pkmn.id(), pkmn);
        try {
            insertDBSpecies();
        } catch (SQLException e) {
            System.out.println("Database connection failed, continueing with limited data");
        }
    }

    public static Species getSpecies(int id) {
        if (list.containsKey(id))
            return list.get(id);
        return null;
    }

    private static void insertDBSpecies() throws SQLException {
        for (Species species : DBManager.getSpecies()) {
            list.put(species.id(), species);
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
        return new Pokemon(getSpecies((int) (list.keySet().toArray()[rnd.nextInt(list.size())])), 5);
    }

    public static Pokemon getPoke(int userChoice, int level) {
        if (!legalLevel(level)) {
            System.out.println("Levels must be between 0 and 100");
            return null;
        }
        Species spec = getSpecies(userChoice);
        if (spec != null) {
            return new Pokemon(spec, level);
        }
        return null;
    }

    private static boolean legalLevel(int level) {
        return level>=0&&level<=100;
    }

    public static Pokemon getPoke(int userChoice) {
        return getPoke(userChoice, DEFAULT_LEVEL);
    }
    public static void DBtoInitializerCode() throws SQLException {
            for (Species species : DBManager.getSpecies()) {
                System.out.println(species.toCode());
            }
    }
}

