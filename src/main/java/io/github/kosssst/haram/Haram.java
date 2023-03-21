package io.github.kosssst.haram;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Haram extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(getConfig().getDouble("haram-tp.x"), getConfig().getDouble("haram-tp.y"), getConfig().getDouble("haram-tp.z")), this);
        System.out.println("Listener enabled");
        System.out.println("Plugin enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin disabled");
    }
}
