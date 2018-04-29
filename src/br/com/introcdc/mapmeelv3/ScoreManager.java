package br.com.introcdc.mapmeelv3;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreManager {

    public static Scoreboard empty = Bukkit.getScoreboardManager().getNewScoreboard();

    public static String randomString() {
        String mapmeel = "%sMap%sMeel";
        String[] colors = new String[] { "§a§l", "§b§l", "§c§l", "§d§l", "§e§l", "§f§l", "§1§l", "§2§l", "§4§l", "§5§l", "§6§l", "§9§l" };
        Random r = new Random();
        return String.format(mapmeel, colors[r.nextInt(colors.length)], colors[r.nextInt(colors.length)]);
    }

    @SuppressWarnings("UnusedAssignment")
    public static void scoreboard(Player player) {
        Scoreboard b = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = b.registerNewObjective("dummy", "dummy");
        obj.setDisplayName(ScoreManager.randomString());
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int numero = 15;

        Score nada01 = obj.getScore("§a");
        nada01.setScore(numero--);

        Score nick = obj.getScore("§lNick: " + Profile.getProfile(player.getName()).getColor() + player.getName());
        nick.setScore(numero--);

        if (Updater.Invis.contains(player.getUniqueId())) {
            Score nada02 = obj.getScore("§o(Você está invisível!)");
            nada02.setScore(numero--);
        } else {
            Score nada02 = obj.getScore("§b");
            nada02.setScore(numero--);
        }

        Score warp = obj.getScore("§lWarp: §b§l" + Profile.getProfile(player.getName()).getLocation().getName());
        warp.setScore(numero--);

        Score nada03 = obj.getScore("§d");
        nada03.setScore(numero--);

        Score fails = obj.getScore("§lFails: §c§l" + Profile.getProfile(player.getName()).getFails());
        fails.setScore(numero--);

        Score nada04 = obj.getScore("§e");
        nada04.setScore(numero--);

        Score tempo = obj.getScore("§lTempo: §e§l" + Utils.sToTime(Profile.getProfile(player.getName()).getTime()));
        tempo.setScore(numero--);

        if (Profile.getProfile(player.getName()).getLocation().equals(Warp.EASTEREGG_2)) {
            player.setScoreboard(ScoreManager.empty);
        } else {
            player.setScoreboard(b);
        }
    }

}
