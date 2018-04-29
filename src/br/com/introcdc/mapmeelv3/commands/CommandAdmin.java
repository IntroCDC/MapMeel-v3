package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Updater;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandAdmin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "�cEste comando s� pode ser executado por jogadores inGame!");
            return false;
        }
        if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
            sender.sendMessage(Strings.prefix + "�cVoc� n�o tem permiss�o para isto!");
            return false;
        }
        if (Updater.Invis.contains(((Player) sender).getUniqueId())) {
            Updater.Invis.remove(((Player) sender).getUniqueId());
            sender.sendMessage(Strings.prefix + "Voc� saiu do modo Admin e est� vis�vel para todos os jogadores!!");
        } else {
            Updater.Invis.add(((Player) sender).getUniqueId());
            sender.sendMessage(Strings.prefix + "Voc� entrou no modo Admin e est� invis�vel para todos os jogadores!!");
        }
        return false;
    }

}
