package br.com.introcdc.mapmeelv3;

public enum DialogBot {

    BOA_SORTE("mapmeelv3.dialogs.boasorte"),
    FRASE_LOBBY_FINAL("mapmeelv3.dialogs.lobbyfinal"),
    LEVEL_1_1C("mapmeelv3.dialogs.level11c"),
    LEVEL_1_1D("mapmeelv3.dialogs.level11d"),
    LEVEL_1_EASTEREGG_1("mapmeelv3.dialogs.level1easteregg1"),
    LEVEL_1_EASTEREGG_2("mapmeelv3.dialogs.level1easteregg2"),
    LEVEL_1_EASTEREGG_3("mapmeelv3.dialogs.level1easteregg3"),
    LEVEL_1_EASTEREGG_4("mapmeelv3.dialogs.level1easteregg4"),
    LEVEL_1_LOBBY("mapmeelv3.dialogs.level1lobby"),
    LEVEL_2_2B("mapmeelv3.dialogs.level22b"),
    LEVEL_2_EASTEREGG_1("mapmeelv3.dialogs.level2easteregg1"),
    LEVEL_2_EASTEREGG_2("mapmeelv3.dialogs.level2easteregg2"),
    LEVEL_2_LOBBY("mapmeelv3.dialogs.level2lobby"),
    LEVEL_3_3B("mapmeelv3.dialogs.level33b"),
    LEVEL_3_3B1("mapmeelv3.dialogs.level33b1"),
    LEVEL_3_E_1("mapmeelv3.dialogs.level3e1"),
    LEVEL_3_E_R1("mapmeelv3.dialogs.level3er1"),
    LEVEL_3_E_R2("mapmeelv3.dialogs.level3er2"),
    LEVEL_3_E_R3("mapmeelv3.dialogs.level3er3"),
    LEVEL_3_E_R4("mapmeelv3.dialogs.level3er4"),
    LEVEL_3_E_R5("mapmeelv3.dialogs.level3er5"),
    LEVEL_3_E_R6("mapmeelv3.dialogs.level3er6"),
    LEVEL_3_E_R7("mapmeelv3.dialogs.level3er7"),
    LEVEL_3_LOBBY("mapmeelv3.dialogs.level3lobby"),
    LEVEL_4_4B("mapmeelv3.dialogs.level44b"),
    LEVEL_4_4D("mapmeelv3.dialogs.level44d"),
    LEVEL_4_EASTEREGG_1("mapmeelv3.dialogs.level4easteregg1"),
    LEVEL_4_EASTEREGG_2("mapmeelv3.dialogs.level4easteregg2"),
    LEVEL_4_LOBBY("mapmeelv3.dialogs.level4lobby"),
    MENSAGEM_ANIVESARIO("mapmeelv3.dialogs.messagefinal"),
    NAO_LEVEL("mapmeelv3.dialogs.naolevel"),
    OLA("mapmeelv3.dialogs.ola"),
    SEJA_BEM_VINDA("mapmeelv3.dialogs.sejabemvinda"),
    ZERAR_VIDA("mapmeelv3.dialogs.zerarvida");

    private String patch;

    DialogBot(final String patch) {
        this.patch = patch;
    }

    public String getPatch() {
        return this.patch;
    }

}
