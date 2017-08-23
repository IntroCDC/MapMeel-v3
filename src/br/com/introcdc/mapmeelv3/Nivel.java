package br.com.introcdc.mapmeelv3;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;

public enum Nivel {

    EASTEREGG_1("EasterEgg-1",
                null,
                Warp.EASTEREGG_1,
                Audio.WIN_1),
    EASTEREGG_2("EasterEgg-2",
                null,
                Warp.EASTEREGG_2,
                Audio.WIN_2),
    EASTEREGG_3("EasterEgg-3",
                null,
                Warp.EASTEREGG_3,
                Audio.WIN_3),
    EASTEREGG_4("EasterEgg-4",
                null,
                Warp.EASTEREGG_3,
                Audio.WIN_4),
    L_1A("1A",
         new Location(Bukkit.getWorld("world"), 977.5, 47.0, 1000.5),
         Warp.L_1A,
         Audio.WIN_1),
    L_1B("1B",
         new Location(Bukkit.getWorld("world"), 1000.5, 47.0, 977.5),
         Warp.L_1B,
         Audio.WIN_1),
    L_1C("1C",
         new Location(Bukkit.getWorld("world"), 1023.5, 47.0, 1000.5),
         Warp.L_1C,
         Audio.WIN_1),
    L_1D("1D",
         new Location(Bukkit.getWorld("world"), 1000.5, 47.0, 1023.5),
         Warp.L_1D,
         Audio.WIN_1),
    L_2A("2A",
         new Location(Bukkit.getWorld("world"), 1959.5, 68.0, 1941.5),
         Warp.L_2A,
         Audio.WIN_2),
    L_2B("2B",
         new Location(Bukkit.getWorld("world"), 2036.5, 73.0, 1968.5),
         Warp.L_2B,
         Audio.WIN_2),
    L_2C("2C",
         new Location(Bukkit.getWorld("world"), 1978.5, 62.0, 2020.5),
         Warp.L_2C,
         Audio.WIN_2),
    L_2D("2D",
         new Location(Bukkit.getWorld("world"), 1916.5, 67.0, 1979.5),
         Warp.L_2D,
         Audio.WIN_2),
    L_3A("3A",
         new Location(Bukkit.getWorld("world_nether"), 444.5, 83.0, 404.5),
         Warp.L_3A,
         Audio.WIN_3),
    L_3B("3B",
         new Location(Bukkit.getWorld("world_nether"), 508.5, 71.0, 517.5),
         Warp.L_3B,
         Audio.WIN_3),
    L_3C("3C",
         new Location(Bukkit.getWorld("world_nether"), 462.5, 70.0, 457.5),
         Warp.L_3C,
         Audio.WIN_3),
    L_3D("3D",
         new Location(Bukkit.getWorld("world_nether"), 491.5, 58.0, 457.5),
         Warp.L_3D,
         Audio.WIN_3),
    L_4A("4A",
         new Location(Bukkit.getWorld("world_the_end"), 1825.5, 72.0, 2077.5),
         Warp.L_4A,
         Audio.WIN_4),
    L_4B("4B",
         new Location(Bukkit.getWorld("world_the_end"), 1857.5, 72.0, 2048.5),
         Warp.L_4B,
         Audio.WIN_4),
    L_4C("4C",
         new Location(Bukkit.getWorld("world_the_end"), 1888.5, 72.0, 2077.5),
         Warp.L_4C,
         Audio.WIN_4),
    L_4D("4D",
         new Location(Bukkit.getWorld("world_the_end"), 1857.5, 72.0, 2098.5),
         Warp.L_4D,
         Audio.WIN_4);

    public static boolean checkLevel(final Nivel... niveis) {
        int pass = 0;
        for (final Nivel nivel : niveis) {
            if (nivel.isCleared()) {
                pass++;
            }
        }
        return pass >= niveis.length;
    }

    private boolean cleared;
    private String name;
    private Location placar;
    private Warp warp;

    private Audio win;

    Nivel(final String name, final Location placar, final Warp warp, final Audio win) {
        this.name = name;
        this.placar = placar;
        this.warp = warp;
        this.cleared = MapMeelMain.getPlugin().getConfig().getBoolean("Niveis." + name + ".Cleared");
        this.win = win;
        new BukkitRunnable() {
            @Override
            public void run() {
                Nivel.this.setCleared(Nivel.this.cleared);
            }
        }.runTaskLater(MapMeelMain.getPlugin(), 20);
    }

    public String getName() {
        return this.name;
    }

    public Location getPlacar() {
        return this.placar;
    }

    public Warp getWarp() {
        return this.warp;
    }

    public Audio getWin() {
        return this.win;
    }

    public boolean isCleared() {
        return this.cleared;
    }

    public void setCleared(final boolean cleared) {
        this.cleared = cleared;
        MapMeelMain.getPlugin().getConfig().set("Niveis." + this.name + ".Cleared", cleared);
        MapMeelMain.getPlugin().saveConfig();
        if (this.placar != null) {
            for (final Block b : Utils.nearBlocks(this.placar, 8)) {
                if (!b.getType().equals(Material.WOOL)) {
                    continue;
                }
                if (cleared) {
                    b.setData((byte) 5);
                } else {
                    b.setData((byte) 0);
                }
            }
        }
        if (cleared) {
            for (final Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Strings.prefix + "§aParabéns! §fO Nível §a" + this.name + "§f foi completado com sucesso!");
                Utils.sendTitle(p, "§2§lParabéns!", "§fO Nível §a§l" + this.name + "§f foi completado!", 10, 40, 10);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 50000);
                Utils.launchFireworks(p.getLocation());
                Utils.launchFireworks(p.getLocation());
                Utils.launchFireworks(p.getLocation());
            }
            if (CoreOverwatch.recording) {
                if (CoreOverwatch.currentState != null) {
                    CoreOverwatch.currentState.setMessage("§fO Nível §a" + this.getName() + "§f foi completado com sucesso!");
                }
            }
        }
        for (final String ss : LevelChecker.getAllCheckers().keySet()) {
            LevelChecker.getAllCheckers().get(ss).checkForUpdate();
        }
    }

}
