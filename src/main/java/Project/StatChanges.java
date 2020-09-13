package Project;

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
        atk = 0;
        def = 0;
        spatk = 0;
        spdef = 0;
        spe = 0;
        eva = 0;
        acc = 0;
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
        if (stage < 0) return 1.0 / otherCalc((byte) (-stage));
        return ((double) stage + 2) / 2;
    }

    private double acCalc(byte stage) {
        if (stage < 0) return 1.0 / acCalc((byte) (-stage));
        return ((double) stage + 3) / 3;
    }

    public void changeStat(byte statLoc, byte stageChange) {
        assert statLoc >= 0 && statLoc <= 6 : "Illegal statChange location";
        assert stageChange < 8 && stageChange > -8 : "Illegal stageChange value";
        switch (statLoc) {
            case 0:
                atk += stageChange;
                if (atk > 6) atk = 6;
                if (atk < -6) atk = -6;
                break;
            case 1:
                def += stageChange;
                if (def > 6) def = 6;
                if (def < -6) def = -6;
                break;
            case 2:
                spatk += stageChange;
                if (spatk > 6) spatk = 6;
                if (spatk < -6) spatk = -6;
                break;
            case 3:
                spdef += stageChange;
                if (spdef > 6) spdef = 6;
                if (spdef < -6) spdef = -6;
                break;
            case 4:
                spe += stageChange;
                if (spe > 6) spe = 6;
                if (spe < -6) spe = -6;
                break;
            case 5:
                eva += stageChange;
                if (eva > 6) eva = 6;
                if (eva < -6) eva = -6;
                break;
            case 6:
                acc += stageChange;
                if (acc > 6) acc = 6;
                if (acc < -6) acc = -6;
                break;
        }
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
