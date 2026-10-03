package club.ironpvp.floodedWorld.user;

import club.ironpvp.floodedWorld.FloodedWorld;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;

public class NVUser {

    private static final NamespacedKey NIGHT_VISION_KEY = new NamespacedKey(FloodedWorld.getInstance(), "floodedworld");

    private final Player player;

    public NVUser(Player player) {
        this.player = player;
    }

    public boolean isNightVision() {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        byte defaultState = 0;
        return pdc.getOrDefault(NIGHT_VISION_KEY, PersistentDataType.BYTE, defaultState) == 1;
    }

}
