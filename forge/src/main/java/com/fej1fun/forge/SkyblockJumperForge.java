package com.fej1fun.forge;

import com.fej1fun.EventRegistry;
import dev.architectury.platform.forge.EventBuses;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.fej1fun.SkyblockJumper;

@Mod(SkyblockJumper.MOD_ID)
public final class SkyblockJumperForge {
    public SkyblockJumperForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(SkyblockJumper.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

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

        // Run our common setup.
        SkyblockJumper.init();
    }
}
