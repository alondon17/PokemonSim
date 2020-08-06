public class Opponent extends Trainer{
    private int cashPrize;
    private TrainerClass trainerClass;

    public Opponent(TrainerClass trainerClass,String name,Pokemon[] pokemons,int cashPrize) {
        super(name,pokemons);
        this.trainerClass=trainerClass;
        this.cashPrize=cashPrize;
    }

    @Override
    public String getName() {
        return trainerClass.getName()+" "+super.getName();
    }
}
