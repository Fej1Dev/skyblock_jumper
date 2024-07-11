package com.fej1fun;

import com.fej1fun.registries.EventRegistry;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SkyblockJumper {
    public static final String MOD_ID = "skyblock_jumper";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        // Write common init code here.

        EventRegistry.init();

        //Client
        ClientLifecycleEvent.CLIENT_SETUP.register(minecraft -> {
        });

        LOG.info("finished loading");
    }
}
