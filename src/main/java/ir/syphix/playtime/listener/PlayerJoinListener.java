package ir.syphix.playtime.listener;

import ir.syphix.playtime.PlayTimeManager;
import ir.syphix.playtime.configuration.YamlConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayTimeManager.joinTime.put(player.getName(), System.currentTimeMillis());
        PlayTimeManager.savePlayer(player.getName());
        YamlConfig.save();
    }
}
