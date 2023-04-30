package be.moondevelopment.moonapi.framework.modules;


import lombok.Getter;

public abstract class AbstractModule {
    private @Getter final boolean enabled;

    public String getName() {
        return getClass().getSimpleName();
    }

    public AbstractModule(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void onEnable();

    public abstract void onDisable();

}
