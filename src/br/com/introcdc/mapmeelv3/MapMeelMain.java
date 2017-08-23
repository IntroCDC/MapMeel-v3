package br.com.introcdc.mapmeelv3;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.world.DataException;

import br.com.introcdc.mapmeelv3.commands.CommandAdmin;
import br.com.introcdc.mapmeelv3.commands.CommandAudio;
import br.com.introcdc.mapmeelv3.commands.CommandBloqueador;
import br.com.introcdc.mapmeelv3.commands.CommandCheckpoint;
import br.com.introcdc.mapmeelv3.commands.CommandInvSee;
import br.com.introcdc.mapmeelv3.commands.CommandInvis;
import br.com.introcdc.mapmeelv3.commands.CommandLobby;
import br.com.introcdc.mapmeelv3.commands.CommandMeel;
import br.com.introcdc.mapmeelv3.commands.CommandOverwatch;
import br.com.introcdc.mapmeelv3.commands.CommandPreferencias;
import br.com.introcdc.mapmeelv3.commands.CommandResetALL;
import br.com.introcdc.mapmeelv3.commands.CommandSearchBlock;
import br.com.introcdc.mapmeelv3.commands.CommandSetCargo;
import br.com.introcdc.mapmeelv3.commands.CommandTeatro;
import br.com.introcdc.mapmeelv3.commands.CommandTop;
import br.com.introcdc.mapmeelv3.commands.CommandVis;
import br.com.introcdc.mapmeelv3.commands.CommandWarp;
import br.com.introcdc.mapmeelv3.commands.CommandWorldTeleport;
import br.com.introcdc.mapmeelv3.listeners.BlockDamage;
import br.com.introcdc.mapmeelv3.listeners.BlockPlace;
import br.com.introcdc.mapmeelv3.listeners.ChatEvent;
import br.com.introcdc.mapmeelv3.listeners.CommandPreProcess;
import br.com.introcdc.mapmeelv3.listeners.DamageEvent;
import br.com.introcdc.mapmeelv3.listeners.DeathRespawn;
import br.com.introcdc.mapmeelv3.listeners.EntitySpawn;
import br.com.introcdc.mapmeelv3.listeners.InteractEvent;
import br.com.introcdc.mapmeelv3.listeners.InventoryClick;
import br.com.introcdc.mapmeelv3.listeners.LeaveJoin;
import br.com.introcdc.mapmeelv3.listeners.MotdEvent;
import br.com.introcdc.mapmeelv3.listeners.MoveEvent;
import br.com.introcdc.mapmeelv3.listeners.NPCClick;
import br.com.introcdc.mapmeelv3.listeners.WorldEvents;
import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class MapMeelMain extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return MapMeelMain.plugin;
    }

    @Override
    public void onDisable() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Strings.prefix + "§4§l§m==============================");
            player.sendMessage(Strings.prefix + "§c§lStatus: §f§lPlugin descarregado com sucesso!");
            player.sendMessage(Strings.prefix + "§c§lPlugin: §f§l" + MapMeelMain.plugin.getName());
            player.sendMessage(Strings.prefix + "§c§lVersão (Bukkit): §f§l" + Bukkit.getServer().getBukkitVersion());
            player.sendMessage(Strings.prefix + "§4§l§m==============================");
        }
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§4§l§m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§lStatus: §f§lPlugin descarregado com sucesso!");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§lPlugin: §f§l" + MapMeelMain.plugin.getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§lVersão (Bukkit): §f§l" + Bukkit.getServer().getBukkitVersion());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§4§l§m==============================");
    }

    @Override
    public void onEnable() {
        MapMeelMain.plugin = this;
        for (final Player player : Bukkit.getOnlinePlayers()) {
            CheckpointManager.getCache().put(player.getUniqueId(), Profile.getProfile(player.getName()).getLocation().getLocation());
            player.sendMessage(Strings.prefix + "§2§l§m==============================");
            player.sendMessage(Strings.prefix + "§a§lStatus: §f§lPlugin carregado com sucesso!");
            player.sendMessage(Strings.prefix + "§a§lPlugin: §f§l" + MapMeelMain.plugin.getName());
            player.sendMessage(Strings.prefix + "§a§lVersão (Bukkit): §f§l" + Bukkit.getServer().getBukkitVersion());
            player.sendMessage(Strings.prefix + "§2§l§m==============================");
        }
        MapMeelMain.plugin.getConfig().options().copyDefaults(true);
        MapMeelMain.plugin.saveConfig();
        Updater.update();
        Updater.updateSoundMaps();
        this.getCommand("setcargo").setExecutor(new CommandSetCargo());
        this.getCommand("invsee").setExecutor(new CommandInvSee());
        this.getCommand("bloqueador").setExecutor(new CommandBloqueador());
        this.getCommand("preferencias").setExecutor(new CommandPreferencias());
        this.getCommand("admin").setExecutor(new CommandAdmin());
        this.getCommand("vis").setExecutor(new CommandVis());
        this.getCommand("invis").setExecutor(new CommandInvis());
        this.getCommand("checkpoint").setExecutor(new CommandCheckpoint());
        this.getCommand("top").setExecutor(new CommandTop());
        this.getCommand("audio").setExecutor(new CommandAudio());
        this.getCommand("warp").setExecutor(new CommandWarp());
        this.getCommand("worldteleport").setExecutor(new CommandWorldTeleport());
        this.getCommand("lobby").setExecutor(new CommandLobby());
        this.getCommand("overwatch").setExecutor(new CommandOverwatch());
        this.getCommand("teatro").setExecutor(new CommandTeatro());
        this.getCommand("meel").setExecutor(new CommandMeel());
        this.getCommand("resetall").setExecutor(new CommandResetALL());
        this.getCommand("searchblock").setExecutor(new CommandSearchBlock());
        Bukkit.getPluginManager().registerEvents(new LeaveJoin(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new NPCClick(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new InteractEvent(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new BlockDamage(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new CoreOverwatch(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new EntitySpawn(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new WorldEvents(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new CommandPreProcess(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new MotdEvent(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new DeathRespawn(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), MapMeelMain.plugin);
        Bukkit.getPluginManager().registerEvents(new DamageEvent(), MapMeelMain.plugin);
        CoreOverwatch.updater();
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§2§l§m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§lStatus: §f§lPlugin carregado com sucesso!");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§lPlugin: §f§l" + MapMeelMain.plugin.getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§lVersão (Bukkit): §f§l" + Bukkit.getServer().getBukkitVersion());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§2§l§m==============================");

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

        Utils.loadAllCheckers();

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc remove all");
            }
        }.runTaskLater(MapMeelMain.getPlugin(), 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                Utils.loadBots();
                try {
                    Utils.loadPortals();
                    Utils.loadCheckpoints();
                } catch (MaxChangedBlocksException | DataException | IOException e) {
                    e.printStackTrace();
                }
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (Profile.getProfile(player.getName()).isAudioFundoOn()) {
                        Utils.playSound(player, Audio.PARAR);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Utils.checkForPlayAudio(player, true);
                            }
                        }.runTaskLater(MapMeelMain.getPlugin(), 20);
                    }
                    if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.CONVIDADO)) {
                        if (!Updater.Invis.contains(player.getUniqueId())) {
                            Updater.Invis.add(player.getUniqueId());
                        }
                    }
                }
                if (Utils.isMeelOn()) {
                    for (final Player player : Bukkit.getOnlinePlayers()) {
                        if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.JOGADOR)) {
                            player.kickPlayer(ScoreManager.randomString() + "\n\n§cA Meel entrou no servidor!");
                        }
                        if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.CONVIDADO)) {
                            if (!Updater.Invis.contains(player.getUniqueId())) {
                                Updater.Invis.add(player.getUniqueId());
                            }
                        }
                        if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.ADMIN)) {
                            if (!Updater.Invis.contains(player.getUniqueId())) {
                                Updater.Invis.add(player.getUniqueId());
                            }
                        }
                    }
                }
                /// CoreOverwatch.startRecord();
            }
        }.runTaskLater(MapMeelMain.getPlugin(), 40);

    }

    @Override
    public void onLoad() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Strings.prefix + "§6§l§m==============================");
            player.sendMessage(Strings.prefix + "§e§lStatus: §f§lCarregando plugin...");
            player.sendMessage(Strings.prefix + "§e§lVersão (Bukkit): §f§l" + Bukkit.getServer().getBukkitVersion());
            player.sendMessage(Strings.prefix + "§6§l§m==============================");
        }
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§6§l§m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§e§lStatus: §f§lCarregando plugin...");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§e§lVersão (Bukkit): §f§l" + Bukkit.getServer().getBukkitVersion());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§6§l§m==============================");
    }

}
