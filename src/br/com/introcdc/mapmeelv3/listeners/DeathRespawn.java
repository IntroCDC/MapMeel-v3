package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.introcdc.mapmeelv3.CheckpointManager;
import br.com.introcdc.mapmeelv3.MapMeelMain;
import br.com.introcdc.mapmeelv3.Profile;
import br.com.introcdc.mapmeelv3.Utils;
import br.com.introcdc.mapmeelv3.variables.Strings;

public class DeathRespawn implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getEntity().spigot().respawn();
            }
        }.runTask(MapMeelMain.getPlugin());
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        ((Player) event.getEntity()).setFoodLevel(20);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName())) {
                MoveEvent.teleporting = true;
            }
        }
        if (CheckpointManager.getCurrentCheckpoint(event.getPlayer()) != null) {
            event.getPlayer().teleport(CheckpointManager.getCurrentCheckpoint(event.getPlayer()).clone().add(0, 1, 0));
            event.getPlayer().sendMessage(Strings.prefix + "§fVocê foi teleportado para o seu CheckPoint!");
        } else {
            event.getPlayer().teleport(Profile.getProfile(event.getPlayer().getName()).getLocation().getLocation());
        }
        if (Utils.isMeelOn()) {
            if (Utils.getMeel().getName().equalsIgnoreCase(event.getPlayer().getName())) {
                MoveEvent.teleporting = false;
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Utils.errou(event.getPlayer());
                Utils.checkForPlayAudio(event.getPlayer(), true);
            }
        }.runTaskLater(MapMeelMain.getPlugin(), 5L);
    }

}
