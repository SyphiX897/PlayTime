package ir.syphix.playtime;

import ir.syphix.playtime.configuration.YamlConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;
import java.util.stream.Collectors;

public class PlayTimeManager {

    public static HashMap<String, Long> joinTime = new HashMap<>();

    public static FileConfiguration config = YamlConfig.get();
    public static ConfigurationSection rootSection = config.getConfigurationSection("players-playtime");

    public static ConfigurationSection getPlayerSection(String playerName) {
        return rootSection.getConfigurationSection(playerName);
    }

    public static long getPlayTime(String playerName) {
        long cacheTime = joinTime.getOrDefault(playerName, 0L);
        if (cacheTime == 0) {
            return getPlayerSection(playerName).getLong("playtime");
        }
        return getPlayerSection(playerName).getLong("playtime") + ((System.currentTimeMillis() - cacheTime) / 1000);
    }

    public static boolean hasData(String playerName) {
        return getPlayerSection(playerName) != null;
    }

    public static void saveDummyPlayer() {
        String playerName = String.valueOf(new Random().nextLong(100000000));
        ConfigurationSection playerSection = getPlayerSection(playerName);
        if (playerSection == null) {
            playerSection = rootSection.createSection(playerName);
        }
        playerSection.set("playtime", new Random().nextInt(1000000));
        YamlConfig.save();
    }

    public static void savePlayer(String playerName) {
        ConfigurationSection playerSection = getPlayerSection(playerName);
        if (playerSection == null) {
            playerSection = rootSection.createSection(playerName);
        }
        playerSection.set("playtime", getPlayTime(playerName));
        YamlConfig.save();
    }

    public static String getFormattedPlayTime(String playerName) {
        long playtime = getPlayTime(playerName);
        return getFormattedPlayTime(playtime);
    }

    public static String getFormattedPlayTime(long playtime) {
        long hours = (playtime / 60) / 60;
        long minutes = (playtime / 60) % 60;
        long seconds = playtime % 60;
        return String.format("%sh %sm %ss",hours, minutes, seconds);
    }

    public static Map<String, Long> getPlayers() {
        HashMap<String, Long> playersMap = new HashMap<>();
        Set<String> playerKeys = rootSection.getKeys(false);
        for (String playerKey : playerKeys) {
            playersMap.put(playerKey, getPlayTime(playerKey));
        }
        return playersMap;
    }

    public static List<Map.Entry<String, Long>> playersPlayTimeHashMap() {
        List<Map.Entry<String, Long>> sortedPlayers = getPlayers().entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        Collections.reverse(sortedPlayers);
        return sortedPlayers;
    }


}
