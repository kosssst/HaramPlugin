package io.github.kosssst.haram;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;


public final class Haram extends JavaPlugin {
    private static Haram instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Objects.requireNonNull(this.getCommand("haramtp")).setExecutor(new HaramTpCommand());
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getLogger().log(Level.INFO, "[Haram Plugin] Plugin enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "[Haram Plugin] Plugin disabled");
    }

    public static Haram getInstance() {
        return instance;
    }
}
