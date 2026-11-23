package br.com.introcdc.mapmeelv3;

import br.com.introcdc.mapmeelv3.listeners.MoveEvent;
import br.com.introcdc.mapmeelv3.variables.Strings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class MapFinal {

    public static Random random = new Random();

    public static boolean started = false;

    private static int timer = 0;

    // Cada "passo" equivale a 2 ticks (100ms) a 20 TPS. O tempo e medido pelo relogio real,
    // para o show continuar sincronizado com a musica mesmo se o servidor estiver abaixo de 20 TPS.
    private static final long STEP_MS = 100L;
    private static long stageStart = 0L;

    private static int currentStep() {
        return (int) ((System.currentTimeMillis() - MapFinal.stageStart) / MapFinal.STEP_MS) + 1;
    }

    public static void event1(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §aEasterEgg-2");
        p.teleport(HistoriaMapMeelv3.meio);
        HistoriaMapMeelv3.loadConstruction("teatroFNegra");
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 135) {
                    this.cancel();
                    MapFinal.stageStart += 135 * MapFinal.STEP_MS;
                    MapFinal.event2(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event10(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §a2A");
        p.teleport(Warp.L_2A.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event11(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event11(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §a2B");
        p.teleport(Warp.L_2B.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event12(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event12(Player p) {
        Location special = new Location(Bukkit.getWorld("world"), 8000, 100, 8000);
        p.teleport(special);
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 270) {
                    this.cancel();
                    MapFinal.stageStart += 270 * MapFinal.STEP_MS;
                    MapFinal.event13(p);
                } else {
                    Utils.launchFireworks(special.clone().add(MapFinal.random.nextInt(80) - 40, 5, MapFinal.random.nextInt(80) - 40));
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event13(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §aEasterEgg-1");
        p.teleport(Warp.EASTEREGG_1.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 210) {
                    this.cancel();
                    MapFinal.stageStart += 210 * MapFinal.STEP_MS;
                    MapFinal.event14(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event14(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §aEasterEgg-2");
        p.teleport(Warp.EASTEREGG_2.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 140) {
                    this.cancel();
                    MapFinal.stageStart += 140 * MapFinal.STEP_MS;
                    MapFinal.event15(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event15(Player p) {
        Location special = new Location(Bukkit.getWorld("world"), 8000, 100, 8000);
        p.teleport(special);
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 600) {
                    this.cancel();
                    MapFinal.stageStart += 600 * MapFinal.STEP_MS;
                    MapFinal.started = false;
                    Utils.playSound(p, Audio.WIN);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp PlataformaFinal " + p.getName());
                    Utils.sendTitle(p, "§2§lParabéns!", "§oVocê finalizou o §5§oMapMeel v3§f§o com sucesso!", 20, 100, 20);
                    p.sendMessage(Strings.prefix + "§2§lParabéns! §f§oVocê finalizou o §5§oMapMeel v3§f§o com sucesso!");

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                sendPluginMessage(player, "MapMeelv3Complete");
                                sendPlayer(player, "KitPvP");
                            }
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 200);

                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 50000, 1);
                    Bukkit.getWorld("world").setTime(6000);
                    if (Utils.isMeelOn()) {
                        if (Utils.getMeel().getName().equalsIgnoreCase(p.getName())) {
                            MoveEvent.teleporting = false;
                        }
                    }
                } else {
                    if (MapFinal.timer < 300) {
                        Utils.launchFireworks(special.clone().add(MapFinal.random.nextInt(80) - 40, 5, MapFinal.random.nextInt(80) - 40));
                    }
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void sendPlayer(Player player, String server) {
        sendPluginMessage(player, "Connect", server);
    }

    public static void sendPluginMessage(Player player, String... messages) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(byteArray);
        try {
            for (String message : messages) {
                output.writeUTF(message);
            }
        } catch (IOException ignored) {
        }
        player.sendPluginMessage(MapMeelMain.getPlugin(), "kindome:pm", byteArray.toByteArray());
    }

    public static void event2(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §a2A");
        p.teleport(Warp.L_2A.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 135) {
                    this.cancel();
                    MapFinal.stageStart += 135 * MapFinal.STEP_MS;
                    MapFinal.event3(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event3(Player p) {
        Location special = new Location(Bukkit.getWorld("world"), 8000, 100, 8000);
        p.teleport(special);
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 140) {
                    this.cancel();
                    MapFinal.stageStart += 140 * MapFinal.STEP_MS;
                    MapFinal.event4(p);
                } else {
                    Utils.launchFireworks(special.clone().add(MapFinal.random.nextInt(80) - 40, 5, MapFinal.random.nextInt(80) - 40));
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event4(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §aLobby-1");
        p.teleport(Warp.LOBBY_1.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event5(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event5(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §aLobby-2");
        p.teleport(Warp.LOBBY_2.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event6(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event6(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §a1A");
        p.teleport(Warp.L_1A.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event7(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event7(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §a1B");
        p.teleport(Warp.L_1B.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event8(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event8(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §a1C");
        p.teleport(Warp.L_1C.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event9(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void event9(Player p) {
        p.sendMessage(Strings.prefix + "§fMapa: §a1D");
        p.teleport(Warp.L_1D.getLocation());
        MapFinal.timer = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer > 17) {
                    this.cancel();
                    MapFinal.stageStart += 17 * MapFinal.STEP_MS;
                    MapFinal.event10(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

    public static void start(Player p) {
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(p.getName())) {
                MoveEvent.teleporting = true;
            }
        }
        if (MapFinal.started) {
            p.sendMessage(Strings.prefix + "§cAguarde para executar este evento!");
            return;
        }
        MapFinal.started = true;
        // Marca o inicio da musica: todas as etapas sao medidas a partir daqui, em tempo real.
        MapFinal.stageStart = System.currentTimeMillis();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather rain 180");
        Bukkit.getWorld("world").setTime(16000);
        MapFinal.timer = 0;
        Utils.playSound(p, Audio.UNDONE);
        p.teleport(Warp.FINAL.getLocation());
        new BukkitRunnable() {
            @Override
            public void run() {
                MapFinal.timer = MapFinal.currentStep();
                if (MapFinal.timer <= 140) {
                    p.setVelocity(new Vector(0, 0.3f, 0));
                    p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation().clone().add(5, 0, 5), 10);
                    p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation().clone().add(-5, 0, 5), 10);
                    p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation().clone().add(5, 0, -5), 10);
                    p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation().clone().add(-5, 0, -5), 10);
                } else {
                    this.cancel();
                    MapFinal.stageStart += 140 * MapFinal.STEP_MS;
                    MapFinal.event1(p);
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 2);
    }

}
