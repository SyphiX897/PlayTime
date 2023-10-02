package ir.syphix.playtime;

import ir.syphix.playtime.command.PlayTimeCommand;
import ir.syphix.playtime.command.PlayTimeTopCommand;
import ir.syphix.playtime.configuration.YamlConfig;
import ir.syphix.playtime.listener.PlayerJoinListener;
import ir.syphix.playtime.listener.PlayerQuitListener;
import ir.syrent.origin.paper.Origin;
import ir.syrent.origin.paper.OriginPlugin;

public final class PlayTime extends OriginPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        YamlConfig.setup(Origin.getPlugin().getDataFolder(), "data.yml");
        if (YamlConfig.get().getConfigurationSection("players-playtime") == null) {
            YamlConfig.get().createSection("players-playtime");
        }
        YamlConfig.save();

        Origin.registerListener(new PlayerJoinListener());
        Origin.registerListener(new PlayerQuitListener());
        getCommand("playtime").setExecutor(new PlayTimeCommand());
        if (getConfig().getBoolean("playtime-top-command")) {
            getCommand("playtimetop").setExecutor(new PlayTimeTopCommand());
        }
    }

}
