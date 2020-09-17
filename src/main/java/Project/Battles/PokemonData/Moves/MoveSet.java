package Project.Battles.PokemonData.Moves;

import Project.Battles.PokemonData.Species;

import java.util.*;

public class MoveSet {
    private static Move struggle = Struggle.getStruggle();

    private Move[] moveSet = new Move[4];
    private int[] ppSet = new int[4];
    private byte numOfMoves;

    public MoveSet(Move[] moveset) {
        for (Move move : moveset) {
            if (move != null) {
                this.moveSet[numOfMoves] = move;
                this.ppSet[numOfMoves] = move.pp();
                numOfMoves++;
                if (numOfMoves >= 4)
                    break;
            }
        }
    }

    public MoveSet(int[] indexes) {
        for (int index : indexes) {
            if (index > 0) {
                this.moveSet[numOfMoves] = MoveList.getMove(index);
                this.ppSet[numOfMoves] = this.moveSet[numOfMoves].pp();
                this.numOfMoves++;
                if (numOfMoves >= 4)
                    break;
            }
        }
    }

    public MoveSet(MoveSet moveSet) {
        this(moveSet.moveSet.clone());
    }

    public MoveSet(Species species, int level) {
        TreeMap<Integer, Move[]> learnset = species.getLearnSet();
        for (int i = 0; i <= level; i++) {
            if (learnset.containsKey(i)) {
                for (Move move : learnset.get(i)) {
                    tryLearn(move);
                }
            }
        }
    }

    public void restorePP(int moveIndex) throws IndexOutOfBoundsException {
        if (moveIndex < this.numOfMoves) {
            ppSet[moveIndex] = moveSet[moveIndex].pp();
        } else throw new IndexOutOfBoundsException("Restore PP to not existent place");
    }

    public void restoreAllPP() {
        for (int i = 0; i < numOfMoves; i++) {
            ppSet[i] = moveSet[i].pp();
        }
    }

    public boolean isStruggle() {
        for (int i = 0; i < numOfMoves; i++) {
            if (ppSet[i] > 0) {
                return false;
            }
        }
        return true;
        //TODO program checks of pp and disablers
    }

    public void printMoveSet() {
        for (int i = 0; i < numOfMoves; i++) {
            System.out.println((i + 1) + ": " + moveSet[i].name() + "\t" + ppSet[i] + "/" + moveSet[i].pp() + "PP");
        }
    }

    public byte length() {
        return numOfMoves;
    }

    public Move get(int index) throws IndexOutOfBoundsException {
        if (0 <= index && index < numOfMoves) {
            ppSet[index]--;
            return moveSet[index];
        }
        if (index == 5)
            return struggle;
        throw new IndexOutOfBoundsException("Requested move in illegal index");
    }

    public Move[] arrMoves() {
        return moveSet;
    }

    public void tryLearn(Move move) {
        //Todo application in trying when full moveset in class Pokemon
        if (numOfMoves != 4) {
            moveSet[numOfMoves] = move;
            ppSet[numOfMoves] = move.pp();
            numOfMoves++;
        }
    }
    public String moveSetString(){
        String s="";
        for (int i = 0; i < numOfMoves; i++) {
            s+=(i + 1) + ": " + moveSet[i].name() + "  " + ppSet[i] + "/" + moveSet[i].pp() + "PP   ";
        }
        return s;
    }
}
