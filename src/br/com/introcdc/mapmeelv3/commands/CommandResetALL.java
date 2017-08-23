package br.com.introcdc.mapmeelv3.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.block.Hopper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.introcdc.mapmeelv3.Bot;
import br.com.introcdc.mapmeelv3.HistoriaMapMeelv3;
import br.com.introcdc.mapmeelv3.Nivel;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.ScoreManager;
import br.com.introcdc.mapmeelv3.Warp;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class CommandResetALL implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!sender.getName().equalsIgnoreCase("Introo")) {
            sender.sendMessage(Strings.prefix + "§cApenas o Intro pode utilizar este comando!");
            return false;
        }
        sender.sendMessage(Strings.prefix + "Resetando mapa...");
        for (final Nivel nivel : Nivel.values()) {
            nivel.setCleared(false);
        }
        HistoriaMapMeelv3.toggleLightsAudience(false);
        HistoriaMapMeelv3.toggleLightsStage(false, "tudo");
        HistoriaMapMeelv3.autoStarted = false;
        HistoriaMapMeelv3.start = false;
        for (final int key : Bot.getBots().keySet()) {
            Bot.getBots().get(key).playDialog(null);
        }
        ((Chest) Bukkit.getWorld("world").getBlockAt(5036, 29, 4874).getState()).getBlockInventory().clear();
        ((Chest) Bukkit.getWorld("world").getBlockAt(5036, 29, 4875).getState()).getBlockInventory().clear();
        final Hopper hopper = (Hopper) Bukkit.getWorld("world").getBlockAt(5036, 28, 4874).getState();
        hopper.getInventory().clear();
        hopper.getInventory().setItem(0, new ItemStack(Material.SNOW_BALL, 2));
        hopper.getInventory().setItem(1, new ItemStack(Material.SNOW_BALL, 1));
        hopper.getInventory().setItem(2, new ItemStack(Material.SNOW_BALL, 1));
        hopper.getInventory().setItem(3, new ItemStack(Material.SNOW_BALL, 1));

        final ItemStack keyEG2 = new ItemStack(Material.PAPER, 2);
        final ItemMeta keyEG2Meta = keyEG2.getItemMeta();
        keyEG2Meta.setDisplayName("2Mel017MapCDC");
        keyEG2.setItemMeta(keyEG2Meta);
        hopper.getInventory().setItem(4, keyEG2);

        ((Chest) Bukkit.getWorld("world").getBlockAt(5001, 63, 4836).getState()).getBlockInventory().clear();
        final ItemStack passEG2p1 = new ItemStack(Material.WRITTEN_BOOK);
        final BookMeta passEG2p1Meta = (BookMeta) passEG2p1.getItemMeta();
        passEG2p1Meta.setDisplayName("2");
        passEG2p1Meta.setAuthor("MapMeel v3");
        passEG2p1Meta.setPages("2º: 017v3");
        passEG2p1.setItemMeta(passEG2p1Meta);
        ((Chest) Bukkit.getWorld("world").getBlockAt(5001, 63, 4836).getState()).getBlockInventory().setItem(13, passEG2p1);

        ((Chest) Bukkit.getWorld("world").getBlockAt(5027, 36, 4879).getState()).getBlockInventory().clear();
        final ItemStack passEG2p2 = new ItemStack(Material.WRITTEN_BOOK);
        final BookMeta passEG2p2Meta = (BookMeta) passEG2p2.getItemMeta();
        passEG2p2Meta.setDisplayName("3");
        passEG2p2Meta.setAuthor("MapMeel v3");
        passEG2p2Meta.setPages("3º: Map");
        passEG2p2.setItemMeta(passEG2p2Meta);
        ((Chest) Bukkit.getWorld("world").getBlockAt(5027, 36, 4879).getState()).getBlockInventory().setItem(13, passEG2p2);

        ((Chest) Bukkit.getWorld("world").getBlockAt(5009, 31, 4934).getState()).getBlockInventory().clear();
        final ItemStack passEG2p3 = new ItemStack(Material.WRITTEN_BOOK);
        final BookMeta passEG2p3Meta = (BookMeta) passEG2p3.getItemMeta();
        passEG2p3Meta.setDisplayName("4");
        passEG2p3Meta.setAuthor("MapMeel v3");
        passEG2p3Meta.setPages("4º: CDC");
        passEG2p3.setItemMeta(passEG2p3Meta);
        ((Chest) Bukkit.getWorld("world").getBlockAt(5009, 31, 4934).getState()).getBlockInventory().setItem(13, passEG2p3);

        ((Chest) Bukkit.getWorld("world").getBlockAt(5019, 37, 4954).getState()).getBlockInventory().clear();
        final ItemStack passEG2p4 = new ItemStack(Material.WRITTEN_BOOK);
        final BookMeta passEG2p4Meta = (BookMeta) passEG2p4.getItemMeta();
        passEG2p4Meta.setDisplayName("1");
        passEG2p4Meta.setAuthor("MapMeel v3");
        passEG2p4Meta.setPages("1º: 2Mel");
        passEG2p4.setItemMeta(passEG2p4Meta);
        ((Chest) Bukkit.getWorld("world").getBlockAt(5019, 37, 4954).getState()).getBlockInventory().setItem(13, passEG2p4);

        final Hopper hopper2 = (Hopper) Bukkit.getWorld("world_the_end").getBlockAt(5809, 49, 4911).getState();
        hopper2.getInventory().clear();
        hopper2.getInventory().setItem(0, new ItemStack(Material.SNOW_BALL, 2));
        hopper2.getInventory().setItem(1, new ItemStack(Material.SNOW_BALL, 1));
        hopper2.getInventory().setItem(2, new ItemStack(Material.SNOW_BALL, 1));
        hopper2.getInventory().setItem(3, new ItemStack(Material.SNOW_BALL, 1));

        final ItemStack keyEG4 = new ItemStack(Material.PAPER, 2);
        final ItemMeta keyEG4Meta = keyEG4.getItemMeta();
        keyEG4Meta.setDisplayName("#TheBasesDarknessBrightness");
        keyEG4.setItemMeta(keyEG4Meta);
        hopper2.getInventory().setItem(4, keyEG4);

        final ArrayList<Chest> papers = new ArrayList<>();
        final ArrayList<Chest> xps = new ArrayList<>();
        papers.add((Chest) Bukkit.getWorld("world").getBlockAt(5031, 31, 4873).getState());
        xps.add((Chest) Bukkit.getWorld("world").getBlockAt(5031, 31, 4871).getState());
        papers.add((Chest) Bukkit.getWorld("world_the_end").getBlockAt(5811, 58, 4911).getState());
        xps.add((Chest) Bukkit.getWorld("world_the_end").getBlockAt(5813, 58, 4911).getState());

        for (final Chest block : papers) {
            final ItemStack paper = new ItemStack(Material.PAPER, 64);
            for (int i = 0; i < 26; i++) {
                block.getBlockInventory().setItem(i, paper);
            }
        }

        for (final Chest block : xps) {
            final ItemStack xp = new ItemStack(Material.EXP_BOTTLE, 64);
            for (int i = 0; i < 26; i++) {
                block.getBlockInventory().setItem(i, xp);
            }
        }

        final ArrayList<World> worlds = new ArrayList<>();
        worlds.add(Bukkit.getWorld("world"));
        worlds.add(Bukkit.getWorld("world_nether"));
        worlds.add(Bukkit.getWorld("world_the_end"));

        for (final World world : worlds) {
            for (final Entity entity : world.getEntities()) {
                if (entity.getType().equals(EntityType.PRIMED_TNT) || entity instanceof TNTPrimed || entity.getType().equals(EntityType.ENDER_CRYSTAL) || entity instanceof EnderCrystal || entity.getType().equals(EntityType.SPLASH_POTION) || entity instanceof Explosive || entity instanceof ExplosiveMinecart || entity instanceof Wither || entity instanceof WitherSkull || entity instanceof Fireball || entity instanceof Enderman || entity instanceof Creeper || entity instanceof Item) {
                    entity.remove();
                }
            }
        }

        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            Profile.getProfile(player.getName()).setLocation(Warp.LOBBY);
            player.kickPlayer(ScoreManager.randomString() + "\n\n§cMapa resetado!");
        }
        sender.sendMessage(Strings.prefix + "§fMapa resetado com sucesso!");
        return false;
    }

}
