package com.fej1fun.registries;

import com.fej1fun.SkyblockJumper;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class PortalRegistry {
    public static void init() {

        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.STONE)
                .lightWithItem(Items.MUTTON)
                .destDimID(new ResourceLocation(SkyblockJumper.MOD_ID,"skyblock"))
                .tintColor(96,96,96)
                .registerPortal();
    }

}
