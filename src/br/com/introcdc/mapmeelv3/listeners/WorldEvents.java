package br.com.introcdc.mapmeelv3.listeners;

import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class WorldEvents implements Listener {

    @EventHandler
    public void chunkLoad(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();
        for (int x = 0; x <= 16; x++) {
            for (int z = 0; z <= 16; z++) {
                Block block = chunk.getBlock(x, 0, z);
                if (block.getBiome() == Biome.VOID) {
                    block.setBiome(Biome.PLAINS);
                }
            }
        }
    }

}
