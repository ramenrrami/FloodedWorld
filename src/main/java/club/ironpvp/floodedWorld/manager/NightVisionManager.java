package club.ironpvp.floodedWorld.manager;

import club.ironpvp.floodedWorld.FloodedWorld;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class NightVisionManager {
    private FloodedWorld plugin;
    private NamespacedKey nightVisionKey;

    public NightVisionManager(FloodedWorld plugin) {
        this.plugin = plugin;
        this.nightVisionKey = new NamespacedKey(plugin, "nightvision");
    }

    public @NotNull NamespacedKey getNightVisionKey() {
        return nightVisionKey;
    }

    public void setNightVision(Player player, boolean enabled) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(getNightVisionKey(), PersistentDataType.BYTE, (byte) (enabled ? 1 : 0));
    }

    public boolean isNightVision(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        byte defaultState = 0;
        return pdc.getOrDefault(getNightVisionKey(), PersistentDataType.BYTE, defaultState) == 1;
    }

    public boolean toggleNightVision(Player player) {
        boolean newState = !isNightVision(player);
        setNightVision(player, newState);
        return newState;
    }

    public void addPot(Player player) {
        if (!this.isNightVision(player));

        PotionEffect nv = new PotionEffect(PotionEffectType.NIGHT_VISION, 99999999, 0, false, false);
        player.addPotionEffect(nv);
    }

    public void onRemv(Player player) {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }
}
