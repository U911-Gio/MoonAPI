package be.moondevelopment.moonapi.framework.modules;



import be.moondevelopment.moonapi.MoonAPI;
import be.moondevelopment.moonapi.framework.utils.ColorUtil;
import com.google.common.reflect.ClassPath;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;

public class ModuleManager {

    @Getter
    private final ArrayList<AbstractModule> modules = new ArrayList<>();
    @Getter
    private static final ArrayList<String> loaded = new ArrayList<>();


    public void unload() {
        for (AbstractModule module : modules) {
            module.onDisable();
        }
    }
    public void load(String classpath) {

        Set<ClassPath.ClassInfo> classPackage;
        try {
            classPackage = ClassPath.from(ModuleManager.class.getClassLoader())
                    .getTopLevelClassesRecursive(classpath);
        } catch (IOException x) {
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

                Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("  &aSuccesfully loaded &2" + instance.getName()));

            } catch (InstantiationException | IllegalAccessException x) {
                Bukkit.getConsoleSender().sendMessage(ColorUtil.CC("  &cError whilst loading &4" + classInfo.getSimpleName()));
                MoonAPI.getInstance().getLogger().severe(String.format("Couldn't load module %s.", classInfo.getSimpleName()));
                x.printStackTrace();
            }


        }


    }

}