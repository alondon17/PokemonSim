package Project;

public class PokeMain {
    static BattleManager battleManager;
    public static void main(String[] args) {
        Trainer trainer1=new Player("Alon",PokeSelector.random(5));
        Trainer trainer2=new Player("Alex",PokeSelector.random(5));
        battleManager=new BattleManager(trainer1,trainer2,2);
battleManager.startBattle();

    }
}
