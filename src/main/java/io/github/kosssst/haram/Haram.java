package io.github.kosssst.haram;

import org.bukkit.plugin.java.JavaPlugin;


public final class Haram extends JavaPlugin {
    private static Haram instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EventListener(getConfig().getDouble("haram-tp.x"), getConfig().getDouble("haram-tp.y"), getConfig().getDouble("haram-tp.z")), this);
        System.out.println("Listener enabled");
        System.out.println("Plugin enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin disabled");
    }

    public static Haram getInstance() {
        return instance;
    }
}
