package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.introcdc.mapmeelv3.Cargo;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandInvSee implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§cApenas jogadores inGame podem utilizar este comando!");
            return false;
        }
        if (Profile.getProfile(sender.getName()).getCargo().isBefore(Cargo.CONVIDADO)) {
            sender.sendMessage(Strings.prefix + "§cVocê não tem permissão para isto!");
            return false;
        }
        if (args.length != 1) {
            sender.sendMessage(Strings.prefix + "§cUso correto: /" + command.getName() + " [nick]");
            return false;
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(Strings.prefix + "§cEste jogador está offline!");
            return false;
        }
        sender.sendMessage(Strings.prefix + "§fVocê abriu o inventário de §a" + Bukkit.getPlayer(args[0]).getName() + "§f com sucesso!");
        ((Player) sender).playSound(((Player) sender).getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 50000);
        ((Player) sender).openInventory(Bukkit.getPlayer(args[0]).getInventory());
        return false;
    }

}
