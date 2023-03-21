package io.github.kosssst.haram;

import org.bukkit.plugin.java.JavaPlugin;

public final class Haram extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        System.out.println("Listener enabled");
        System.out.println("Plugin enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin disabled");
    }
}
