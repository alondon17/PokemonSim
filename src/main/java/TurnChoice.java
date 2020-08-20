import java.util.function.BiFunction;

public class TurnChoice implements Comparable<TurnChoice> {
    public static final int NO_CHOICE_SET=0;
    private int baseOption;
    private int secondaryOption;
    private int targetloc;
    private Pokemon userPokemon;
    public TurnChoice(){}
    public void reset(){
    }

    @Override
    public int compareTo(TurnChoice o) {
        return 0;
    }
}
