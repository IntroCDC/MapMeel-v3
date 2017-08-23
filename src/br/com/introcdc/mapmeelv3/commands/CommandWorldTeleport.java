package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandWorldTeleport implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
                return false;
            }
        }
        if (args.length == 0) {
            sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [world/world_nether/world_the_end] (jogador[OPCIONAL])");
        } else if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Strings.prefix + "§cEste comando só pode ser executado por jogadores inGame!");
                return false;
            }
            if (Bukkit.getWorld(args[0]) == null) {
                sender.sendMessage(Strings.prefix + "§cMundo inexistente!");
                return false;
            }
            sender.sendMessage(Strings.prefix + "§7Teleportado para o mundo §a" + Bukkit.getWorld(args[0]).getName() + "§7 com sucesso!");
            ((Player) sender).teleport(new Location(Bukkit.getWorld(args[0]), ((Player) sender).getLocation().getX(), ((Player) sender).getLocation().getY(), ((Player) sender).getLocation().getZ()));
        } else {
            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(Strings.prefix + "§cEste jogador está offline...");
                return false;
            }
            if (Bukkit.getWorld(args[0]) == null) {
                sender.sendMessage(Strings.prefix + "§cMundo inexistente!");
                return false;
            }
            sender.sendMessage(Strings.prefix + "§7Teleportado §a" + Bukkit.getPlayer(args[1]).getName() + "§7 para o mundo §a" + Bukkit.getWorld(args[0]).getName() + "§7 com sucesso!");
            Bukkit.getPlayer(args[1]).teleport(new Location(Bukkit.getWorld(args[0]), Bukkit.getPlayer(args[1]).getLocation().getX(), Bukkit.getPlayer(args[1]).getLocation().getY(), Bukkit.getPlayer(args[1]).getLocation().getZ()));
        }
        return false;
    }

}
