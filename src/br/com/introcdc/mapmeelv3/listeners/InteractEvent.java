package br.com.introcdc.mapmeelv3.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.HistoriaMapMeelv3;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.Warp;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class InteractEvent implements Listener {

    public static ArrayList<UUID> coolDownLava = new ArrayList<>();
    public static ArrayList<UUID> Lava = new ArrayList<>();

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL) && Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.EASTEREGG_2)) {
            if (event.getPlayer().getLocation().getBlock().getType().equals(Material.GOLD_PLATE)) {
                event.setCancelled(true);
                if (Utils.isMeelOn()) {
                    if (Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName())) {
                        HistoriaMapMeelv3.loadBots(true);
                    }
                } else {
                    HistoriaMapMeelv3.loadBots(true);
                }
                return;
            }
        }
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock() != null) {
            if (Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.L_1D)) {
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    event.setCancelled(true);
                }
            }
            if (Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.LOBBY) || Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.EASTEREGG_1) || Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.EASTEREGG_4)) {
                if (Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.EASTEREGG_4) && event.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("world")) {
                    event.setCancelled(true);
                    return;
                } else if (Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.EASTEREGG_4)) {
                    return;
                }
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    event.setCancelled(true);
                    if (Utils.isMeelOn()) {
                        if (!Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName()) && !event.getPlayer().getName().equalsIgnoreCase("Introo")) {
                            return;
                        }
                    }
                    event.getPlayer().sendMessage(Strings.prefix + "§fVocê recebeu um minecart!");
                    final ItemStack item = new ItemStack(Material.MINECART);
                    final ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName("§7Minecart");
                    item.setItemMeta(itemMeta);
                    event.getPlayer().getInventory().addItem(item);
                }
            }
            if (Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.EASTEREGG_1)) {
                if (event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
                    event.setCancelled(true);
                    if (Utils.isMeelOn()) {
                        if (!Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName()) && !event.getPlayer().getName().equalsIgnoreCase("Introo")) {
                            return;
                        }
                    }
                    final ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                    final BookMeta itemMeta = (BookMeta) book.getItemMeta();
                    itemMeta.setPages("Outubro:\nNa época que o IntroBase64 estava ativo, mesmo não sendo bots e sem recebimento de informações, o Intro sempre tinha algumas informações que ele mesmo via.");
                    itemMeta.setDisplayName("Relato 1");
                    itemMeta.setAuthor("MapMeel v3");
                    book.setItemMeta(itemMeta);
                    event.getPlayer().sendMessage(Strings.prefix + "§fVocê recebeu um livro!");
                    event.getPlayer().getInventory().addItem(book);
                }
                if (event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
                    event.setCancelled(true);
                    if (Utils.isMeelOn()) {
                        if (!Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName()) && !event.getPlayer().getName().equalsIgnoreCase("Introo")) {
                            return;
                        }
                    }
                    final ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                    final BookMeta itemMeta = (BookMeta) book.getItemMeta();
                    itemMeta.setPages("Novembro:\nEsta época foi a de mais sofrimento do Intro, que foi a mesma época que várias pessoas tinham parado de falar com ele por causa de suas ações com o IntroBase64, então logo depois de Dezembro, todos voltaram a falar com o Intro e foi o fim do", "IntroBase64...");
                    itemMeta.setDisplayName("Relato 2");
                    itemMeta.setAuthor("MapMeel v3");
                    book.setItemMeta(itemMeta);
                    event.getPlayer().sendMessage(Strings.prefix + "§fVocê recebeu um livro!");
                    event.getPlayer().getInventory().addItem(book);
                }
            }
            if (Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.L_3B)) {
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    event.setCancelled(true);
                    if (Utils.isMeelOn()) {
                        if (!Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName()) && !event.getPlayer().getName().equalsIgnoreCase("Introo")) {
                            return;
                        }
                    }
                    event.getPlayer().sendMessage(Strings.prefix + "§fVocê recebeu o item de andar na lava!");
                    final ItemStack item = new ItemStack(Material.MAGMA_CREAM);
                    final ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName("§7Andar na lava (10s)");
                    item.setItemMeta(itemMeta);
                    event.getPlayer().getInventory().addItem(item);
                }
            }
            if (Profile.getProfile(event.getPlayer().getName()).getLocation().equals(Warp.LOBBY_2)) {
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    event.setCancelled(true);
                    if (Utils.isMeelOn()) {
                        if (!Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName()) && !event.getPlayer().getName().equalsIgnoreCase("Introo")) {
                            return;
                        }
                    }
                    event.getPlayer().sendMessage(Strings.prefix + "§fVocê recebeu o umas gravações e uma cabeça!");
                    final ItemStack recording_1 = new ItemStack(Material.getMaterial(2257));
                    final ItemMeta recording_1Meta = recording_1.getItemMeta();
                    recording_1Meta.setDisplayName("Gravação #1");
                    recording_1.setItemMeta(recording_1Meta);
                    event.getPlayer().getInventory().addItem(recording_1);
                    final ItemStack recording_2 = new ItemStack(Material.getMaterial(2258));
                    final ItemMeta recording_2Meta = recording_2.getItemMeta();
                    recording_2Meta.setDisplayName("Gravação #2");
                    recording_2.setItemMeta(recording_2Meta);
                    event.getPlayer().getInventory().addItem(recording_2);
                    final ItemStack recording_3 = new ItemStack(Material.getMaterial(2259));
                    final ItemMeta recording_3Meta = recording_3.getItemMeta();
                    recording_3Meta.setDisplayName("Gravação #3");
                    recording_3.setItemMeta(recording_3Meta);
                    event.getPlayer().getInventory().addItem(recording_3);
                    final ItemStack cabeca = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    final SkullMeta cabecaMeta = (SkullMeta) cabeca.getItemMeta();
                    cabecaMeta.setOwner("Base64_");
                    cabecaMeta.setDisplayName("Ha Ha Ha Ha Ha");
                    cabeca.setItemMeta(cabecaMeta);
                    event.getPlayer().getInventory().addItem(cabeca);
                }
                if (event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
                    event.setCancelled(true);
                    if (Utils.isMeelOn()) {
                        if (!Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName()) && !event.getPlayer().getName().equalsIgnoreCase("Introo")) {
                            return;
                        }
                    }
                    event.getPlayer().sendMessage(Strings.prefix + "§fVocê recebeu um minecart!");
                    final ItemStack item = new ItemStack(Material.MINECART);
                    final ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName("§7Minecart");
                    item.setItemMeta(itemMeta);
                    event.getPlayer().getInventory().addItem(item);
                }
            }
        }
        if (event.getPlayer().getInventory().getItemInHand() == null || event.getPlayer().getInventory().getItemInHand().getItemMeta() == null || event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7Andar na lava (10s)")) {
                if (InteractEvent.coolDownLava.contains(event.getPlayer().getUniqueId())) {
                    event.getPlayer().sendMessage(Strings.prefix + "§cAguarde para utilizar o item novamente!");
                    return;
                }
                InteractEvent.coolDownLava.add(event.getPlayer().getUniqueId());
                InteractEvent.Lava.add(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(Strings.prefix + "§fAgora você pode andar sobre as Lavas!");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().sendMessage(Strings.prefix + "§fSeu poder de andar sobre as lavas acabou!");
                        InteractEvent.Lava.remove(event.getPlayer().getUniqueId());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 10 * 20);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().sendMessage(Strings.prefix + "§fAgora você pode utilizar o item de lava novamente");
                        InteractEvent.coolDownLava.remove(event.getPlayer().getUniqueId());
                    }
                }.runTaskLater(MapMeelMain.getPlugin(), 30 * 20);
            }
        }
    }

}
