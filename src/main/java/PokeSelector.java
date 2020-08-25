import java.util.ArrayList;
import java.util.Scanner;

public class PokeSelector {
    final static int MAX_PKMN_IN_PARTY = 6;
    static Scanner scanner = new Scanner(System.in);


    public static Pokemon[] select() {
        ArrayList<Pokemon> arr = new ArrayList<>();
        SpeciesList.printList();
        int i = 1;
        System.out.println("Pokemon number " + i + ": Insert pokeID or negative to quit");
        int userChoice = Methoder.getInt();
        while (arr.size() < MAX_PKMN_IN_PARTY && userChoice > 0) {
            System.out.println("Choose pokemon's level");
            int level = Methoder.getInt();
            Pokemon pkmn = SpeciesList.getPoke(userChoice,level);
            while (pkmn == null) {
                System.out.println("Pokemon not in , try again");
                userChoice = Methoder.getInt();
                System.out.println("Choose pokemon's level");
                level = Methoder.getInt();
                pkmn = SpeciesList.getPoke(userChoice,level);
            }
            arr.add(pkmn);
            System.out.println(pkmn.printStats());
            System.out.println(pkmn.name() + " added!");
            i++;
            if (i <= MAX_PKMN_IN_PARTY) {
                System.out.println("Pokemon number " + i + ": Insert pokeID or negative to quit");
                userChoice = scanner.nextInt();
            }
        }
        if (arr.size() == 0) {
            arr.add(SpeciesList.getPoke(1));
            arr.add(SpeciesList.getPoke(4));
            arr.add(SpeciesList.getPoke(7));
        }
        System.out.println();
        return Methoder.listToArr(arr);
    }

    public static Pokemon[] random(int length) {
        if (length > 6) length = 6;
        Pokemon[] arr = new Pokemon[length];
        for (int i = 0; i < length; i++) {
            arr[i] = SpeciesList.randomPoke();
        }
        return arr;
    }
}
