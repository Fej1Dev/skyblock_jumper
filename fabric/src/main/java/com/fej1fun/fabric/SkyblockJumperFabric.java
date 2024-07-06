package com.fej1fun.fabric;

import net.fabricmc.api.ModInitializer;

import com.fej1fun.SkyblockJumper;

public final class SkyblockJumperFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-createFromNbt-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        SkyblockJumper.init();
    }
}
