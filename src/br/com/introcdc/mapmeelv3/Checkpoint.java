package br.com.introcdc.mapmeelv3;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;

import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;
import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;

public class Checkpoint {

    public static HashMap<String, Checkpoint> checkpoints = new HashMap<>();

    private Hologram hologram;
    private final Location location;
    private final String name;

    public Checkpoint(final String name, final Location location) throws MaxChangedBlocksException, DataException, IOException {
        this.name = name;
        this.location = location;
        if (!Checkpoint.checkpoints.containsKey(name)) {
            Checkpoint.checkpoints.put(name, this);
        }
        this.loadCheckpoint();
    }

    public void checkpoint(final Player player) {
        if (CheckpointManager.getCurrentCheckpoint(player) != null && CheckpointManager.getCurrentCheckpoint(player).equals(this.getLocation())) {
            player.sendBlockChange(this.getLocation(), Material.STAINED_GLASS, (byte) 14);
            return;
        }
        if (CoreOverwatch.recording) {
            if (CoreOverwatch.currentState != null) {
                CoreOverwatch.currentState.setMessage(player.getName() + " atualizou o CheckPoint");
            }
        }
        player.sendMessage(Strings.prefix + "§6§lVocê marcou um checkpoint com sucesso!");
        Utils.playSound(player, Audio.CHECKPOINT);
        CheckpointManager.setCurrentCheckpoint(player, this.getLocation());
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendBlockChange(Checkpoint.this.getLocation(), Material.STAINED_GLASS, (byte) 10);
            }
        }.runTaskLater(MapMeelMain.getPlugin(), 300);
    }

    public Hologram getHologram() {
        return this.hologram;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public void loadCheckpoint() throws DataException, IOException, MaxChangedBlocksException {
        final EditSession es = new EditSession(new BukkitWorld(this.location.getWorld()), 999999999);
        final CuboidClipboard cc = CuboidClipboard.loadSchematic(new File("checkpoint.schematic"));
        final com.sk89q.worldedit.Vector origin = new com.sk89q.worldedit.Vector(this.location.getX(), this.location.getY(), this.location.getZ());
        cc.paste(es, origin, true);
        new BukkitRunnable() {
            @Override
            public void run() {
                Checkpoint.this.getLocation().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, Checkpoint.this.getLocation().clone().add(0, 1, 0), 1);
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 3 * 20, 1);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Checkpoint.this.getHologram() == null) {
                    Checkpoint.this.hologram = HologramAPI.createHologram(Checkpoint.this.location.clone().add(0, 2, 0), "§a§l§oCheckpoint");
                }
                if (Checkpoint.this.hologram.isSpawned()) {
                    Checkpoint.this.hologram.despawn();
                }
                if (!Checkpoint.this.hologram.isSpawned()) {
                    Checkpoint.this.hologram.spawn();
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 3 * 20, 10 * 20);
    }

}
