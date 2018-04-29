package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandSearchBlock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
                return false;
            }
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        if (args.length != 2) {
            sender.sendMessage(Strings.prefix + "§cUso correto: /searchblock [raio] [bloco]");
            return false;
        }
        for (Block b : Utils.nearBlocks(p.getLocation(), Integer.parseInt(args[0]))) {
            if (b.getTypeId() == Integer.parseInt(args[1])) {
                sender.sendMessage(Strings.prefix + "§fBloco localizado! X: " + b.getLocation().getX() + " - Y: " + b.getLocation().getY() + " - Z: " + b.getLocation().getZ());
            }
        }
        return false;
    }

}
