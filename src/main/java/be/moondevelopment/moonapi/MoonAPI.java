package be.moondevelopment.moonapi;
/*
 * @created 23/03/2023 - 17:59
 * @project MoonAPI
 * @author MoonDevelopment
 */

import be.moondevelopment.moonapi.framework.utils.ColorUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.bukkit.plugin.java.annotation.plugin.author.Authors;

@Plugin(
        name = "MoonAPI",
        version = "1.0"
)
@Description(
        "An all in one framework plugin with different utils | Created by MoonDevelopment."
)
@Authors({
        @Author("MoonDevelopment")
})
public class MoonAPI extends JavaPlugin {

    @Getter
    private static MoonAPI instance;

    @Override
    public void onEnable() {
        instance = this;

        long start = System.currentTimeMillis();

        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&7===&8=============================================&7==="));
        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&8- &3Name: &bMoonAPI"));
        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&8- &3Version: &b" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&8- &3Authors: &b" + this.getDescription().getAuthors()));
        Bukkit.getConsoleSender().sendMessage("");

        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&8- &aSuccesfully enabled &eMoonAPI&a plugin in &2" + (System.currentTimeMillis() - start) + "ms&a."));
        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&7===&8=============================================&7==="));

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&7===&8=============================================&7==="));
        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("- &cDisabling &bMoonAPI &3" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getServicesManager().unregisterAll(this);
        Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("&7===&8=============================================&7==="));
    }

}
