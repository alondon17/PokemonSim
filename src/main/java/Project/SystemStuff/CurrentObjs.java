package Project.SystemStuff;

import Project.Battles.BattleManager;
import Project.Visuals.FrameThing;

public class CurrentObjs {
    private static BattleManager battleManager;
    private static FrameThing frameThing;

    public static BattleManager battleManager() {
        return battleManager;
    }

    public static void setBattleManager(BattleManager battleManager) {
        CurrentObjs.battleManager = battleManager;
    }

    public static FrameThing frameThing() {
        return frameThing;
    }

    public static void setFrameThing(FrameThing frameThing) {
        CurrentObjs.frameThing = frameThing;
    }
}
