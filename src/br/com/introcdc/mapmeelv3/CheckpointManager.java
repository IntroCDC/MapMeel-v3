package br.com.introcdc.mapmeelv3;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CheckpointManager {

    private static HashMap<UUID, Location> cache = new HashMap<>();

    public static HashMap<UUID, Location> getCache() {
        return CheckpointManager.cache;
    }

    public static Location getCurrentCheckpoint(final Player player) {
        if (Warp.byLocation(CheckpointManager.cache.get(player.getUniqueId())) != null) {
            return null;
        }
        return CheckpointManager.cache.get(player.getUniqueId());
    }

    public static void resetCurrentCheckPoint(final Player player) {
        CheckpointManager.cache.replace(player.getUniqueId(), Profile.getProfile(player.getName()).getLocation().getLocation());
    }

    public static Location setCurrentCheckpoint(final Player player, final Location location) {
        CheckpointManager.cache.replace(player.getUniqueId(), location);
        return location;
    }

}
