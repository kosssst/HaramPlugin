package io.github.kosssst.haram;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerEating(PlayerItemConsumeEvent event) {
        Haram plugin = Haram.getInstance();
        FileConfiguration conf = plugin.getConfig();
        ItemStack food = event.getItem();
        Material mat = food.getType();
        Player player = event.getPlayer();
        World nether = Bukkit.getWorld("world_nether");
        if ((mat == Material.COOKED_PORKCHOP) || (mat == Material.PORKCHOP)){
            ItemStack foodToRemove = new ItemStack(mat, 1);
            player.getInventory().removeItem(foodToRemove);
            saveInventory(player);
            eraseInventory(player);
            player.teleport(new Location(nether, conf.getDouble("haram-tp.x"), conf.getDouble("haram-tp.y"), conf.getDouble("haram-tp.z")));
            player.sendTitle("HARAM", "", 20, 400, 20);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Haram plugin = Haram.getInstance();
        Player player = event.getPlayer();
        String name = player.getName();
        String fileName = name + ".yaml";
        File file = new File(plugin.getDataFolder(), fileName);
        if (file.exists()) {
            loadInventory(player);
        }
    }

    private void saveInventory(Player player) {
        Haram plugin = Haram.getInstance();
        String name = player.getName();
        String fileName = name + ".yaml";
        File file = new File(plugin.getDataFolder(), fileName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        ItemStack[] inventoryContents = player.getInventory().getContents();
        ItemStack[] armorContents = player.getInventory().getArmorContents();
        config.set("inventory.contents", inventoryContents);
        config.set("inventory.armor", armorContents);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadInventory(Player player) {
        Haram plugin = Haram.getInstance();
        String name = player.getName();
        String fileName = name + ".yaml";
        File file = new File(plugin.getDataFolder(), fileName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        

        ItemStack[] items = new ItemStack[Objects.requireNonNull(config.getList("inventory.contents")).size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = (ItemStack) Objects.requireNonNull(config.getList("inventory.contents")).get(i);
        }

        player.getInventory().setContents(items);
        file.delete();
    }

    private void eraseInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }
}
