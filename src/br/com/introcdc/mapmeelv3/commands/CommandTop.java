package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandTop implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§cEste comando só pode ser executado por jogadores inGame!");
            return false;
        }
        if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.CONVIDADO)) {
            sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
            return false;
        }
        ((Player) sender).teleport(((Player) sender).getWorld().getHighestBlockAt(((Player) sender).getLocation()).getLocation().clone().add(0, 1, 0));
        sender.sendMessage(Strings.prefix + "Você teleportou para o bloco mais alto onde você está!");
        return false;
    }

}
