package club.ironpvp.floodedWorld.listeners;

import club.ironpvp.floodedWorld.manager.NightVisionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVisionListener implements Listener {
    private final NightVisionManager manager;
    public NightVisionListener(NightVisionManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!manager.isNightVision(e.getPlayer())) return;

        PotionEffect nv = new PotionEffect(PotionEffectType.NIGHT_VISION, 99999999, 0, false, false);
        e.getPlayer().addPotionEffect(nv);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!manager.isNightVision(e.getPlayer())) return;

        e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

}
