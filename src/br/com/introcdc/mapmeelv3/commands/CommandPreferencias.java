package br.com.introcdc.mapmeelv3.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandPreferencias implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§cApenas jogadores inGame podem executar este comando!");
            return false;
        }
        final Inventory inv = Bukkit.createInventory(null, 36, "Preferencias (" + sender.getName() + ")");
        final ItemStack cor = new ItemStack(Material.WOOL);
        final ItemMeta corMeta = cor.getItemMeta();
        corMeta.setDisplayName("Escolher Cor");
        cor.setItemMeta(corMeta);
        short color = 0;
        if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§a§l")) {
            color = 5;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§b§l")) {
            color = 3;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§c§l")) {
            color = 2;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§d§l")) {
            color = 6;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§e§l")) {
            color = 4;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§1§l")) {
            color = 11;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§2§l")) {
            color = 13;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§3§l")) {
            color = 9;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§4§l")) {
            color = 14;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§5§l")) {
            color = 10;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§6§l")) {
            color = 1;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§7§l")) {
            color = 8;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§8§l")) {
            color = 7;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§9§l")) {
            color = 11;
        } else if (Profile.getProfile(sender.getName()).getColor().equalsIgnoreCase("§0§l")) {
            color = 15;
        }
        cor.setDurability(color);

        final ItemStack flySpeed = new ItemStack(Material.FEATHER);
        final ItemMeta flySpeedMeta = flySpeed.getItemMeta();
        flySpeedMeta.setDisplayName("FlySpeed");
        flySpeed.setItemMeta(flySpeedMeta);

        final ItemStack audioFundo = new ItemStack(Material.JUKEBOX);
        final ItemMeta audioFundoMeta = audioFundo.getItemMeta();
        audioFundoMeta.setDisplayName("Áudio de Fundo: " + (Profile.getProfile(sender.getName()).isAudioFundoOn() ? "§a§oLigado" : "§c§oDesligado"));
        audioFundo.setItemMeta(audioFundoMeta);

        inv.setItem(11, flySpeed);
        inv.setItem(13, cor);
        inv.setItem(15, audioFundo);

        ((Player) sender).openInventory(inv);
        return false;
    }

}
