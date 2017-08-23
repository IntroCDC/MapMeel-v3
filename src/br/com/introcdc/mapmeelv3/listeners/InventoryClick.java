package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.introcdc.mapmeelv3.Audio;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (event.getCurrentItem().getType().equals(Material.TNT)) {
            event.setCancelled(true);
            event.getCurrentItem().setType(Material.AIR);
            player.getInventory().clear();
            return;
        }
        if (event.getInventory().getName().startsWith("Preferencias")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Escolher Cor")) {
                if (event.getCurrentItem().getDurability() == 0) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§a§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §a§lVERDE CLARO§f!");
                    event.getCurrentItem().setDurability((short) 5);
                } else if (event.getCurrentItem().getDurability() == 5) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§b§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §b§lAZUL CLARO§f!");
                    event.getCurrentItem().setDurability((short) 3);
                } else if (event.getCurrentItem().getDurability() == 3) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§c§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §c§lVERMELHO CLARO§f!");
                    event.getCurrentItem().setDurability((short) 2);
                } else if (event.getCurrentItem().getDurability() == 2) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§d§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §d§lROSA§f!");
                    event.getCurrentItem().setDurability((short) 6);
                } else if (event.getCurrentItem().getDurability() == 6) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§e§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §e§lAMARELO§f!");
                    event.getCurrentItem().setDurability((short) 4);
                } else if (event.getCurrentItem().getDurability() == 4) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§1§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §1§lAZUL ESCURO§f!");
                    event.getCurrentItem().setDurability((short) 11);
                } else if (event.getCurrentItem().getDurability() == 11) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§2§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §2§lVERDE ESCURO§f!");
                    event.getCurrentItem().setDurability((short) 13);
                } else if (event.getCurrentItem().getDurability() == 13) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§3§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §3§lCIANO§f!");
                    event.getCurrentItem().setDurability((short) 9);
                } else if (event.getCurrentItem().getDurability() == 9) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§4§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §4§lVERMELHO ESCURO§f!");
                    event.getCurrentItem().setDurability((short) 14);
                } else if (event.getCurrentItem().getDurability() == 14) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§5§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §5§lROXO§f!");
                    event.getCurrentItem().setDurability((short) 10);
                } else if (event.getCurrentItem().getDurability() == 10) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§6§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §6§lLARANJA§f!");
                    event.getCurrentItem().setDurability((short) 1);
                } else if (event.getCurrentItem().getDurability() == 1) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§7§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §7§lCINZA CLARO§f!");
                    event.getCurrentItem().setDurability((short) 8);
                } else if (event.getCurrentItem().getDurability() == 8) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§8§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §8§lCINZA ESCURO§f!");
                    event.getCurrentItem().setDurability((short) 7);
                } else if (event.getCurrentItem().getDurability() == 7) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§9§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §9§lAZUL§f!");
                    event.getCurrentItem().setDurability((short) 12);
                } else if (event.getCurrentItem().getDurability() == 12) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§0§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §0§lPRETO§f!");
                    event.getCurrentItem().setDurability((short) 15);
                } else if (event.getCurrentItem().getDurability() == 15) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                    Profile.getProfile(player.getName()).setColor("§f§l");
                    player.sendMessage(Strings.prefix + "§fVocê alterou sua cor para §f§lBRANCO§f!");
                    event.getCurrentItem().setDurability((short) 0);
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("FlySpeed")) {
                final Inventory inv = Bukkit.createInventory(null, 9, "Preferencias (" + player.getName() + ")");
                for (int i = 1; i <= 9; i++) {
                    final ItemStack flySpeed = new ItemStack(Material.FEATHER);
                    final ItemMeta flySpeedMeta = flySpeed.getItemMeta();
                    flySpeedMeta.setDisplayName("x" + i);
                    flySpeed.setItemMeta(flySpeedMeta);
                    inv.addItem(flySpeed);
                }
                player.openInventory(inv);
            } else if (event.getCurrentItem().getType().equals(Material.FEATHER) && event.getCurrentItem().getItemMeta().getDisplayName().startsWith("x")) {
                final String numero = "0." + event.getCurrentItem().getItemMeta().getDisplayName().replace("x", "");
                final double quantify = Double.parseDouble(numero);
                Profile.getProfile(player.getName()).setFlySpeed(quantify);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                player.sendMessage(Strings.prefix + "Seu Fly Speed foi alterado para §a§l" + event.getCurrentItem().getItemMeta().getDisplayName() + "§f!");
                player.closeInventory();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("Áudio de Fundo: ") && event.getCurrentItem().getType().equals(Material.JUKEBOX)) {
                final ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0, 50000);
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Áudio de Fundo: §a§oLigado")) {
                    Profile.getProfile(player.getName()).setAudioFundoEnable(false);
                    itemMeta.setDisplayName("Áudio de Fundo: §c§oDesligado");
                    event.getCurrentItem().setItemMeta(itemMeta);
                    player.sendMessage(Strings.prefix + "§fSeus áudios de fundo foram desligados!");
                    player.updateInventory();
                    Utils.playSound(player, Audio.PARAR);
                } else {
                    Profile.getProfile(player.getName()).setAudioFundoEnable(true);
                    itemMeta.setDisplayName("Áudio de Fundo: §a§oLigado");
                    event.getCurrentItem().setItemMeta(itemMeta);
                    player.sendMessage(Strings.prefix + "§fSeus áudios de fundo foram ligados!");
                    player.updateInventory();
                    Utils.checkForPlayAudio(player, true);
                }
            }
        }
    }

}
