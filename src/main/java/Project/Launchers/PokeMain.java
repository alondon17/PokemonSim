package Project.Launchers;

import Project.Battles.BattleManager;
import Project.Battles.Player;
import Project.Battles.Trainer;
import Project.Battles.PokemonData.PokeSelector;
import Project.SystemStuff.CurrentObjs;

public class PokeMain {

    public static void main(String[] args) {
        Trainer trainer1 = new Player("Alon", PokeSelector.select(new int[]{39}));
        Trainer trainer2 = new Player("Alex", PokeSelector.select());
        CurrentObjs.setBattleManager(new BattleManager(trainer1, trainer2, 1));
        CurrentObjs.battleManager().startBattle();

    }
}
