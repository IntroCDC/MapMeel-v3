package br.com.introcdc.mapmeelv3.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import br.com.introcdc.mapmeelv3.Audio;
import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Checkpoint;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Portal;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.Warp;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class MoveEvent implements Listener {

    public static boolean isPlaying = false;
    public static ArrayList<UUID> spongeInvenciliby = new ArrayList<>();

    public static boolean teleporting = false;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
            if (event.getTo().getY() < 0.0) {
                if (Utils.isMeelOn()) {
                    event.getPlayer().chat("/meel");
                } else {
                    Utils.errou(event.getPlayer());
                }
            }
            return;
        }
        if (event.getTo().getY() < 0.0) {
            if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                MoveEvent.spongeInvenciliby.add(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(Strings.prefix + "§cVocê morreu!");
                event.getPlayer().setHealth(20);
                Utils.errou(event.getPlayer());
            }
        }
        for (String ss : Portal.getPortais().keySet()) {
            if (Portal.getPortais().get(ss).getLocation().getWorld().equals(event.getPlayer().getWorld())) {
                if (Portal.getPortais().get(ss).getLocation().clone().add(0, 2, 0).distance(event.getPlayer().getLocation()) <= 1) {
                    if (!Profile.getProfile(event.getPlayer().getName()).getLocation().getName().equalsIgnoreCase(Warp.FINAL.getName())) {
                        if (Utils.isMeelOn()) {
                            if (event.getPlayer().getName().equalsIgnoreCase(Utils.getMeel().getName())) {
                                Portal.getPortais().get(ss).teleport(event.getPlayer());
                            } else {
                                if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.ADMIN)) {
                                    event.getPlayer().sendMessage(Strings.prefix + "§cUtilize o /warp!");
                                } else {
                                    event.getPlayer().sendMessage(Strings.prefix + "§cUtilize o /meel!");
                                }
                            }
                        } else {
                            Portal.getPortais().get(ss).teleport(event.getPlayer());
                        }
                    }
                }
            }
        }
        for (String ss : Checkpoint.checkpoints.keySet()) {
            if (Checkpoint.checkpoints.get(ss).getLocation().getWorld().equals(event.getPlayer().getWorld())) {
                if (Checkpoint.checkpoints.get(ss).getLocation().clone().add(0, 1, 0).distance(event.getPlayer().getLocation()) <= 1) {
                    Checkpoint.checkpoints.get(ss).checkpoint(event.getPlayer());
                }
            }
        }
        if (event.getTo().getBlock().getType().toString().contains("PORTAL") && event.getTo().getWorld().getName().equalsIgnoreCase("world_the_end")) {
            if (Utils.isMeelOn()) {
                if (Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName())) {
                    if (MoveEvent.isPlaying) {
                        return;
                    }
                    MoveEvent.isPlaying = true;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        Utils.playSound(player, Audio.BUILD_A_HOME);
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            MoveEvent.isPlaying = false;
                        }
                    }.runTaskLater(MapMeelMain.getPlugin(), 7 * 60 * 20);
                } else {
                    Utils.playSound(event.getPlayer(), Audio.BUILD_A_HOME);
                }
            } else {
                Utils.playSound(event.getPlayer(), Audio.BUILD_A_HOME);
            }
        }
        if (event.getTo().clone().add(0, -1, 0).getBlock().getType().equals(Material.SPONGE)) {
            event.getPlayer().setVelocity(new Vector(0, 5, 0));
            if (!MoveEvent.spongeInvenciliby.contains(event.getPlayer().getUniqueId())) {
                MoveEvent.spongeInvenciliby.add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getTo().getBlock().getType().equals(Material.TRIPWIRE)) {
            Utils.errou(event.getPlayer());
        }
        if (InteractEvent.Lava.contains(event.getPlayer().getUniqueId())) {
            if (event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(0, -1, 0)).getType().equals(Material.LAVA) || event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(0, -1, 0)).getType().equals(Material.STATIONARY_LAVA)) {
                Material mat1 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(0, -1, 0)).getType();
                Material mat2 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(1, -1, 0)).getType();
                Material mat3 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(0, -1, -1)).getType();
                Material mat4 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(0, -1, 1)).getType();
                Material mat5 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(-1, -1, 0)).getType();
                Material mat6 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(1, -1, 1)).getType();
                Material mat7 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(-1, -1, -1)).getType();
                Material mat8 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(-1, -1, 1)).getType();
                Material mat9 = event.getPlayer().getWorld().getBlockAt(event.getTo().clone().add(1, -1, -1)).getType();
                event.getPlayer().sendBlockChange(event.getTo().clone().add(0, -1, 0), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(1, -1, 0), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(0, -1, -1), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(0, -1, 1), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(-1, -1, 0), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(1, -1, 1), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(-1, -1, -1), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(-1, -1, 1), Material.GLASS, (byte) 0);
                event.getPlayer().sendBlockChange(event.getTo().clone().add(1, -1, -1), Material.GLASS, (byte) 0);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(0, -1, 0), mat1, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(1, -1, 0), mat2, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(0, -1, -1), mat3, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(0, -1, 1), mat4, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(-1, -1, 0), mat5, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(1, -1, 1), mat6, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(-1, -1, -1), mat7, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(-1, -1, 1), mat8, (byte) 0);
                        event.getPlayer().sendBlockChange(event.getTo().clone().add(1, -1, -1), mat9, (byte) 0);
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 100L);
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (Utils.isMeelOn() && !event.getPlayer().getLocation().getBlock().getType().toString().toUpperCase().contains("PORTAL")) {
            if (Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName())) {
                if (!MoveEvent.teleporting) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
