public class PokeMain {
    public static void main(String[] args) {
        Trainer trainer1=new Player("Alon",PokeSelector.select());
        Trainer trainer2=new Player("Alex",PokeSelector.select());
        BattleManager battleManager=new BattleManager(trainer1,trainer2);


    }
}
