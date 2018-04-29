package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Updater;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandInvis implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§cEste comando só pode ser executado por jogadores inGame!");
            return false;
        }
        if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
            sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
            return false;
        }
        sender.sendMessage(Strings.prefix + "Você ficou invisível para os jogadores!");
        if (!Updater.Invis.contains(((Player) sender).getUniqueId())) {
            Updater.Invis.add(((Player) sender).getUniqueId());
        }
        return false;
    }

}
