@SuppressWarnings("SpellCheckingInspection")
public class StatChanges {
    private byte atk;
    private byte def;
    private byte spatk;
    private byte spdef;
    private byte spe;
    private byte eva;
    private byte acc;

    public StatChanges() {
        atk=0;def=0;spatk=0;spdef=0;spe=0;eva=0;acc=0;
    }

    public void resetChanges() {
        atk = 0;
        def = 0;
        spatk = 0;
        spdef = 0;
        spe = 0;
        eva = 0;
        acc = 0;
    }

    private double otherCalc(byte stage) {
        if (stage < 0) return 1.0/otherCalc((byte) (-stage));
        return ((double) stage + 2) / 2;
    }

    private double acCalc(byte stage) {
        if (stage < 0) return 1.0/acCalc((byte) (-stage));
        return ((double) stage + 3) / 3;
    }

    public double atkMod() {
        return otherCalc(atk);
    }

    public double defMod() {
        return otherCalc(def);
    }

    public double spatkMod() {
        return otherCalc(spatk);
    }

    public double spdefMod() {
        return otherCalc(spdef);
    }

    public double speMod() {
        return otherCalc(spe);
    }
    public double evaMod() {
        return acCalc((byte) -eva);
    }
    public double accMod() {
        return acCalc(acc);
    }
}
