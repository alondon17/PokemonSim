package Project.Battles;

import Project.Battles.PokemonData.Pokemon;
import Project.SystemStuff.Utilities.Methoder;

public abstract class Trainer {
    private Pokemon[] pokemons;


    private String name;
    public Trainer(String name,Pokemon[] pokemons) {
        this.name = name;
        this.pokemons= Methoder.arrayShorten(pokemons);
    }

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = Methoder.arrayShorten(pokemons);
    }

    public String getName() {
        return name;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }
}
