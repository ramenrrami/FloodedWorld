package club.ironpvp.floodedWorld;

import club.ironpvp.floodedWorld.commands.NightVisionCommand;
import club.ironpvp.floodedWorld.listeners.ChunkLoadListener;
import club.ironpvp.floodedWorld.listeners.NightVisionListener;
import club.ironpvp.floodedWorld.listeners.PlayerSpawnListener;
import club.ironpvp.floodedWorld.manager.NightVisionManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FloodedWorld extends JavaPlugin {

    private static FloodedWorld insance;
    public FloodedWorld() {
        FloodedWorld.insance = this;
    }

    public static FloodedWorld getInstance() { return insance; }

    private NightVisionManager manager;

    @Override
    public void onEnable() {
        manager = new NightVisionManager(this);

        Bukkit.getPluginManager().registerEvents(new ChunkLoadListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSpawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new NightVisionListener(manager), this);

        getCommand("nightvision").setExecutor(new NightVisionCommand(manager));
        getCommand("nightvision").setTabCompleter(new NightVisionCommand(manager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
