package be.moondevelopment.moonapi.velocity;

import be.moondevelopment.moonapi.velocity.framework.utils.LoggerUtil;
import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import org.slf4j.Logger;

@Plugin(
    id = "moonapi",
    name = "MoonAPI",
    version = "2.1-SNAPSHOT",
    authors = {"MoonDevelopment"},
    description = "An all in one framework plugin with different utils | Created by MoonDevelopment."
)
public class MoonAPI {

    @Getter private static MoonAPI instance;
    @Getter private final ProxyServer server;
    @Getter private final Logger logger;
    @Getter private final PluginContainer container;

    @Inject
    public MoonAPI(ProxyServer server, Logger logger, PluginContainer container) {
        instance = this;
        this.server = server;
        this.logger = logger;
        this.container = container;

        logger.info("MoonAPI has been initialized successfully.");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        long start = System.currentTimeMillis();
        LoggerUtil.start_begin(container.getDescription(), "MoonAPI");
        LoggerUtil.start_end("MoonAPI", start);

    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        LoggerUtil.stop_begin("MoonAPI");
        server.getEventManager().unregisterListeners(this);
        LoggerUtil.stop_end();
    }
}
