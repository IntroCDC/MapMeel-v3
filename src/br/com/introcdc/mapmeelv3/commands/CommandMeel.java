package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.CheckpointManager;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandMeel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        if (Profile.getProfile(p.getName()).getCargo().isBefore(Cargo.CONVIDADO)) {
            p.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
            return false;
        }
        if (!Utils.isMeelOn()) {
            sender.sendMessage(Strings.prefix + "§cA Meel não está online no servidor!");
            return false;
        }
        p.teleport(Utils.getMeel().getLocation());
        Profile.getProfile(sender.getName()).setLocation(Profile.getProfile(Utils.getMeel().getName()).getLocation());
        CheckpointManager.resetCurrentCheckPoint((Player) sender);
        return false;
    }

}
