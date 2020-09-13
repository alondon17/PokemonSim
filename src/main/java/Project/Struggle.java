package Project;

public class Struggle extends Move {
    private static Move struggle= new Struggle();
    private Struggle() {
        super(165, 0, "Struggle", 0, true, 100);
    }

    public static Move getStruggle() {
        return struggle;
    }
}
