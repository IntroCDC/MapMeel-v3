package br.com.introcdc.mapmeelv3;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Updater {

    public static ArrayList<UUID> Invis = new ArrayList<>();

    public static void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    Profile.getProfile(player.getName()).addTime();
                    String header = "";
                    header += "\n" + ScoreManager.randomString() + "\n\n§fWarp atual: §a" + Profile.getProfile(player.getName()).getLocation().getName();
                    header += "\n§fTotal de Fails: §c" + Profile.getProfile(player.getName()).getFails() + "\n";
                    header += "§fSeu ping: §a" + Utils.getPing(player) + "ms\n";
                    header += Utils.getServerTPS() + "\n";
                    String footer = "\n";
                    if (Profile.getProfile(player.getName()).getLocation().getName().contains("1")) {
                        String message = "";
                        message += (Nivel.L_1A.isCleared() ? "§2§l" : "§4§l") + "1A §f- ";
                        message += (Nivel.L_1B.isCleared() ? "§2§l" : "§4§l") + "1B §f- ";
                        message += (Nivel.L_1C.isCleared() ? "§2§l" : "§4§l") + "1C §f- ";
                        message += (Nivel.L_1D.isCleared() ? "§2§l" : "§4§l") + "1D §f- ";
                        message += (Nivel.EASTEREGG_1.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-1";
                        footer += (Nivel.L_1A.isCleared() ? "§2§l" : "§4§l") + "1A §f- ";
                        footer += (Nivel.L_1B.isCleared() ? "§2§l" : "§4§l") + "1B §f- ";
                        footer += (Nivel.L_1C.isCleared() ? "§2§l" : "§4§l") + "1C §f- ";
                        footer += (Nivel.L_1D.isCleared() ? "§2§l" : "§4§l") + "1D §f- ";
                        footer += (Nivel.EASTEREGG_1.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-1\n";
                        if (!Profile.getProfile(player.getName()).getLocation().equals(Warp.EASTEREGG_2)) {
                            Utils.sendActionBar(player, message);
                        }
                    } else if (Profile.getProfile(player.getName()).getLocation().getName().contains("2")) {
                        String message = "";
                        message += (Nivel.L_2A.isCleared() ? "§2§l" : "§4§l") + "2A §f- ";
                        message += (Nivel.L_2B.isCleared() ? "§2§l" : "§4§l") + "2B §f- ";
                        message += (Nivel.L_2C.isCleared() ? "§2§l" : "§4§l") + "2C §f- ";
                        message += (Nivel.L_2D.isCleared() ? "§2§l" : "§4§l") + "2D §f- ";
                        message += (Nivel.EASTEREGG_2.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-2";
                        footer += (Nivel.L_2A.isCleared() ? "§2§l" : "§4§l") + "2A §f- ";
                        footer += (Nivel.L_2B.isCleared() ? "§2§l" : "§4§l") + "2B §f- ";
                        footer += (Nivel.L_2C.isCleared() ? "§2§l" : "§4§l") + "2C §f- ";
                        footer += (Nivel.L_2D.isCleared() ? "§2§l" : "§4§l") + "2D §f- ";
                        footer += (Nivel.EASTEREGG_2.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-2\n";
                        if (!Profile.getProfile(player.getName()).getLocation().equals(Warp.EASTEREGG_2)) {
                            Utils.sendActionBar(player, message);
                        }
                    } else if (Profile.getProfile(player.getName()).getLocation().getName().contains("3")) {
                        String message = "";
                        message += (Nivel.L_3A.isCleared() ? "§2§l" : "§4§l") + "3A §f- ";
                        message += (Nivel.L_3B.isCleared() ? "§2§l" : "§4§l") + "3B §f- ";
                        message += (Nivel.L_3C.isCleared() ? "§2§l" : "§4§l") + "3C §f- ";
                        message += (Nivel.L_3D.isCleared() ? "§2§l" : "§4§l") + "3D §f- ";
                        message += (Nivel.EASTEREGG_3.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-3";
                        footer += (Nivel.L_3A.isCleared() ? "§2§l" : "§4§l") + "3A §f- ";
                        footer += (Nivel.L_3B.isCleared() ? "§2§l" : "§4§l") + "3B §f- ";
                        footer += (Nivel.L_3C.isCleared() ? "§2§l" : "§4§l") + "3C §f- ";
                        footer += (Nivel.L_3D.isCleared() ? "§2§l" : "§4§l") + "3D §f- ";
                        footer += (Nivel.EASTEREGG_3.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-3\n";
                        if (!Profile.getProfile(player.getName()).getLocation().equals(Warp.EASTEREGG_2)) {
                            Utils.sendActionBar(player, message);
                        }
                    } else if (Profile.getProfile(player.getName()).getLocation().getName().contains("4")) {
                        String message = "";
                        message += (Nivel.L_4A.isCleared() ? "§2§l" : "§4§l") + "4A §f- ";
                        message += (Nivel.L_4B.isCleared() ? "§2§l" : "§4§l") + "4B §f- ";
                        message += (Nivel.L_4C.isCleared() ? "§2§l" : "§4§l") + "4C §f- ";
                        message += (Nivel.L_4D.isCleared() ? "§2§l" : "§4§l") + "4D §f- ";
                        message += (Nivel.EASTEREGG_4.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-4";
                        footer += (Nivel.L_4A.isCleared() ? "§2§l" : "§4§l") + "4A §f- ";
                        footer += (Nivel.L_4B.isCleared() ? "§2§l" : "§4§l") + "4B §f- ";
                        footer += (Nivel.L_4C.isCleared() ? "§2§l" : "§4§l") + "4C §f- ";
                        footer += (Nivel.L_4D.isCleared() ? "§2§l" : "§4§l") + "4D §f- ";
                        footer += (Nivel.EASTEREGG_4.isCleared() ? "§2§l" : "§4§l") + "EasterEgg-4\n";
                        if (!Profile.getProfile(player.getName()).getLocation().equals(Warp.EASTEREGG_2)) {
                            Utils.sendActionBar(player, message);
                        }
                    }
                    footer += "§fTempo em jogo:\n§e" + Utils.sToTime(Profile.getProfile(player.getName()).getTime()) + "\n";
                    Utils.sendTablist(player, header, footer);
                    ScoreManager.scoreboard(player);
                    if (Updater.Invis.contains(player.getUniqueId())) {
                        for (final Player players : Bukkit.getOnlinePlayers()) {
                            if (Profile.getProfile(players.getName()).getCargo().equals(Cargo.ADMIN)) {
                                if (!players.canSee(player)) {
                                    players.showPlayer(player);
                                }
                            } else {
                                if (players.canSee(player)) {
                                    players.hidePlayer(player);
                                }
                            }
                        }
                    } else {
                        for (final Player players : Bukkit.getOnlinePlayers()) {
                            if (!players.canSee(player)) {
                                players.showPlayer(player);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 20);
    }

    public static void updateSoundMaps() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    if (Profile.getProfile(player.getName()).isAudioFundoOn() && Profile.getProfile(player.getName()).getLocation().getFundo() != null) {
                        Utils.playSound(player, Audio.PARAR);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Utils.playSound(player, Profile.getProfile(player.getName()).getLocation().getFundo());
                            }
                        }.runTaskLater(MapMeelMain.getPlugin(), 20);
                    }
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 0, 7 * 60 * 20);
    }

}
