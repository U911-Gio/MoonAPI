package be.moondevelopment.moonapi.spigot.framework.modules;
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

import be.moondevelopment.moonapi.spigot.framework.utils.ColorUtil;
import be.moondevelopment.moonapi.spigot.MoonAPI;
import com.google.common.reflect.ClassPath;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;

public class ModuleManager {

    /**
     * The class loader used to load the modules.
     */
    private final ClassLoader classLoader;

    /**
     * ModuleManager constructor.
     *
     * @param classLoader
     */
    public ModuleManager(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * The list of modules.
     */
    @Getter
    private final ArrayList<AbstractModule> modules = new ArrayList<>();

    /**
     * The list of loaded modules.
     */
    @Getter
    private static final ArrayList<String> loaded = new ArrayList<>();

    /**
     * Unload all modules.
     */
    public void unload() {
        for (AbstractModule module : modules) {
            module.onDisable();
        }
    }

    /**
     * Load all modules from a classpath.
     *
     * @param classpath
     */
    public void load(String classpath) {

        Set<ClassPath.ClassInfo> classPackage;
        try {
            classPackage = ClassPath.from(classLoader)
                    .getTopLevelClassesRecursive(classpath);
        } catch (IOException x) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("  &cError whilst loading &4" + modules + "."));
            MoonAPI.getInstance().getLogger().log(Level.SEVERE, "Couldn't load classes " + modules + ".");
            return;
        }

        for (ClassPath.ClassInfo classInfo : classPackage) {


            try {

                Class clazz = classInfo.load();

                if (!clazz.getSuperclass().equals(AbstractModule.class)) continue;

                AbstractModule instance = (AbstractModule) clazz.newInstance();

                if (!instance.isEnabled()) continue;

                instance.onEnable();

                modules.add(instance);

                loaded.add(instance.getName());

                Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("  &bSuccesfully loaded &3" + instance.getName()));

            } catch (InstantiationException | IllegalAccessException x) {
                Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("  &cError whilst loading &4" + classInfo.getSimpleName()));
                MoonAPI.getInstance().getLogger().severe(String.format("Couldn't load module %s.", classInfo.getSimpleName()));
                x.printStackTrace();
            }


        }


    }

}