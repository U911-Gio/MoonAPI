package be.moondevelopment.moonapi.framework.utils;

import org.bukkit.plugin.Plugin;

public class ConfigUtil {

    public static YamlConfigUtil get(Plugin plugin, String file) {
        return new YamlConfigUtil(plugin, file);
    }

}
