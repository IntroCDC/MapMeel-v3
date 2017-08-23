package br.com.introcdc.mapmeelv3.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.Audio;
import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.CheckpointManager;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.Warp;
import br.com.introcdc.mapmeelv3.listeners.MoveEvent;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandWarp implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
                return false;
            }
        }
        if (args.length == 0) {
            sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [warp] [nick(OPCIONAL)]");
            final ArrayList<String> names = new ArrayList<>();
            for (final Warp a : Warp.values()) {
                names.add(a.getName());
            }
            sender.sendMessage(Strings.prefix + "§fWarps: " + names.toString().replace("[", "").replace("]", ""));
            return false;
        }
        if (!Warp.existsWarp(args[0])) {
            sender.sendMessage(Strings.prefix + "§cWarp inexistente!");
            final ArrayList<String> names = new ArrayList<>();
            for (final Warp a : Warp.values()) {
                names.add(a.getName());
            }
            sender.sendMessage(Strings.prefix + "§fWarps: " + names.toString().replace("[", "").replace("]", ""));
            return false;
        }
        final Warp warp = Warp.byName(args[0]);
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Strings.prefix + "§cApenas jogadores inGame podem executar este comando!");
                return false;
            }
            ((Player) sender).teleport(warp.getLocation());
            Profile.getProfile(sender.getName()).setLocation(warp);
            sender.sendMessage(Strings.prefix + "§fVocê teleportou para a warp §a" + warp.getName() + "§f com sucesso!");
            if (Profile.getProfile(sender.getName()).isAudioFundoOn()) {
                Utils.playSound((Player) sender, Audio.PARAR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.playSound((Player) sender, Profile.getProfile(sender.getName()).getLocation().getFundo());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 20);
            }
        } else {
            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(Strings.prefix + "§cEste jogador está offline!");
                return false;
            }
            final Player p = Bukkit.getPlayer(args[1]);
            if (sender instanceof Player) {
                if (Utils.isMeelOn()) {
                    if (Utils.getMeel().getName().equalsIgnoreCase(p.getName())) {
                        return false;
                    }
                }
            } else {
                if (Utils.isMeelOn()) {
                    if (Utils.getMeel().getName().equalsIgnoreCase(p.getName())) {
                        MoveEvent.teleporting = true;
                    }
                }
            }
            p.teleport(warp.getLocation());
            Profile.getProfile(p.getName()).setLocation(warp);
            CheckpointManager.resetCurrentCheckPoint(p);
            sender.sendMessage(Strings.prefix + "§fVocê teleportou §a" + p.getName() + "§f para a warp §a" + warp.getName() + "§f com sucesso!");
            if (Profile.getProfile(p.getName()).isAudioFundoOn()) {
                Utils.playSound(p, Audio.PARAR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Utils.playSound(p, Profile.getProfile(p.getName()).getLocation().getFundo());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 20);
            }
        }
        return false;
    }

}
