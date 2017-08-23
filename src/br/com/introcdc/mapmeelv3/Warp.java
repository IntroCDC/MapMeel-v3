package br.com.introcdc.mapmeelv3;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Warp {

    EASTEREGG_1("EasterEgg-1",
                new Location(Bukkit.getWorld("world"), -1000.5, 30, -1000.5),
                null),
    EASTEREGG_2("EasterEgg-2",
                new Location(Bukkit.getWorld("world"), 5000.5, 30, 5000.5),
                null),
    EASTEREGG_3("EasterEgg-3",
                new Location(Bukkit.getWorld("world_nether"), 4863.5, 59, 4707.5),
                null),
    EASTEREGG_4("EasterEgg-4",
                new Location(Bukkit.getWorld("world_the_end"), 5804.5, 57, 4974.5),
                null),
    FINAL("Final",
          new Location(Bukkit.getWorld("world"), 10000.5, 100, 10000.5),
          null),
    L_1A("1A",
         new Location(Bukkit.getWorld("world"), 1500.5, 32, 1500.5),
         Audio.GUMPTION),
    L_1B("1B",
         new Location(Bukkit.getWorld("world"), 1000.5, 30, 1500.5),
         Audio.SPONGEBOB),
    L_1C("1C",
         new Location(Bukkit.getWorld("world"), 1500.5, 30, 1000.5),
         Audio.UNITY),
    L_1D("1D",
         new Location(Bukkit.getWorld("world"), 2000.5, 30, 1000.5),
         Audio.ROLL_POLKA),
    L_2A("2A",
         new Location(Bukkit.getWorld("world"), 2200.5, 23, 2200.5),
         Audio.SUPER_MARIO_REMIX),
    L_2B("2B",
         new Location(Bukkit.getWorld("world"), 3100.5, 14, 3100.5),
         Audio.LEVAN_POLKKA),
    L_2C("2C",
         new Location(Bukkit.getWorld("world"), 2100.5, 14, 1600.5),
         Audio.EPHIXA),
    L_2D("2D",
         new Location(Bukkit.getWorld("world"), 2861.5, 29, 2845.5),
         Audio.INDIANA_JONES),
    L_3A("3A",
         new Location(Bukkit.getWorld("world_nether"), 944.5, 68, 330.5),
         Audio.AFTER_THE_FALL),
    L_3B("3B",
         new Location(Bukkit.getWorld("world_nether"), 454.5, 89, 672.5),
         Audio.CHILDREN_OF_THE_SUN),
    L_3C("3C",
         new Location(Bukkit.getWorld("world_nether"), 120.5, 67, 421.5),
         Audio.VICTORY),
    L_3D("3D",
         new Location(Bukkit.getWorld("world_nether"), 500.5, 33, 1.5),
         Audio.FLYINGSNOW),
    L_4A("4A",
         new Location(Bukkit.getWorld("world_the_end"), 1285.5, 58, 2348.5),
         Audio.THE_ISLAND),
    L_4B("4B",
         new Location(Bukkit.getWorld("world_the_end"), 1755.5, 241, 1740.5),
         Audio.MYTHIC),
    L_4C("4C",
         new Location(Bukkit.getWorld("world_the_end"), 2388.5, 53, 2150.5),
         Audio.ARIVAL_OF_THE_BIRDS),
    L_4D("4D",
         new Location(Bukkit.getWorld("world_the_end"), 4100.5, 59, 3400.5),
         Audio.FOR_THE_WIN),
    LOBBY("Lobby",
          new Location(Bukkit.getWorld("world"), 8.5, 22, 31.5),
          Audio.MUSIC_THEME),
    LOBBY_1("Lobby-1",
            new Location(Bukkit.getWorld("world"), 1000.5, 33, 1000.5),
            Audio.STARFALL),
    LOBBY_2("Lobby-2",
            new Location(Bukkit.getWorld("world"), 1962.5, 54, 1974.5),
            Audio.NA_VEDUI),
    LOBBY_3("Lobby-3",
            new Location(Bukkit.getWorld("world_nether"), 500.5, 76, 393.5),
            Audio.SALVATION),
    LOBBY_4("Lobby-4",
            new Location(Bukkit.getWorld("world_the_end"), 1857.5, 65, 2077.5),
            Audio.RIDE_TO_VICTORY),
    PLATAFORMA_FINALV("PlataformaFinal",
                      new Location(Bukkit.getWorld("world"), 12000.5, 100, 12000.5),
                      null),
    PLATAFORMAFINALFAKE("PlataformaFinalF",
                        new Location(Bukkit.getWorld("world"), 6000.5, 100, 6000.5),
                        null);

    public static Warp byLocation(final Location location) {
        for (final Warp w : Warp.values()) {
            if (w.getLocation().equals(location)) {
                return w;
            }
        }
        return null;
    }

    public static Warp byName(final String name) {
        for (final Warp w : Warp.values()) {
            if (w.getName().equalsIgnoreCase(name)) {
                return w;
            }
        }
        return null;
    }

    public static boolean existsWarp(final String name) {
        for (final Warp w : Warp.values()) {
            if (w.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private Audio fundo;

    private Location location;

    private String name;

    Warp(final String name, final Location location, final Audio fundo) {
        this.name = name;
        this.location = location;
        this.fundo = fundo;
    }

    public Audio getFundo() {
        return this.fundo;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

}
