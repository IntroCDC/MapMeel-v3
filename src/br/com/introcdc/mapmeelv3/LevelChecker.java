package br.com.introcdc.mapmeelv3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class LevelChecker {

    private static HashMap<String, LevelChecker> ALL_CHECKERS = new HashMap<>();

    public static HashMap<String, LevelChecker> getAllCheckers() {
        return LevelChecker.ALL_CHECKERS;
    }

    private String name;
    private ArrayList<Nivel> nivel;
    private Runnable runnableNotPassed;
    private Runnable runnablePass;

    public LevelChecker(String name, Runnable runnablePass, Runnable runnableNotPassed, Nivel[] niveis) {
        this.name = name;
        this.runnablePass = runnablePass;
        this.runnableNotPassed = runnableNotPassed;
        this.nivel = new ArrayList<>();
        Collections.addAll(this.nivel, niveis);
        LevelChecker.getAllCheckers().put(name, this);
    }

    public void checkForUpdate() {
        int pass = this.getNivel().size();
        int passed = 0;
        for (Nivel nivel : Nivel.values()) {
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
