package be.moondevelopment.moonapi.spigot.framework.command;
/*
 * @author MoonDevelopment
 * @website https://www.moondevelopment.be/
 * @license GNU General Public License v3.0
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * The licenses for most software and other practical works are designed
 * to take away your freedom to share and change the works.  By contrast,
 * the GNU General Public License is intended to guarantee your freedom to
 * share and change all versions of a program--to make sure it remains free
 * software for all its users.  We, the Free Software Foundation, use the
 * GNU General Public License for most of our software; it applies also to
 * any other work released this way by its authors.  You can apply it to
 * your programs, too.
 *
 * When we speak of free software, we are referring to freedom, not
 * price.  Our General Public Licenses are designed to make sure that you
 * have the freedom to distribute copies of free software (and charge for
 * them if you wish), that you receive source code or can get it if you
 * want it, that you can change the software or use pieces of it in new
 * free programs, and that you know you can do these things.
 *
 * To protect your rights, we need to prevent others from denying you
 * these rights or asking you to surrender the rights.  Therefore, you have
 * certain responsibilities if you distribute copies of the software, or if
 * you modify it: responsibilities to respect the freedom of others.
 *
 * For example, if you distribute copies of such a program, whether
 * free or for a fee, you must pass on to the recipients the same
 * freedoms that you received.  You must make sure that they, too, receive
 * or can get the source code.  And you must show them these terms so they
 * know their rights.
 *
 * Developers that use the GNU GPL protect your rights with two steps:
 * (1) assert copyright on the software, and (2) offer you this License
 * giving you legal permission to copy, distribute and/or modify it.
 *
 * For the developers' and authors' protection, the GPL clearly explains
 * that there is no warranty for this free software.  For both users' and
 * authors' sake, the GPL requires that modified versions be marked as
 * changed, so that their problems will not be attributed erroneously to
 * authors of previous versions.
 *
 * Some devices are designed to deny users access to install or run
 * modified versions of the software inside them, although the manufacturer
 * can do so.  This is fundamentally incompatible with the aim of
 * protecting users' freedom to change the software.  The systematic
 * pattern of such abuse occurs in the area of products for individuals to
 * use, which is precisely where it is most unacceptable.  Therefore, we
 * have designed this version of the GPL to prohibit the practice for those
 * products.  If such problems arise substantially in other domains, we
 * stand ready to extend this provision to those domains in future versions
 * of the GPL, as needed to protect the freedom of users.
 *
 * Finally, every program is threatened constantly by software patents.
 * States should not allow patents to restrict development and use of
 * software on general-purpose computers, but in those that do, we wish to
 * avoid the special danger that patents applied to a free program could
 * make it effectively proprietary.  To prevent this, the GPL assures that
 * patents cannot be used to render the program non-free.

 * The precise terms and conditions for copying, distribution and
 * modification follow.
 */

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class CommandManager extends BukkitCommand {

    /**
     * The plugin instance
     */
    private static Plugin plugin;

    /**
     * The command map
     */
    private static CommandMap commandMap;

    /**
     * Initialize the command manager
     *
     * @param spigot The plugin instance
     */
    public static void init(Plugin spigot) {
        plugin = spigot;

        try {
            Field field  = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMap = (CommandMap) field.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException x) {
            x.printStackTrace();
        }
    }

    /**
     * CommandManager constructor
     *
     * @param command
     */
    public CommandManager(String command) {
        super(command);

        if (plugin == null) {
            throw new NullPointerException("Plugin instance is null");
        }

        commandMap.register(plugin.getName(), this);
    }

    /**
     * CommandManager constructor
     *
     * @param command
     * @param description
     */
    public CommandManager(String command, String description) {
        super(command);
        this.setDescription(description);

        if (plugin == null) {
            throw new NullPointerException("Plugin instance is null");
        }

        commandMap.register(plugin.getName(), this);
    }

    /**
     * CommandManager constructor
     *
     * @param command
     * @param description
     * @param permission
     */
    public CommandManager(String command, String description, String permission) {
        super(command);
        this.setDescription(description);
        this.setPermission(permission);

        if (plugin == null) {
            throw new NullPointerException("Plugin instance is null");
        }

        commandMap.register(plugin.getName(), this);
    }

    /**
     * CommandManager constructor
     *
     * @param command
     * @param aliases
     */
    public CommandManager(String command, String[] aliases) {
        super(command);
        this.setAliases(Arrays.asList(aliases));

        if (plugin == null) {
            throw new NullPointerException("Plugin instance is null");
        }

        commandMap.register(plugin.getName(), this);
    }

    /**
     * CommandManager constructor
     *
     * @param command
     * @param aliases
     * @param description
     */
    public CommandManager(String command, String[] aliases, String description) {
        super(command);
        this.setAliases(Arrays.asList(aliases));
        this.setDescription(description);

        if (plugin == null) {
            throw new NullPointerException("Plugin instance is null");
        }

        commandMap.register(plugin.getName(), this);
    }

    /**
     * CommandManager constructor
     *
     * @param command
     * @param aliases
     * @param description
     * @param permission
     */
    public CommandManager(String command, String[] aliases, String description, String permission) {
        super(command);
        this.setAliases(Arrays.asList(aliases));
        this.setDescription(description);
        this.setPermission(permission);

        if (plugin == null) {
            throw new NullPointerException("Plugin instance is null");
        }

        commandMap.register(plugin.getName(), this);
    }

    /**
     * Execute the command
     *
     * @param commandSender
     * @param s
     * @param args
     * @return boolean false
     */
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        onCommand(commandSender, s, args);
        return false;
    }

    /**
     * Abstract method to execute the command
     *
     * @param sender
     * @param s
     * @param args
     */
    public abstract void onCommand(CommandSender sender, String s, String[] args);

    /**
     * Tab complete the command
     *
     * @param sender
     * @param alias
     * @param args
     * @return List<String>
     * @throws IllegalArgumentException
     */
    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return  onTabComplete(sender, args);
    }

    /**
     * Abstract method to tab complete the command
     *
     * @param sender
     * @param args
     * @return List<String>
     */
    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}
