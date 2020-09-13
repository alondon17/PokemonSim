package Project;

public class PokeMain {
    static BattleManager battleManager;
    public static void main(String[] args) {
        Trainer trainer1=new Player("Alon",PokeSelector.select());
        Trainer trainer2=new Player("Alex",PokeSelector.select());
        battleManager=new BattleManager(trainer1,trainer2,2);
battleManager.startBattle();

    }
}
