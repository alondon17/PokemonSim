package Project;

import Project.SystemStuff.Consts;

public class TurnChoice implements Comparable<TurnChoice> {
    public static final int NO_CHOICE_SET = 0 ;
    private int baseOption;
    private int secondaryOption;
    private int targetloc;
    private Pokemon userPokemon;
    private int userLoc;

    public TurnChoice(int baseOption, int secondaryOption, int targetloc, Pokemon userPokemon, int userLoc) {
        this.baseOption = baseOption;
        this.secondaryOption = secondaryOption;
        this.targetloc = targetloc;
        this.userPokemon = userPokemon;
        this.userLoc = userLoc;
    }

    public TurnChoice() {
    }

    public void reset() {
    }

    @Override
    public int compareTo(TurnChoice o) {
        if (this.baseOption == Consts.SWITCH_CODE)
            return Consts.compareToVal;
        if (o.baseOption == Consts.SWITCH_CODE)
            return -Consts.compareToVal;

        return 0;
    }

    public int getBaseOption() {
        return baseOption;
    }

    public int getSecondaryOption() {
        return secondaryOption;
    }

    public int getTargetloc() {
        return targetloc;
    }

    public Pokemon getUserPokemon() {
        return userPokemon;
    }

    public int getUserLoc() {
        return userLoc;
    }
    public Move getMove(){
        if(getBaseOption()==Consts.MOVE_CODE)
        return getUserPokemon().arrMoves()[getSecondaryOption()];
        return null;
    }
}
