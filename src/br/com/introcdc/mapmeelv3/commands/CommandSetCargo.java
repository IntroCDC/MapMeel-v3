package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandSetCargo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.ADMIN)) {
                sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
                return false;
            }
        }
        if (args.length != 2) {
            sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [nick] [jogador/convidado/admin]");
            return false;
        }
        if (args[1].equalsIgnoreCase("Meel")) {
            sender.sendMessage(Strings.prefix + "§cEste cargo não existe!");
            return false;
        }
        if (!Cargo.existCargo(args[1])) {
            sender.sendMessage(Strings.prefix + "§cEste cargo não existe!");
            return false;
        }
        Cargo cargo = Cargo.byName(args[1]);
        String nick = args[0];
        if (Bukkit.getPlayer(nick) != null) {
            nick = Bukkit.getPlayer(nick).getName();
        }
        if (Bukkit.getOfflinePlayer(nick) != null) {
            nick = Bukkit.getOfflinePlayer(nick).getName();
        }
        Profile.getProfile(nick).setCargo(cargo);
        sender.sendMessage(Strings.prefix + "§fVocê alterou o rank de §a" + nick + "§f para §b" + cargo.getName() + "§f com sucesso!");
        return false;
    }

}
