package club.ironpvp.floodedWorld;

import club.ironpvp.floodedWorld.listeners.ChunkLoadListener;
import club.ironpvp.floodedWorld.listeners.PlayerSpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FloodedWorld extends JavaPlugin {

    private static FloodedWorld insance;
    public FloodedWorld() {
        FloodedWorld.insance = this;
    }

    public static FloodedWorld getInstance() { return insance; }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ChunkLoadListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSpawnListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
