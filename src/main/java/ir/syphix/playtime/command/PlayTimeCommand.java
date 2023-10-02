package ir.syphix.playtime.command;

import ir.syphix.playtime.PlayTimeManager;
import ir.syrent.origin.paper.utils.ComponentUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayTimeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player player) {
                if (player.hasPermission("playtime.self")) {
                    if (PlayTimeManager.hasData(player.getName())) {
                        player.sendMessage(toComponent(String.format("<gradient:dark_green:green>%s playtime: <gradient:gold:yellow>%s", player.getName(), PlayTimeManager.getFormattedPlayTime(player.getName()))));
                    } else {
                        player.sendMessage(toComponent("<gradient:dark_red:red>There is no player with this name!"));
                    }
                } else {
                    player.sendMessage(toComponent("<gradient:dark_red:red>You dont have permission to do this!"));
                }
            } else {
                sender.sendMessage(toComponent("<gradient:dark_red:red>/playtime <player-name>"));
            }
        } else if (args.length == 1) {
            String targetName = args[0];
            OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(targetName);
            if (!PlayTimeManager.hasData(offlineTarget.getName())) {
                sender.sendMessage(toComponent("<gradient:dark_red:red>There is no player with this name"));
                return true;
            }

            sender.sendMessage(toComponent(String.format("<gradient:dark_green:green>%s playtime: <gradient:gold:yellow>%s", targetName, PlayTimeManager.getFormattedPlayTime(targetName))));
        } else {
            sender.sendMessage(toComponent("<gradient:dark_red:red>/playtime <player-name>"));
        }
        return false;
    }

    public Component toComponent(String content) {
        return ComponentUtils.component(content);
    }
}
