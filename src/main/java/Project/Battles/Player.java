package Project.Battles;

import Project.Battles.PokemonData.Pokemon;

public class Player extends Trainer {
    private long money;

    public Player(String name, Pokemon[] pokemons) {
        super(name, pokemons);
        money=0;
    }
}
