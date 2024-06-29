package com.fej1fun.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.fej1fun.SkyblockJumper;

@Mod(SkyblockJumper.MOD_ID)
public final class ExampleModForge {
    public ExampleModForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(SkyblockJumper.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        SkyblockJumper.init();
    }
}
