package club.ironpvp.floodedWorld.listeners;

import club.ironpvp.floodedWorld.FloodedWorld;
import club.ironpvp.floodedWorld.manager.NightVisionManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class NightVisionListener implements Listener {
    private final NightVisionManager manager;
    public NightVisionListener(NightVisionManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        manager.addPot(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.onRemv(e.getPlayer());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Bukkit.getScheduler().runTaskLater(FloodedWorld.getInstance(), () -> {
            if (e.getPlayer().isOnline() && manager.isNightVision(e.getPlayer())) manager.addPot(e.getPlayer());
        }, 1L);
    }

}
