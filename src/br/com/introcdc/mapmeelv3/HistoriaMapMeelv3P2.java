package br.com.introcdc.mapmeelv3;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import br.com.introcdc.mapmeelv3.listeners.MoveEvent;
import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class HistoriaMapMeelv3P2 extends HistoriaMapMeelv3 {

    public static Location intro_casa = new Location(Bukkit.getWorlds().get(0), 4981.5, 36.0, 4869.5);
    public static Location meel_hotel = new Location(Bukkit.getWorlds().get(0), 5024.5, 38.0, 4868.5);

    public static void startHistory(final Player player) {
        HistoriaMapMeelv3.reloadAllSkins();

        HistoriaMapMeelv3.toggleLightsAudience(false);
        HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
        HistoriaMapMeelv3.loadConstruction("teatroFNegra");

        final Location location = Warp.FINAL.getLocation();

        for (final Player pl : Bukkit.getOnlinePlayers()) {
            pl.teleport(new Location(Bukkit.getWorlds().get(0), 5002, 36, 4890, 180, -8));
            Utils.playSound(pl, Audio.HISTORIA_2);
        }

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "cortina"), 0, 2, 9);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "cortina"), 0, 3, 21);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "atrascima"), 0, 10, 13);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "atrascima"), 0, 11, 28);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "tudo"), 0, 18, 23);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "tudo"), 0, 19, 28);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "atrascima"), 0, 22, 6);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "atrascima"), 0, 22, 29);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(true, "tudo"), 0, 31, 1);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "tudo"), 0, 33, 9);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 0, 17, 22);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroCasas");
            HistoriaMapMeelv3.toggleLightsStage(true, "tudo");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.add(1, 0, 0));
        }, 0, 34, 16);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3P2.meel_hotel), 0, 55, 27);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCmeel.despawn(), 1, 0, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3P2.intro_casa), 1, 7, 6);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.despawn(), 1, 12, 6);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 1, 14, 6);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
        }, 1, 20, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.despawn(), 1, 30, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 1, 35, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 1, 41, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroRioMar");
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(new Location(Bukkit.getWorlds().get(0), 5014.5, 36.0, 4869.5));
        }, 1, 50, 0);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCintro.spawn(new Location(Bukkit.getWorld("world"), 4982.5, 36.0, 4873.5));
        }, 1, 53, 0);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio), 1, 55, 26);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(new Location(Bukkit.getWorlds().get(0), 5014.5, 36.0, 4869.5)), 1, 57, 26);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(new Location(Bukkit.getWorlds().get(0), 5014.5, 36.0, 4869.5)), 1, 59, 26);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(new Location(Bukkit.getWorlds().get(0), 5014.5, 36.0, 4869.5)), 2, 2, 26);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroShopping1");
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.meio);
        }, 2, 4, 3);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.spawn(new Location(Bukkit.getWorld("world"), 4982.5, 36.0, 4873.5));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
        }, 2, 15, 8);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(1, 0, 0));
        }, 2, 18, 8);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 2, 34, 3);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 2, 40, 3);

        new CustomDelay(() -> HistoriaMapMeelv3.loadConstruction("teatroCasas"), 2, 57, 3);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(3, 0, 0)), 2, 58, 21);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCmeel.getNavigator().setTarget(HistoriaMapMeelv3P2.meel_hotel), 3, 0, 21);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(-3, 0, 0)), 3, 0, 21);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3P2.intro_casa), 3, 2, 21);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
            HistoriaMapMeelv3.toggleLightsStage(true, "frente");
        }, 3, 5, 21);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio_frente);
        }, 3, 23, 21);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroNada");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0));
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 3, 41, 4);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.faceLocation(HistoriaMapMeelv3.NPCmeel.getStoredLocation());
            HistoriaMapMeelv3.NPCmeel.faceLocation(HistoriaMapMeelv3.NPCintro.getStoredLocation());
        }, 3, 42, 4);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCmeel.despawn();
            HistoriaMapMeelv3.loadConstruction("teatroCasas");
        }, 8, 59, 4);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3P2.intro_casa);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(-3, 0, 0));
        }, 9, 34, 4);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3P2.intro_casa);
            HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio);
        }, 9, 36, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(3, 0, 0)), 9, 38, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(new Location(Bukkit.getWorld("world"), 5012.5, 36.0, 4868.5)), 9, 41, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(new Location(Bukkit.getWorld("world"), 5028.5, 52.0, 4868.5)), 9, 42, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(new Location(Bukkit.getWorld("world"), 5028.5, 52.0, 4868.5)), 9, 43, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(new Location(Bukkit.getWorld("world"), 5028.5, 52.0, 4868.5)), 9, 44, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.faceLocation(new Location(Bukkit.getWorld("world"), 5028.5, 52.0, 4868.5)), 9, 45, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.toggleLightsStage(false, "tudo"), 10, 2, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(3, 0, 0)), 10, 20, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3.meio.clone().add(-3, 0, 0)), 10, 22, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.getNavigator().setTarget(HistoriaMapMeelv3P2.intro_casa), 10, 30, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.despawn(), 10, 36, 4);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.loadConstruction("teatroFechado");
            HistoriaMapMeelv3.NPCmeel.spawn(HistoriaMapMeelv3.meio_frente);
        }, 10, 46, 4);

        new CustomDelay(() -> HistoriaMapMeelv3.NPCintro.spawn(HistoriaMapMeelv3.meio_frente.clone().add(1, 0, 0)), 10, 50, 4);

        new CustomDelay(() -> {
            HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
            HistoriaMapMeelv3.NPCintro.despawn();
            HistoriaMapMeelv3.NPCmeel.despawn();
        }, 11, 0, 0);

        new CustomDelay(() -> {
            Portal.portalCoolDown.add(player.getUniqueId());
            player.sendMessage(Strings.prefix + "§eTeleportando...");
            Utils.playSound(player, Audio.PARAR);
            new BukkitRunnable() {
                @Override
                public void run() {
                    Utils.playSound(player, Audio.PORTAL);
                    Portal.teleporting.add(player.getUniqueId());
                    Portal.noFallDamage.add(player.getUniqueId());
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            player.setVelocity(new Vector(0, 30, 0));
                        }
                    }, 2250);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            player.setVelocity(new Vector(0, 30, 0));
                        }
                    }, 2400);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            final Location dest = location.clone();
                            dest.setY(dest.getY() + 100);
                            dest.setDirection(player.getLocation().getDirection());
                            if (Utils.isMeelOn()) {
                                if (Utils.getMeel().getName().equalsIgnoreCase(player.getName())) {
                                    MoveEvent.teleporting = true;
                                }
                            }
                            player.teleport(dest);
                            final Warp warp = Warp.FINAL;
                            if (warp != null) {
                                Profile.getProfile(player.getName()).setLocation(warp);
                                CheckpointManager.resetCurrentCheckPoint(player);
                                if (CoreOverwatch.recording) {
                                    if (CoreOverwatch.currentState != null) {
                                        CoreOverwatch.currentState.setMessage(player.getName() + " entrou no portal para a warp " + warp.getName());
                                    }
                                }
                            }
                            MoveEvent.teleporting = false;
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 3 * 20);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.setVelocity(new Vector(0, -30, 0));
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 65L);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Portal.noFallDamage.remove(player.getUniqueId());
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 168L);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Portal.portalCoolDown.remove(player.getUniqueId());
                            MapFinal.start(player);
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 200L);
                }
            }.runTaskLater(MapMeelMain.getPlugin(), 20);
        }, 11, 5, 0);

    }

}
