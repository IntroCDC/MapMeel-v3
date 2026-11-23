package br.com.introcdc.mapmeelv3;

import br.com.introcdc.mapmeelv3.overwatch.CoreOverwatch;
import br.com.introcdc.mapmeelv3.variables.Strings;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;
import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Checkpoint {

    public static HashMap<String, Checkpoint> checkpoints = new HashMap<>();

    private Hologram hologram;
    private Location location;
    private String name;

    public Checkpoint(String name, Location location) throws MaxChangedBlocksException, DataException, IOException {
        this.name = name;
        this.location = location;
        if (!Checkpoint.checkpoints.containsKey(name)) {
            Checkpoint.checkpoints.put(name, this);
        }
        this.loadCheckpoint();
    }

    public void checkpoint(Player player) {
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
        EditSession es = new EditSession(new BukkitWorld(this.location.getWorld()), 999999999);
        CuboidClipboard cc = CuboidClipboard.loadSchematic(new File("checkpoint.schematic"));
        com.sk89q.worldedit.Vector origin = new com.sk89q.worldedit.Vector(this.location.getX(), this.location.getY(), this.location.getZ());
        cc.paste(es, origin, true);
    }

    /**
     * Retorna true se existir algum jogador online no mesmo mundo a até "radius" blocos.
     */
    public static boolean hasPlayerNear(Location location, double radius) {
        double radiusSquared = radius * radius;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld() != location.getWorld()) {
                continue;
            }
            if (player.getLocation().distanceSquared(location) <= radiusSquared) {
                return true;
            }
        }
        return false;
    }

    /**
     * Antes cada checkpoint tinha 2 tarefas próprias (uma rodando a cada tick).
     * Agora é uma tarefa só para todos, e só trabalha se houver jogador por perto.
     */
    public static void startTasks() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().isEmpty()) {
                    return;
                }
                for (Checkpoint checkpoint : Checkpoint.checkpoints.values()) {
                    if (Checkpoint.hasPlayerNear(checkpoint.getLocation(), 48)) {
                        checkpoint.getLocation().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, checkpoint.getLocation().clone().add(0, 1, 0), 1);
                    }
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 3 * 20, 2);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Checkpoint checkpoint : Checkpoint.checkpoints.values()) {
                    if (checkpoint.hologram == null) {
                        checkpoint.hologram = HologramAPI.createHologram(checkpoint.location.clone().add(0, 2, 0), "§a§l§oCheckpoint");
                        checkpoint.hologram.spawn();
                        continue;
                    }
                    // Re-spawn apenas dos hologramas com jogador perto (para quem acabou de chegar ver).
                    if (!Checkpoint.hasPlayerNear(checkpoint.getLocation(), 64)) {
                        continue;
                    }
                    if (checkpoint.hologram.isSpawned()) {
                        checkpoint.hologram.despawn();
                    }
                    checkpoint.hologram.spawn();
                }
            }
        }.runTaskTimer(MapMeelMain.getPlugin(), 3 * 20, 10 * 20);
    }

}
