package ir.syphix.playtime.listener;

import ir.syphix.playtime.PlayTimeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayTimeManager.savePlayer(player.getName());
        PlayTimeManager.joinTime.remove(player.getName());
    }
}
