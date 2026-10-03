package club.ironpvp.floodedWorld.listeners;

import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerSpawnListener implements Listener {

    @EventHandler
    public void onSpawn(AsyncPlayerSpawnLocationEvent e) {
        Location loc = e.getSpawnLocation();

        for (int y = 60; y < e.getSpawnLocation().getWorld().getMaxHeight(); y++) {
            Block block = loc.getWorld().getBlockAt(loc.getBlockX(), y, loc.getBlockZ());
            if (block.getType() == Material.WATER) {
                loc.setY(y);
                e.setSpawnLocation(loc);
                break;
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Location loc = e.getRespawnLocation();

        for (int y = 60; y < e.getRespawnLocation().getWorld().getMaxHeight(); y++) {
            Block block = loc.getWorld().getBlockAt(loc.getBlockX(), y, loc.getBlockZ());
            if (block.getType() == Material.WATER) {
                loc.setY(y);
                e.setRespawnLocation(loc);
                break;
            }
        }
    }
}
