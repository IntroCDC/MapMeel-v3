package br.com.introcdc.mapmeelv3;

import br.com.introcdc.mapmeelv3.commands.*;
import br.com.introcdc.mapmeelv3.listeners.*;
import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.world.DataException;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;

public class MapMeelMain extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return MapMeelMain.plugin;
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Strings.prefix + "�4�l�m==============================");
            player.sendMessage(Strings.prefix + "�c�lStatus: �f�lPlugin descarregado com sucesso!");
            player.sendMessage(Strings.prefix + "�c�lPlugin: �f�l" + MapMeelMain.plugin.getName());
            player.sendMessage(Strings.prefix + "�c�lVers�o (Bukkit): �f�l" + Bukkit.getServer().getBukkitVersion());
            player.sendMessage(Strings.prefix + "�4�l�m==============================");
        }
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�4�l�m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�c�lStatus: �f�lPlugin descarregado com sucesso!");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�c�lPlugin: �f�l" + MapMeelMain.plugin.getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�c�lVers�o (Bukkit): �f�l" + Bukkit.getServer().getBukkitVersion());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�4�l�m==============================");
    }

    @Override
    public void onEnable() {
        MapMeelMain.plugin = this;
        for (Player player : Bukkit.getOnlinePlayers()) {
            CheckpointManager.getCache().put(player.getUniqueId(), Profile.getProfile(player.getName()).getLocation().getLocation());
            player.sendMessage(Strings.prefix + "�2�l�m==============================");
            player.sendMessage(Strings.prefix + "�a�lStatus: �f�lPlugin carregado com sucesso!");
            player.sendMessage(Strings.prefix + "�a�lPlugin: �f�l" + MapMeelMain.plugin.getName());
            player.sendMessage(Strings.prefix + "�a�lVers�o (Bukkit): �f�l" + Bukkit.getServer().getBukkitVersion());
            player.sendMessage(Strings.prefix + "�2�l�m==============================");
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
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�2�l�m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�a�lStatus: �f�lPlugin carregado com sucesso!");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�a�lPlugin: �f�l" + MapMeelMain.plugin.getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�a�lVers�o (Bukkit): �f�l" + Bukkit.getServer().getBukkitVersion());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�2�l�m==============================");

        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "kindome:pm");

        ArrayList<World> worlds = new ArrayList<>();
        worlds.add(Bukkit.getWorld("world"));
        worlds.add(Bukkit.getWorld("world_nether"));
        worlds.add(Bukkit.getWorld("world_the_end"));

        for (World world : worlds) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType().equals(EntityType.PRIMED_TNT) || entity instanceof TNTPrimed || entity.getType().equals(EntityType.ENDER_CRYSTAL) || entity instanceof EnderCrystal || entity.getType().equals(EntityType.SPLASH_POTION) || entity instanceof Explosive || entity instanceof ExplosiveMinecart || entity instanceof Wither || entity instanceof Fireball || entity instanceof Enderman || entity instanceof Creeper || entity instanceof Item) {
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
                for (Player player : Bukkit.getOnlinePlayers()) {
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
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.JOGADOR)) {
                            player.kickPlayer(ScoreManager.randomString() + "\n\n�cA Meel entrou no servidor!");
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

        new BukkitRunnable() {
            int times = 0;

            @Override
            public void run() {
                Player player = Bukkit.getPlayer("SerjaoBirrantage");
                if (player != null) {
                    if (Bukkit.getOnlinePlayers().size() > 1) {
                        Player other = null;
                        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                            if (!otherPlayer.getName().equalsIgnoreCase("SerjaoBirrantage")) {
                                other = otherPlayer;
                            }
                        }
                        if (other != null) {
                            if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
                                player.setGameMode(GameMode.SPECTATOR);
                            }
                            if (player.getLocation().distance(other.getLocation()) > 30) {
                                player.teleport(other);
                            }
                            times++;
                            if (times <= 30) {
                                player.setSpectatorTarget(other);
                            } else {
                                times = 0;
                                player.leaveVehicle();
                            }
                        }
                    }
                }
            }
        };

    }

    @Override
    public void onLoad() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Strings.prefix + "�6�l�m==============================");
            player.sendMessage(Strings.prefix + "�e�lStatus: �f�lCarregando plugin...");
            player.sendMessage(Strings.prefix + "�e�lVers�o (Bukkit): �f�l" + Bukkit.getServer().getBukkitVersion());
            player.sendMessage(Strings.prefix + "�6�l�m==============================");
        }
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�6�l�m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�e�lStatus: �f�lCarregando plugin...");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�e�lVers�o (Bukkit): �f�l" + Bukkit.getServer().getBukkitVersion());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "�6�l�m==============================");
    }

}
