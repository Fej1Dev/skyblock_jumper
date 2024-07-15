package com.fej1fun.fabric;

import com.fej1fun.EventRegistry;
import net.fabricmc.api.ModInitializer;
import com.fej1fun.SkyblockJumper;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public final class SkyblockJumperFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-createFromNbt-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.

        CustomPortalBuilder.beginPortal().
                destDimID(EventRegistry.STONEBLOCK_DIM.location()).
                returnDim(EventRegistry.SKYBLOCK_DIM.location(),false).
                frameBlock(Blocks.OAK_LOG).lightWithItem(Items.MUTTON).
                tintColor(102,51,0).registerPortal();
        CustomPortalBuilder.beginPortal().
                destDimID(EventRegistry.WATERBLOCK_DIM.location()).
                returnDim(EventRegistry.STONEBLOCK_DIM.location(),false).
                frameBlock(Blocks.STONE).lightWithItem(Items.CHARCOAL).
                tintColor(128,128,128).registerPortal();
        CustomPortalBuilder.beginPortal().
                destDimID(EventRegistry.LAVABLOCK_DIM.location()).
                returnDim(EventRegistry.WATERBLOCK_DIM.location(),false).
                frameBlock(Blocks.PRISMARINE).lightWithItem(Items.KELP).
                tintColor(0,128,255).registerPortal();
        CustomPortalBuilder.beginPortal().
                destDimID(new ResourceLocation("minecraft:overworld")).
                returnDim(EventRegistry.LAVABLOCK_DIM.location(),false).
                frameBlock(Blocks.NETHERRACK).lightWithItem(Items.GOLD_NUGGET).
                tintColor(255,128,0).registerPortal();


        SkyblockJumper.init();
    }
}
