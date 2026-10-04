/*
 * Copyright (C) 2026 raammi
 */

package club.ironpvp.floodedWorld.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoadListener implements Listener {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        if (!e.isNewChunk()) return;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = e.getWorld().getMinHeight(); y < e.getWorld().getMaxHeight(); y++) {
                    Block block = e.getChunk().getBlock(x, y, z);
                    if (block.getType() == Material.AIR || block.getType() == Material.AIR) {
                        block.setType(Material.WATER, false);
                    }
                }
            }
        }
    }
}
