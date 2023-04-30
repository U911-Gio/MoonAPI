package be.moondevelopment.moonapi.framework.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class YamlConfigUtil extends YamlConfiguration {

    private final File jfile;

    public YamlConfigUtil(Plugin plugin, String file) {
        jfile = new File(plugin.getDataFolder(), file);
        if (!this.jfile.exists()) {
            jfile.getParentFile().mkdirs();
            plugin.saveResource(file, false);
        }

        try {
            this.load(jfile);
        } catch (InvalidConfigurationException | IOException x) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&4Error whilst loading " + file + ": \n&c" + x.getMessage() + "\n &cCaused by: \n" + x.getCause()));
        }
    }



    public void save() {
        try {
            this.save(this.jfile);
        } catch (IOException x) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&4Error whilst loading " + jfile.getName() + ": \n&c" + x.getMessage() + "\n &cCaused by: \n" + x.getCause()));
        }

    }


}

