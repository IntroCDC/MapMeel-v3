package br.com.introcdc.mapmeelv3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class LevelChecker {

    private static final HashMap<String, LevelChecker> ALL_CHECKERS = new HashMap<>();

    public static HashMap<String, LevelChecker> getAllCheckers() {
        return LevelChecker.ALL_CHECKERS;
    }

    private final String name;
    private final ArrayList<Nivel> nivel;
    private final Runnable runnableNotPassed;
    private final Runnable runnablePass;

    public LevelChecker(final String name, final Runnable runnablePass, final Runnable runnableNotPassed, final Nivel[] niveis) {
        this.name = name;
        this.runnablePass = runnablePass;
        this.runnableNotPassed = runnableNotPassed;
        this.nivel = new ArrayList<>();
        Collections.addAll(this.nivel, niveis);
        LevelChecker.getAllCheckers().put(name, this);
    }

    public void checkForUpdate() {
        final int pass = this.getNivel().size();
        int passed = 0;
        for (final Nivel nivel : Nivel.values()) {
            if (nivel.isCleared()) {
                passed++;
            }
        }
        if (passed < pass) {
            this.getRunnableNotPassed().run();
        } else {
            this.getRunnablePass().run();
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Nivel> getNivel() {
        return this.nivel;
    }

    public Runnable getRunnableNotPassed() {
        return this.runnableNotPassed;
    }

    public Runnable getRunnablePass() {
        return this.runnablePass;
    }

}
