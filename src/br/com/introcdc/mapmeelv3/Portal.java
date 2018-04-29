package br.com.introcdc.mapmeelv3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;

import br.com.introcdc.mapmeelv3.listeners.MoveEvent;
import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;
import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;

public class Portal {

    public static ArrayList<UUID> noFallDamage = new ArrayList<>();
    private static HashMap<String, Portal> portais = new HashMap<>();
    public static ArrayList<UUID> portalCoolDown = new ArrayList<>();

    public static ArrayList<UUID> teleporting = new ArrayList<>();

    public static HashMap<String, Portal> getPortais() {
        return Portal.portais;
    }

    private Location destino;
    private Hologram hologram;
    private Location location;
    private String name;
    private Nivel nivel;

    public Portal(String name, Location destino, Location location, Nivel nivel) throws MaxChangedBlocksException, DataException, IOException {
        this.name = name;
        this.destino = destino;
        this.location = location;
        this.nivel = nivel;
        if (!Portal.getPortais().containsKey(name)) {
            Portal.getPortais().put(name, this);
        }
        this.loadPortal();
    }

    public Location getDestino() {
        return this.destino;
    }

    public Hologram getHologram() {
        return this.hologram;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public void loadPortal() throws DataException, IOException, MaxChangedBlocksException {
        EditSession es = new EditSession(new BukkitWorld(this.location.getWorld()), 999999999);
        CuboidClipboard cc = CuboidClipboard.loadSchematic(new File("portalBase.schematic"));
        com.sk89q.worldedit.Vector origin = new com.sk89q.worldedit.Vector(this.location.getX(), this.location.getY(), this.location.getZ());
        cc.paste(es, origin, true);
        for (Warp warp : Warp.values()) {
            if (warp.getLocation().equals(this.getDestino())) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Portal.this.getHologram() == null) {
                            Portal.this.hologram = HologramAPI.createHologram(Portal.this.location.clone().add(0, 5, 0), "§f§lDestino: §a§l" + warp.getName());
                        }
                        if (Portal.this.hologram.isSpawned()) {
                            Portal.this.hologram.despawn();
                        }
                        if (!Portal.this.hologram.isSpawned()) {
                            Portal.this.hologram.spawn();
                        }
                    }
                }.runTaskTimer(MapMeelMain.getPlugin(), 3 * 20, 10 * 20);
            }
        }
    }

    public void teleport(Player player) {
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            return;
        }
        if (Portal.portalCoolDown.contains(player.getUniqueId())) {
            return;
        }
        if (this.nivel != null) {
            this.nivel.setCleared(true);
        }
        Portal.portalCoolDown.add(player.getUniqueId());
        player.sendMessage(Strings.prefix + "§eTeleportando...");
        Utils.playSound(player, Audio.PARAR);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Portal.this.nivel != null) {
                    Utils.playSound(player, Portal.this.nivel.getWin());
                } else {
                    Utils.playSound(player, Audio.PORTAL);
                }
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
                        Location dest = Portal.this.getDestino().clone();
                        dest.setY(dest.clone().getY() + 100);
                        dest.setDirection(player.getLocation().getDirection());
                        if (Utils.isMeelOn()) {
                            if (Utils.getMeel().getName().equalsIgnoreCase(player.getName())) {
                                MoveEvent.teleporting = true;
                            }
                        }
                        player.teleport(dest);
                        Warp warp = null;
                        for (Warp w : Warp.values()) {
                            if (w.getLocation().equals(Portal.this.getDestino())) {
                                warp = w;
                            }
                        }
                        if (warp != null) {
                            Warp w = warp;
                            Profile.getProfile(player.getName()).setLocation(warp);
                            CheckpointManager.resetCurrentCheckPoint(player);
                            if (Profile.getProfile(player.getName()).isAudioFundoOn()) {
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        Utils.playSound(player, Audio.PARAR);
                                        new Timer().schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                Utils.playSound(player, w.getFundo());
                                            }
                                        }, 1500);
                                    }
                                }, 6000);
                            }
                            if (CoreOverwatch.recording) {
                                if (CoreOverwatch.currentState != null) {
                                    CoreOverwatch.currentState.setMessage(player.getName() + " entrou no portal para a warp " + w.getName());
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
                        if (Portal.this.getName().equalsIgnoreCase("L4_EG4")) {
                            player.kickPlayer(ScoreManager.randomString() + "\n\n§cRelogue no servidor para prevenir bugs com as imagens.");
                        }
                        if (Portal.this.getName().equalsIgnoreCase("L_FINAL")) {
                            HistoriaMapMeelv3P2.startHistory(player);
                        }
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 200L);
            }
        }.runTaskLater(MapMeelMain.getPlugin(), 20);
    }

}
