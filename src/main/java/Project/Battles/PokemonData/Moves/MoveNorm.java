package Project.Battles.PokemonData.Moves;

import Project.Battles.PokemonData.Pokemon;

public class MoveNorm extends Move {
    private int power;
    private int recoil;
    public MoveNorm(int id, int type, String name, int power, boolean isPhys, int accuracy, int pp) {
        super(id, type, name, pp, isPhys, accuracy);
        this.power=power;
        this.recoil=0;
    }

    public int power() {
        return power;
    }

    @Override
    public boolean isRecoil() {
        return recoil>0;
    }
    public int calcRecoil(int damage, Pokemon pkmn) {
        int returnVal=0;
        switch (this.id()){
            case 165:
                returnVal=pkmn.pkmnHp()/4;
        }
        return returnVal;
    }
}
