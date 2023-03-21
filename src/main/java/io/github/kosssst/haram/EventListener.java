package io.github.kosssst.haram;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerEating(PlayerItemConsumeEvent event) {
        ItemStack food = event.getItem();
        Material mat = food.getType();
        Player player = event.getPlayer();
        World nether = Bukkit.getWorld("world_nether");
        if ((mat == Material.COOKED_PORKCHOP) || (mat == Material.PORKCHOP)){
            player.teleport(new Location(nether, 80.0, 42.0, -350.0));
            Bukkit.broadcastMessage("Haram!");
        }
    }

}
