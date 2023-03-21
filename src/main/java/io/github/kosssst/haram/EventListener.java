package io.github.kosssst.haram;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerEating(PlayerItemConsumeEvent event) {
        ItemStack food = event.getItem();
        Material mat = food.getType();
        if ((mat == Material.COOKED_PORKCHOP) || (mat == Material.PORKCHOP)){
            Bukkit.broadcastMessage("Haram!");
        }
    }

}
