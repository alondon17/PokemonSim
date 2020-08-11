import java.util.*;

public class BattleManager {
    final static int MOVE_CODE = 1;
    final static int SWITCH_CODE = 2;
    final static int VIEW_CODE = 3;
    final static int BAG_CODE = 4;
    Random rnd = new Random();
    private int p0Alive;
    private int p1Alive;
    private boolean secondHasFainted;
    private boolean isP0First;
    private int[] pChoice = new int[2];
    private int[] recoil = {0, 0};
    private int[] pSwitchTo=new int[2];
    private Move[] pMove = new Move[2];
    // array as p0's pkmns and then p1's
    //TODO fix change in pOuts implementation across the code
    private Pokemon[] pOut;
    private Trainer[] trainer = new Trainer[2];
    private Pokemon[][] player = new Pokemon[2][];
    private int numOfPokemonOnField;

    public BattleManager(Trainer trainer0, Trainer trainer1,int numOfPokemonOnField) {
        this.numOfPokemonOnField=numOfPokemonOnField;
        pOut=new Pokemon[2*numOfPokemonOnField];
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
        startBattle();
endBattle();
    }

    private void endBattle() {
    }


    public void startBattle() {

        for (int i=0;i<numOfPokemonOnField;i++)
        if(player[0].length>i&&player[0][i]!=null)
            pOut[i] = player[0][i];
        for (int i=0;i<numOfPokemonOnField;i++)
            if(player[1].length>i&&player[1][i]!=null)
                pOut[numOfPokemonOnField+i] = player[1][i];
        while (p0Alive > 0 && p1Alive > 0) {
            secondHasFainted=false;
            pChooseMove(0);
            pChooseMove(1);
            isP0First = IsP0First();
            //TODO fix bug: pokemon switched in still does fainted pokemon's move
            int first=isP0First?0:1;

                pTurn(first);
                bothAlive();
                if (p0Alive * p1Alive == 0)
                    break;
                pTurn(1-first);

            bothAlive();
            endOfTurn();

        }
    }

    public void endOfTurn() {

    }

    public void pChooseMove(int p) {
        boolean exitMenu = false;
        System.out.println(trainer[p].getName() + ", you have " + pOut[p].name() + " out, choose your move");

        while (!exitMenu) {
            printMenu();
            pChoice[p] = Methoder.getInt();
            switch (pChoice[p]) {
                case MOVE_CODE:

                    exitMenu = chooseMovePkmn(p);
                    if (!exitMenu) System.out.println("Wrong code, back to menu");
                    break;
                case SWITCH_CODE:
                    exitMenu = chooseSwitchPkmn(p);
                    if (!exitMenu) System.out.println("Wrong code, back to menu");
                    break;
                case VIEW_CODE:
                    ///TODO code for viewing pokemon or combing with switch function
                    break;
                case BAG_CODE:
                    ///TODO code for using bag
                    break;
            }
        }
    }

    private boolean chooseMovePkmn(int p) {
        if (pOut[p].moveset().isStruggle()) {
            pMove[p] = pOut[p].moveset().get(5);
            pChoice[p] = MOVE_CODE;
            return true;
        }
        pOut[p].moveset().printMoveSet();
        pChoice[p] = Methoder.getInt();
        if (!pOut[p].existsMoveInPkmn(pChoice[p] - 1)) {
            return false;
        }
        pMove[p] = pOut[p].moveset().get(pChoice[p] - 1);
        pChoice[p] = MOVE_CODE;
        return true;
    }

    // TODO fix code for case of choosing pokemon after fainting
    private boolean chooseSwitchPkmn(int p) {
        printPkmn(player[p]);
        if(!switchablePkmnGet(p)) return false;

        pChoice[p] = SWITCH_CODE;
        return true;
    }

    private boolean switchablePkmnGet(int p) {
        pSwitchTo[p] = Methoder.getInt();
        if (!existsPkmnInPlayer(p, pSwitchTo[p] - 1)) {
            return false;
        }
        while (!player[p][pSwitchTo[p] - 1].isAlive() || player[p][pSwitchTo[p] - 1] == pOut[p]) {
            if (!player[p][pSwitchTo[p] - 1].isAlive()) {
                System.out.println("This pokemon has fainted, choose another one");
            } else System.out.println("This pokemon is already out, choose another one");
            pSwitchTo[p] = Methoder.getInt();
            if (!existsPkmnInPlayer(p, pSwitchTo[p] - 1)) {

                return false;
            }
        }
        return true;
    }

    public void pTurn(int p) {
        if (pChoice[p] == SWITCH_CODE) {
            switchAction(p);
        }
        if (pChoice[p] == MOVE_CODE) {
            int damage = AtackMngr.attack(pOut[1 - p], pOut[p], pMove[p]);
            if (pMove[p].isRecoil()) {
                recoil[p] = ((MoveNorm) (pMove[p])).calcRecoil(damage, pOut[p]);
                //TODO revoil taking and switching pokemon
            }
        }
        printPokemonStatus(0);
        printPokemonStatus(1);

    }

    private void switchAction(int p) {
        System.out.println("Come back " + pOut[p].name());
        pOut[p] = player[p][pSwitchTo[p] - 1];
        System.out.println("Go " + pOut[p].name() + "!");
    }

    public void bothAlive() {
        if(!pOut[isP0First?1:0].isAlive()){
            secondHasFainted=true;
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
        }
        else if (!pOut[1].isAlive()) {
            if (chooseSwitchFainted(1)) return;
        }
    }

    private boolean chooseSwitchFainted(int p) {
        System.out.println(pOut[p].name() + " fainted");
        if(p==0){
            p0Alive--;
            if (p0Alive == 0)
                return true;
        }else{
            p1Alive--;
            if (p1Alive == 0)
                return true;
        }
        System.out.println("Choose which pokemon to send out");
        printPkmn(player[p]);
        while (!switchablePkmnGet(p)) {
            System.out.println("Choose a pokemon that hasn't fainted to send instead");
        }
        switchAction(p);
        return false;
    }

    public boolean IsP0First() {
        ///TODO implement priorities and abilities
        if (pChoice[0] == SWITCH_CODE)
            return true;
        if (pChoice[1] == SWITCH_CODE)
            return false;

        assert pMove[0] != null : trainer[0].getName() + " didn't choose a move";
        assert pMove[1] != null : trainer[1].getName() + " didn't choose a move";
        if (pMove[0].priority() != pMove[1].priority())
            return pMove[0].priority() > pMove[1].priority();
        if (pOut[0].speed() != pOut[1].speed())
            return pOut[0].speed() > pOut[1].speed();
        return rnd.nextInt(1) == 1;
    }

    public void printMenu() {
        System.out.println(MOVE_CODE + ": choose a move your pokemon will do\n" +
                SWITCH_CODE + ": switch pokemon\n" +
                VIEW_CODE+": view pokemon stats\n" +
                BAG_CODE+": use bag");
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

    public void printPokemonStatus(int p) {
        pOut[p].battlePrint();
    }
}