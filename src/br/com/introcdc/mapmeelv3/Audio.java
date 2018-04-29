package br.com.introcdc.mapmeelv3;

public enum Audio {

    AFTER_THE_FALL("AfterTheFall",
                   "mapmeelv3.afterthefall"),
    ARIVAL_OF_THE_BIRDS("ArialOfTheBirds",
                        "mapmeelv3.arrivalofthebirds"),
    BUILD_A_HOME("BuildAHome",
                 "mapmeelv3.buildahome"),
    CHECKPOINT("CheckPoint",
               "mapmeelv3.checkpoint"),
    CHILDREN_OF_THE_SUN("ChildrenOfTheSun",
                        "mapmeelv3.childrenofthesun"),
    EPHIXA("Ephixa",
           "mapmeelv3.ephixa"),
    ERROU("Errou",
          "mapmeelv3.errou"),
    FLYINGSNOW("FlyingSnow",
               "mapmeelv3.flyingsnow"),
    FOR_THE_WIN("ForTheWin",
                "mapmeelv3.forthewin"),
    FUNDO("Fundo",
          "mapmeelv3.fundo"),
    GUMPTION("Gumption",
             "mapmeelv3.gumption"),
    HISTORIA("Historia",
             "mapmeelv3.historia"),
    HISTORIA_2("Historia2",
               "mapmeelv3.historia2"),
    INDIANA_JONES("IndianaJones",
                  "mapmeelv3.indianajones"),
    LEVAN_POLKKA("LevanPolkka",
                 "mapmeelv3.levanpolkka"),
    MAINTITLES("MainTitles",
               "mapmeelv3.maintitles"),
    MUSIC_THEME("MusicTheme",
                "mapmeelv3.musictheme"),
    MYTHIC("Mythic",
           "mapmeelv3.mythic"),
    NA_VEDUI("NaVedui",
             "mapmeelv3.navedui"),
    PARAR("Parar",
          "mapmeelv3.parar"),
    PORTAL("Portal",
           "mapmeelv3.portal"),
    RIDE_TO_VICTORY("RideToVictory",
                    "mapmeelv3.ridetovictory"),
    ROLL_POLKA("RollPolka",
               "mapmeelv3.rollpolka"),
    SALVATION("Salvation",
              "mapmeelv3.salvation"),
    SMAX("SMax",
         "mapmeelv3.smax"),
    SPONGEBOB("Spongebob",
              "mapmeelv3.spongebob"),
    STARFALL("Starfall",
             "mapmeelv3.starfall"),
    SUPER_MARIO_REMIX("SuperMarioRemix",
                      "mapmeelv3.supermarioremix"),
    TESTE("Teste",
          "mapmeelv3.teste"),
    THE_ISLAND("TheIsland",
               "mapmeelv3.theisland"),
    UNDONE("Undone",
           "mapmeelv3.undone"),
    UNITY("Unity",
          "mapmeelv3.unity"),
    VICTORY("Victory",
            "mapmeelv3.victory"),
    WIN("Win",
        "mapmeelv3.win"),
    WIN_1("Win1",
          "mapmeelv3.win1"),
    WIN_2("Win2",
          "mapmeelv3.win2"),
    WIN_3("Win3",
          "mapmeelv3.win3"),
    WIN_4("Win4",
          "mapmeelv3.win4");

    public static Audio byName(String name) {
        for (Audio a : Audio.values()) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return Audio.PARAR;
    }

    public static boolean existsAudio(String name) {
        for (Audio a : Audio.values()) {
            if (a.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private String name;

    private String patch;

    Audio(String name, String patch) {
        this.name = name;
        this.patch = patch;
    }

    public String getName() {
        return this.name;
    }

    public String getPatch() {
        return this.patch;
    }

}
