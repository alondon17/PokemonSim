package Project.Battles;

public enum TrainerClass {
    BIKER(1,"Biker"),
    YOUNGSTER(2,"Youngster"),
    ;
    private int trainer;
    private String className;

    TrainerClass(int trainer,String className){
        this.trainer = trainer;
        this.className=className;
    }

    public int getTrainer() {
        return trainer;
    }

    public String getName() {
        return className;
    }
}
