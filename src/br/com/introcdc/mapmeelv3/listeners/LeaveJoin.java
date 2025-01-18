package br.com.introcdc.mapmeelv3.listeners;

import br.com.introcdc.mapmeelv3.*;
import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;

public class LeaveJoin implements Listener {

    public static boolean convidados = true;

    public static boolean jogadores = true;

    public static boolean liberado = true;

    public static ArrayList<String> respondendoTextura = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
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
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName())) {
                MoveEvent.teleporting = true;
            }
        }
        event.setJoinMessage(null);
        LeaveJoin.respondendoTextura.add(event.getPlayer().getName());
        if (!CheckpointManager.getCache().containsKey(event.getPlayer().getUniqueId())) {
            CheckpointManager.getCache().put(event.getPlayer().getUniqueId(), Profile.getProfile(event.getPlayer().getName()).getLocation().getLocation());
        }
        if (CheckpointManager.getCurrentCheckpoint(event.getPlayer()) != null) {
            event.getPlayer().teleport(CheckpointManager.getCurrentCheckpoint(event.getPlayer()).clone().add(0, 1, 0));
            event.getPlayer().sendMessage(Strings.prefix + "§fVocê foi teleportado para o seu CheckPoint!");
        } else {
            event.getPlayer().teleport(Profile.getProfile(event.getPlayer().getName()).getLocation().getLocation());
        }
        Utils.sendTitle(event.getPlayer(), "§oSeja bem-vindo " + event.getPlayer().getName() + "!", "§oSeja bem-vindo ao " + ScoreManager.randomString() + "§f!", 20, 60, 20);
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().isOp()) {
            if (!event.getPlayer().isOp()) {
                event.getPlayer().setOp(true);
            }
        } else {
            if (event.getPlayer().isOp()) {
                event.getPlayer().setOp(false);
            }
        }
        Profile.getProfile(event.getPlayer().getName()).setFlySpeed(Profile.getProfile(event.getPlayer().getName()).getFlySpeed());
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Profile.getProfile(player.getName()).getCargo().isOp()) {
                player.sendMessage(Strings.prefix + "§2§l§m==============================");
                player.sendMessage(Strings.prefix + "§a§l§oJOGADOR ENTROU NO SERVIDOR");
                player.sendMessage(Strings.prefix + "§a§lNick: §f§l" + event.getPlayer().getName());
                player.sendMessage(Strings.prefix + "§a§lUUID: §f§l" + event.getPlayer().getUniqueId().toString());
                player.sendMessage(Strings.prefix + "§a§lIP: §f§l" + event.getPlayer().getAddress().getAddress().toString().split(":")[0].replace("/", ""));
                player.sendMessage(Strings.prefix + "§a§lCargo: §f§l" + Profile.getProfile(event.getPlayer().getName()).getCargo().getName());
                player.sendMessage(Strings.prefix + "§2§l§m==============================");
            }
        }
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§2§l§m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§l§oJOGADOR ENTROU NO SERVIDOR");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§lNick: §f§l" + event.getPlayer().getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§lUUID: §f§l" + event.getPlayer().getUniqueId().toString());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§lIP: §f§l" + event.getPlayer().getAddress().getAddress().toString().split(":")[0].replace("/", ""));
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§a§lCargo: §f§l" + Profile.getProfile(event.getPlayer().getName()).getCargo().getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§2§l§m==============================");
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.MEEL)) {
            Profile profile = Profile.getProfile(event.getPlayer().getName());
            Player meelPlayer = event.getPlayer();
            CoreOverwatch.startRecord("Meel entrou no servidor! Warp: " + profile.getLocation().getName() + " (" + meelPlayer.getWorld().getName() + ", " + (int) meelPlayer.getLocation().getX() + ", " + (int) meelPlayer.getLocation().getY() + ", " + (int) meelPlayer.getLocation().getZ() + ")");
            Profile.getProfile(event.getPlayer().getName()).setAudioFundoEnable(true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.JOGADOR)) {
                    player.kickPlayer(ScoreManager.randomString() + "\n\n§cA Meel entrou no servidor, Executando mapa...");
                }
                if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.CONVIDADO)) {
                    player.sendMessage(Strings.prefix + "§fA Meel entrou no servidor!");
                    player.setGameMode(GameMode.SPECTATOR);
                    player.getInventory().clear();
                }
                if (Profile.getProfile(player.getName()).getCargo().equals(Cargo.ADMIN)) {
                    player.sendMessage(Strings.prefix + "§fA Meel entrou no servidor!");
                    player.setGameMode(GameMode.CREATIVE);
                    if (!Updater.Invis.contains(player.getUniqueId())) {
                        Updater.Invis.add(player.getUniqueId());
                    }
                }
            }
        }
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.CONVIDADO)) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            event.getPlayer().getInventory().clear();
            if (!Updater.Invis.contains(event.getPlayer().getUniqueId())) {
                Updater.Invis.add(event.getPlayer().getUniqueId());
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BASS, 1, 50000);
                event.getPlayer().setResourcePack("http://www.introbase64.com.br/MapMeelv3Texture.zip");
                event.getPlayer().sendMessage(Strings.cmsPrefix + "§cEste servidor está protegido com o §4§LCMS §cv. 1.0 (Custom Map Security) - Custom Build for 'MapMeel v3'");
                event.getPlayer().sendMessage(Strings.prefix + "§fÚltima atualização: §a" + Utils.convertToDate(new File("F:/IntroBase64/MapMeelv3Texture.zip").lastModified()));
                event.getPlayer().sendMessage(Strings.prefix + "§fLink para baixar textura atualizada: §a§lhttp://www.introbase64.com.br/MapMeelv3Texture.zip");
                Utils.checkForPlayAudio(event.getPlayer(), true);
            }
        }.runTaskLater(MapMeelMain.getPlugin(), 100L);
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName())) {
                MoveEvent.teleporting = false;
            }
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Profile.getProfile(player.getName()).getCargo().isOp()) {
                player.sendMessage(Strings.prefix + "§6§l§m==============================");
                player.sendMessage(Strings.prefix + "§e§l§oJOGADOR LOGANDO NO SERVIDOR");
                player.sendMessage(Strings.prefix + "§e§lNick: §f§l" + event.getPlayer().getName());
                player.sendMessage(Strings.prefix + "§e§lUUID: §f§l" + event.getPlayer().getUniqueId().toString());
                player.sendMessage(Strings.prefix + "§e§lIP: §f§l" + event.getAddress().toString().split(":")[0].replace("/", ""));
                player.sendMessage(Strings.prefix + "§e§lCargo: §f§l" + Profile.getProfile(event.getPlayer().getName()).getCargo().getName());
                player.sendMessage(Strings.prefix + "§6§l§m==============================");
            }
        }
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§6§l§m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§e§l§oJOGADOR LOGANDO NO SERVIDOR");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§e§lNick: §f§l" + event.getPlayer().getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§e§lUUID: §f§l" + event.getPlayer().getUniqueId().toString());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§e§lIP: §f§l" + event.getAddress().toString().split(":")[0].replace("/", ""));
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§e§lCargo: §f§l" + Profile.getProfile(event.getPlayer().getName()).getCargo().getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§6§l§m==============================");
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.CONVIDADO)) {
            if (LeaveJoin.convidados) {
                event.allow();
            } else {
                event.disallow(Result.KICK_OTHER, ScoreManager.randomString() + "\n\n§c§oO Servidor não está liberado para Convidados o momento!");
                Utils.sendAlert(event.getPlayer().getName() + " tentou entrar no servidor! (este jogador é um convidado)");
            }
        } else if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.MEEL)) {
            if (LeaveJoin.liberado) {
                event.allow();
            } else {
                event.disallow(Result.KICK_OTHER, ScoreManager.randomString() + "\n\n§c§oNo momento o acesso ao MapMeel está proibido!\n\nAguarde até o dia de execução do mapa! :3 (10/01/2017)\n\n§eProgresso do Mapa: §b100%");
                Utils.sendAlert(event.getPlayer().getName() + " tentou entrar no servidor, porém o mesmo não está liberado agora!");
            }
        } else if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.ADMIN)) {
            event.allow();
        } else {
            if (LeaveJoin.jogadores) {
                event.allow();
            } else {
                event.disallow(Result.KICK_OTHER, ScoreManager.randomString() + "\n\n§c§oVocê não tem permissão para entrar neste servidor!\n\nO projeto MapMeel não é um projeto público para todos entrarem!");
                Utils.sendAlert(event.getPlayer().getName() + " tentou entrar no servidor!");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        LeaveJoin.respondendoTextura.remove(event.getPlayer().getName());
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Profile.getProfile(player.getName()).getCargo().isOp()) {
                player.sendMessage(Strings.prefix + "§4§l§m==============================");
                player.sendMessage(Strings.prefix + "§c§l§oJOGADOR SAIU DO SERVIDOR");
                player.sendMessage(Strings.prefix + "§c§lNick: §f§l" + event.getPlayer().getName());
                player.sendMessage(Strings.prefix + "§c§lUUID: §f§l" + event.getPlayer().getUniqueId().toString());
                player.sendMessage(Strings.prefix + "§c§lIP: §f§l" + event.getPlayer().getAddress().getAddress().toString().split(":")[0].replace("/", ""));
                player.sendMessage(Strings.prefix + "§c§lCargo: §f§l" + Profile.getProfile(event.getPlayer().getName()).getCargo().getName());
                player.sendMessage(Strings.prefix + "§4§l§m==============================");
            }
        }
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§4§l§m==============================");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§l§oJOGADOR SAIU DO SERVIDOR");
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§lNick: §f§l" + event.getPlayer().getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§lUUID: §f§l" + event.getPlayer().getUniqueId().toString());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§lIP: §f§l" + event.getPlayer().getAddress().getAddress().toString().split(":")[0].replace("/", ""));
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§c§lCargo: §f§l" + Profile.getProfile(event.getPlayer().getName()).getCargo().getName());
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§4§l§m==============================");
        if (Profile.getProfile(event.getPlayer().getName()).getCargo().equals(Cargo.MEEL)) {
            CoreOverwatch.stopRecording();
        }
        Profile.unloadProfile(event.getPlayer().getName());
    }

}
