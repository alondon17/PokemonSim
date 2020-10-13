package Project.Launchers;

import Project.Battles.BattleManager;
import Project.Battles.Player;
import Project.Battles.Trainer;
import Project.Battles.PokemonData.PokeSelector;
import Project.SystemStuff.CurrentObjs;

public class PokeMain {

    public static void main(String[] args) {
        Trainer trainer1 = new Player("Alon", PokeSelector.select(new int[]{4}));
        Trainer trainer2 = new Player("Alex", PokeSelector.select(new int[]{1}));
        CurrentObjs.setBattleManager(new BattleManager(trainer1, trainer2, 1));
        CurrentObjs.battleManager().startBattle();

    }
}
