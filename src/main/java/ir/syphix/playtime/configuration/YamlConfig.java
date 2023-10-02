package ir.syphix.playtime.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YamlConfig {

    private static File file;
    private static FileConfiguration customConfig;

    public static void setup(File folder ,String configName) {
        file = new File(folder, configName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        customConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return customConfig;
    }

    public static void save() {
        try {
            customConfig.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reload() {
        customConfig = YamlConfiguration.loadConfiguration(file);
    }
}
