package io.github.kosssst.haram;

import jdk.tools.jlink.plugin.Plugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Level;

public class HaramTpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        boolean isPlayer = sender instanceof Player;
        Player player = null;
        Haram plugin = Haram.getInstance();
        FileConfiguration conf = plugin.getConfig();
        if (isPlayer) {
            player = (Player) sender;
            if (!player.isOp()) {
                player.sendMessage(Component.text("§c[Haram Plugin] This command can use only op player"));
                return false;
            }
        }
        if (args.length != 3) {
            if (isPlayer) {
                player.sendMessage(Component.text("§c[Haram Plugin] this command takes 3 arguments"));
            } else {
                Bukkit.getLogger().log(Level.SEVERE, "[Haram Plugin] this command takes 3 arguments");
            }
            return false;
        }
        ArrayList<Double> coords = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            try {
                coords.add(Double.parseDouble(args[i]));
            } catch (NumberFormatException e) {
                if (isPlayer) {
                    player.sendMessage(Component.text("§c[Haram Plugin] command takes only numbers as arguments"));
                } else {
                    Bukkit.getLogger().log(Level.SEVERE, "[Haram Plugin] command takes only numbers as arguments");
                }
                return false;
            }
        }
        conf.set("haram-tp.x", coords.get(0));
        conf.set("haram-tp.y", coords.get(1));
        conf.set("haram-tp.z", coords.get(2));
        plugin.saveConfig();
        return true;
    }
}
