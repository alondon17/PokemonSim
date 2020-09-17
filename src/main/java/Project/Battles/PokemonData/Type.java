package Project.Battles.PokemonData;

public enum Type {
    Norm(1, "Normal"),
    Fight(2, "Fighting"),
    Fly(3, "Flying"),
    Poison(4, "Poison"),
    Ground(5, "Ground"),
    Rock(6, "Rock"),
    Bug(7, "Bug"),
    Ghost(8, "Ghost"),
    Steel(9, "Steel"),
    Fire(10, "Fire"),
    Water(11, "Water"),
    Grass(12, "Grass"),
    Elec(13, "Electric"),
    Psy(14, "Psychic"),
    Ice(15,"Ice"),
    Drag(16,"Dragon"),
    Dark(17,"Dark"),
    Fairy(18,"Fairy");
    private final int num;
    private final String name;

    Type(int num, String name) {
this.num=num;
this.name=name;
    }
    public int num() { return num; }
    public String type() { return name; }

}
