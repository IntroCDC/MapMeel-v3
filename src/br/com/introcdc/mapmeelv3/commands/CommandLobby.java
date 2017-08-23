package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.Audio;
import br.com.introcdc.mapmeelv3.CheckpointManager;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.Warp;
import br.com.introcdc.mapmeelv3.listeners.MoveEvent;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandLobby implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        final Player player = (Player) sender;
        final Profile profile = Profile.getProfile(sender.getName());
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(sender.getName())) {
                MoveEvent.teleporting = true;
            }
        }
        if (player.getLocation().clone().add(0, -1, 0).getBlock().getType().equals(Material.AIR) || player.getLocation().clone().add(0, -1, 0).getBlock().getType().toString().toUpperCase().contains("WATER") || player.getFireTicks() > 0) {
            sender.sendMessage(Strings.prefix + "§fFique em um local seguro e parado!");
            return false;
        }
        if (profile.getLocation().getName().toLowerCase().contains("1") && !profile.getLocation().getName().toLowerCase().contains("lobby") && !profile.getLocation().getName().toLowerCase().contains("easteregg")) {
            final Warp warp = Warp.LOBBY_1;
            player.teleport(warp.getLocation());
            profile.setLocation(warp);
            CheckpointManager.resetCurrentCheckPoint(player);
            sender.sendMessage(Strings.prefix + "§fVocê teleportou-se para o lobby 1!");
            if (profile.isAudioFundoOn()) {
                Utils.playSound(player, Audio.PARAR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.playSound(player, profile.getLocation().getFundo());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 20);
            }
        } else if (profile.getLocation().getName().toLowerCase().contains("2") && !profile.getLocation().getName().toLowerCase().contains("lobby") && !profile.getLocation().getName().toLowerCase().contains("easteregg")) {
            final Warp warp = Warp.LOBBY_2;
            player.teleport(warp.getLocation());
            profile.setLocation(warp);
            CheckpointManager.resetCurrentCheckPoint(player);
            sender.sendMessage(Strings.prefix + "§fVocê teleportou-se para o lobby 2!");
            if (profile.isAudioFundoOn()) {
                Utils.playSound(player, Audio.PARAR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.playSound(player, profile.getLocation().getFundo());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 20);
            }
        } else if (profile.getLocation().getName().toLowerCase().contains("3") && !profile.getLocation().getName().toLowerCase().contains("lobby") && !profile.getLocation().getName().toLowerCase().contains("easteregg")) {
            final Warp warp = Warp.LOBBY_3;
            player.teleport(warp.getLocation());
            profile.setLocation(warp);
            CheckpointManager.resetCurrentCheckPoint(player);
            sender.sendMessage(Strings.prefix + "§fVocê teleportou-se para o lobby 3!");
            if (profile.isAudioFundoOn()) {
                Utils.playSound(player, Audio.PARAR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.playSound(player, profile.getLocation().getFundo());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 20);
            }
        } else if (profile.getLocation().getName().toLowerCase().contains("4") && !profile.getLocation().getName().toLowerCase().contains("lobby") && !profile.getLocation().getName().toLowerCase().contains("easteregg")) {
            final Warp warp = Warp.LOBBY_4;
            player.teleport(warp.getLocation());
            profile.setLocation(warp);
            CheckpointManager.resetCurrentCheckPoint(player);
            sender.sendMessage(Strings.prefix + "§fVocê teleportou-se para o lobby 4!");
            if (profile.isAudioFundoOn()) {
                Utils.playSound(player, Audio.PARAR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.playSound(player, profile.getLocation().getFundo());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 20);
            }
        } else {
            sender.sendMessage(Strings.prefix + "§cNesta warp não é possível teleporta-se para o lobby!");
        }
        return false;
    }

}
