package ir.syphix.playtime.command;

import ir.syphix.playtime.PlayTimeManager;
import ir.syrent.origin.paper.Origin;
import ir.syrent.origin.paper.utils.ComponentUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayTimeTopCommand implements CommandExecutor {

    FileConfiguration config = Origin.getPlugin().getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("playtime.top")) {
                List<Map.Entry<String, Long>> topPlayers = PlayTimeManager.playersPlayTimeHashMap();
                List<Component> messages = new ArrayList<>();
                player.sendMessage(toComponent("<#a3ff05>]=-------------PlayTime Top-------------=[</#a3ff05>"));
                player.sendMessage(" ");
                for (int i = 0; i < 5; i++) {
                    messages.add(toComponent(String.format("[%s] Unknown: N/A", i + 1)));
                }

                int counter = 0;
                for (Map.Entry<String, Long> topPlayer : topPlayers) {
                    if (counter >= 5) {
                        break;
                    }
                    messages.set(counter, toComponent(String.format("<green>[%s] %s: <dark_green>%s", counter + 1, topPlayer.getKey(), PlayTimeManager.getFormattedPlayTime(topPlayer.getValue()))));

                    counter++;
                }

                for (Component message : messages) {
                    player.sendMessage(message);
                }
                player.sendMessage(" ");
                player.sendMessage(toComponent("<#a3ff05>]=-------------------------------------=[</#a3ff05>"));
            } else {
                player.sendMessage(toComponent("<gradient:dark_red:red>You dont have permission!"));
            }
        } else {
            sender.sendMessage("<gradient:dark_red:red>You are not a player!");
        }
        return false;
    }

    private Component toComponent(String content) {
        return ComponentUtils.component(content);
    }
}
