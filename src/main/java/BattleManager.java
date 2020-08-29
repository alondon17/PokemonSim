import java.util.*;

public class BattleManager {
    Random rnd = new Random();
    private int p0Alive;
    private int p1Alive;
    private boolean secondHasFainted;
    private boolean isP0First;
    private final int[] pChoice = new int[2];
    private final int[] recoil = {0, 0};
    private final int[] pSwitchTo = new int[2];
    private final Move[] pMove = new Move[2];
    // array as p0's pkmns and then p1's
    //TODO fix change in pOuts implementation across the code
    private final Pokemon[] pOut;
    private final Trainer[] trainer = new Trainer[2];
    private final Pokemon[][] player = new Pokemon[2][];
    private final int numOfPokemonOnField;
    private final TurnOrderer turnOrderer;

    public BattleManager(Trainer trainer0, Trainer trainer1, int numOfPokemonOnField) {
        this.numOfPokemonOnField = numOfPokemonOnField;
        turnOrderer = new TurnOrderer(numOfPokemonOnField);
        pOut = new Pokemon[2 * numOfPokemonOnField];
        trainer[0] = trainer0;
        trainer[1] = trainer1;
        this.player[0] = trainer0.getPokemons();
        this.player[1] = trainer1.getPokemons();
        if (player[0].length == 0 || player[1].length == 0) {
            System.out.println("Error, empty pokemon team");
            return;
        }
        for (int i = 0; i < player[0].length; i++) {
            p0Alive++;
            player[0][i].battleStartReset();

        }
        for (int i = 0; i < player[1].length; i++) {
            p1Alive++;
            player[1][i].battleStartReset();

        }

    }

    private void endBattle() {
    }


    public void startBattle() {

        for (int i = 0; i < numOfPokemonOnField; i++)
            if (player[0].length > i && player[0][i] != null)
                pOut[i] = player[0][i];
        for (int i = 0; i < numOfPokemonOnField; i++)
            if (player[1].length > i && player[1][i] != null)
                pOut[numOfPokemonOnField + i] = player[1][i];

        while (p0Alive > 0 && p1Alive > 0) {
            secondHasFainted = false;
            pChooseMove(0);
            pChooseMove(1);
            while (turnOrderer.size() > 0) {
                pTurn(turnOrderer.getChoice());
            }

            //TODO fix bug: pokemon switched in still does fainted pokemon's move

            endOfTurn();

        }
        endBattle();
    }

    //TODO implement this
    private void doChoice(TurnChoice turnChoice) {
    }

    //TODO implement this
    private boolean isPossible(TurnChoice turnChoice) {
        return true;
    }

    //TODO implement
    public void endOfTurn() {
        //TODO implement switch pokemno correctly
    }

    public void pChooseMove(int player) {
        for (int i = 0; i < numOfPokemonOnField; i++)
            if (canChooseMove(player * numOfPokemonOnField + i)) {
                boolean exitMenu = false;
                System.out.println(trainer[player].getName() + ", you have " + pOut[player * numOfPokemonOnField + i].name() + " out, choose your move");

                while (!exitMenu) {
                    printMenu();
                    pChoice[player] = Methoder.getInt();
                    switch (pChoice[player]) {
                        case Consts.MOVE_CODE:

                            exitMenu = chooseMovePkmn(player * numOfPokemonOnField + i);
                            if (!exitMenu) System.out.println("Wrong code, back to menu");
                            break;
                        case Consts.SWITCH_CODE:
                            exitMenu = chooseSwitchPkmn(player * numOfPokemonOnField + i);
                            if (!exitMenu) System.out.println("Wrong code, back to menu");
                            break;
                        case Consts.VIEW_CODE:
                            ///TODO code for viewing pokemon or combing with switch function
                            break;
                        case Consts.BAG_CODE:
                            ///TODO code for using bag
                            break;
                    }
                }
            }
    }

    private boolean canChooseMove(int pokeIndex) {
        return pOut[pokeIndex] != null;
    }

    private boolean chooseMovePkmn(int pokeIndex) {
        int moveChoice;
        int targetloc = -1;
        if (pOut[pokeIndex].moveset().isStruggle()) {
            turnOrderer.put(new TurnChoice(Consts.MOVE_CODE, 5, getAnOpposing(pokeIndex), pOut[pokeIndex], pokeIndex));
            return true;
        }
        pOut[pokeIndex].moveset().printMoveSet();
        moveChoice = Methoder.getInt();
        if (!pOut[pokeIndex].existsMoveInPkmn(moveChoice - 1)) {
            return false;
        }
        if(isOnly1Target(targetloc, pokeIndex, moveChoice)){
            printOppPokemon(pokeIndex / numOfPokemonOnField);
            while (!targetLegal(targetloc, pokeIndex, moveChoice)) {
                targetloc = Methoder.getInt();
            }
        }
        turnOrderer.put(new TurnChoice(Consts.MOVE_CODE, moveChoice - 1, targetloc, pOut[pokeIndex], pokeIndex));
        return true;
    }

    //TODO create method
    private boolean isOnly1Target(int targetloc, int pokeIndex, int moveChoice) {
        return false;
    }

    //TODO create method
    private boolean targetLegal(int targetloc,int pokeIndex,int moveChoice) {
        return true;
    }

    private void printOppPokemon(int i) {
        System.out.println("choose Pokemon to target");
        System.out.println("P0" + (i / numOfPokemonOnField == 0 ? " User's Side" : " Opponent's Side"));
        for (int j = 0; j < numOfPokemonOnField; j++) {
            System.out.println(j + ": " + pOut[j].name() + (i == j ? "SELF" : ""));
        }
        System.out.println("P1" + (i / numOfPokemonOnField == 1 ? " User's Side" : " Opponent's Side"));
        for (int j = numOfPokemonOnField; j < pOut.length; j++) {
            System.out.println(j + ": " + pOut[j].name() + (i == j ? " SELF" : ""));
        }
    }

    private int getAnOpposing(int pokeIndex) {
        for (int i = pokeIndex >= numOfPokemonOnField ? 0 : numOfPokemonOnField; i < (pokeIndex >= numOfPokemonOnField ? numOfPokemonOnField : 2 * numOfPokemonOnField); i++) {
            if (pOut[i].isAlive())
                return i;
        }
        throw new IndexOutOfBoundsException("Whoops bad code?");
    }

    // TODO fix code for case of choosing pokemon after fainting
    private boolean chooseSwitchPkmn(int pokeIndex) {
        printPkmn(player[pokeIndex / numOfPokemonOnField]);
        TurnChoice turnChoice = switchablePkmnGet(pokeIndex);
        if (turnChoice == null) return false;

        turnOrderer.put(turnChoice);
        return true;
    }

    //TODO fix pOut and implement check if not same switch to pokemon as choice before
    private TurnChoice switchablePkmnGet(int pokeLoc) {
        int switchto;
        byte playerIndex = (byte) (pokeLoc / numOfPokemonOnField);
        switchto = Methoder.getInt();
        if (!existsPkmnInPlayer(playerIndex, switchto - 1)) {
            return null;
        }
        while (!player[playerIndex][switchto - 1].isAlive() || player[playerIndex][switchto - 1] == pOut[playerIndex]) {
            if (!player[playerIndex][switchto - 1].isAlive()) {
                System.out.println("This pokemon has fainted, choose another one");
            } else System.out.println("This pokemon is already out, choose another one");
            switchto = Methoder.getInt();
            if (!existsPkmnInPlayer(playerIndex, switchto - 1)) {

                return null;
            }
        }
        return new TurnChoice(Consts.SWITCH_CODE, switchto - 1, 0, pOut[pokeLoc], pokeLoc);
    }

    //TODO fix pOut and implement TurnChoice
    public void pTurn(TurnChoice turnChoice) {
        if (turnChoice.getBaseOption() == Consts.SWITCH_CODE) {
            switchAction(turnChoice);
        }
        else  if (turnChoice.getBaseOption() == Consts.MOVE_CODE) {
            int damage = AtackMngr.attack(pOut[turnChoice.getUserLoc()], pOut, turnChoice);

        }
        printPokemonStatus();

    }

    //TODO fix pOut and implement TurnChoice
    private void switchAction(TurnChoice turnChoice) {
        System.out.println("Come back " + pOut[turnChoice.getUserLoc()].name());
        pOut[turnChoice.getUserLoc()] = player[turnChoice.getUserLoc() / numOfPokemonOnField][turnChoice.getSecondaryOption()];
        System.out.println("Go " + pOut[turnChoice.getUserLoc()].name() + "!");
    }

    //TODO fix pOut and implement TurnChoice
    //TODO reconsider this function's purpose
    public void bothAlive() {
        if (!pOut[isP0First ? 1 : 0].isAlive()) {
            secondHasFainted = true;
        }
        if (pOut[0].isAlive() && pOut[1].isAlive())
            return;
        else if (!pOut[0].isAlive() && !pOut[1].isAlive())
            //TODO code for this case
            ;
        else if (!pOut[0].isAlive()) {
            if (chooseSwitchFainted(0)) return;
            ///TODO combine both methods and implement actual switch of pokemons
            ;
        } else if (!pOut[1].isAlive()) {
            if (chooseSwitchFainted(1)) return;
        }
    }

    //TODO fix pOut and implement TurnChoice
    private boolean chooseSwitchFainted(int p) {
        System.out.println(pOut[p].name() + " fainted");
        if (p == 0) {
            p0Alive--;
            if (p0Alive == 0)
                return true;
        } else {
            p1Alive--;
            if (p1Alive == 0)
                return true;
        }
        System.out.println("Choose which pokemon to send out");
        printPkmn(player[p]);
        TurnChoice turnChoice = switchablePkmnGet(p);
        while (turnChoice == null) {
            System.out.println("Choose a pokemon that hasn't fainted to send instead");
            turnChoice = switchablePkmnGet(p);
        }
        switchAction(turnChoice);
        return false;
    }

    //TODO fix pOut and implement TurnChoice
    //TODO consider changing to compareTo of TurnChoice
    public boolean IsP0First() {
        ///TODO implement priorities and abilities
        if (pChoice[0] == Consts.SWITCH_CODE)
            return true;
        if (pChoice[1] == Consts.SWITCH_CODE)
            return false;

        assert pMove[0] != null : trainer[0].getName() + " didn't choose a move";
        assert pMove[1] != null : trainer[1].getName() + " didn't choose a move";
        if (pMove[0].priority() != pMove[1].priority())
            return pMove[0].priority() > pMove[1].priority();
        if (pOut[0].speed() != pOut[1].speed())
            return pOut[0].speed() > pOut[1].speed();
        return rnd.nextInt(2) == 1;
    }

    public void printMenu() {
        System.out.println(Consts.MOVE_CODE + ": choose a move your pokemon will do\n" +
                Consts.SWITCH_CODE + ": switch pokemon\n" +
                Consts.VIEW_CODE + ": view pokemon stats\n" +
                Consts.BAG_CODE + ": use bag");
    }

    public void printPkmn(Pokemon[] arrPkmn) {
        int i = 1;
        for (Pokemon pkmn : arrPkmn) {
            System.out.println(i + ": " + pkmn.name() + " " + (pkmn.isAlive() ? pkmn.currHp() + " HP" : " fainted"));
            i++;
        }
    }

    public boolean existsPkmnInPlayer(int p, int index) {
        return index >= 0 &&
                index < player[p].length;
    }
    public void printPokemonStatus() {
        for (int i = 0; i < pOut.length; i++) {
            pOut[i].battlePrint();
        }
    }
    public void printPokemonStatus(int p) {
        pOut[p].battlePrint();
    }

    public int getNumOfPokemonOnField() {
        return numOfPokemonOnField;
    }

    public Pokemon[] getpOut() {
        return pOut;
    }
}