package com.fej1fun.registries;

import com.fej1fun.SkyblockJumper;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class EventRegistry {
    public static final ResourceKey<Level> skyblockResourceKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock"));
    public static final ResourceLocation SKYBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock");

    public static void init() {
        TickEvent.SERVER_LEVEL_POST.register(instance -> {
            ServerLevel serverLevel = instance.getLevel();
            if (serverLevel.dimension().equals(skyblockResourceKey)) {
                StructureTemplate structureTemplateSky = serverLevel.getStructureManager().getOrCreate(SKYBLOCK);
                Vec3i vec3iSky = structureTemplateSky.getSize();
                serverLevel.structureManager().hasAnyStructureAt(new BlockPos(0,127,0));
                if (!serverLevel.structureManager().hasAnyStructureAt(new BlockPos(0,127,0))) {
                    structureTemplateSky.placeInWorld(serverLevel, new BlockPos(0,127,0),
                            new BlockPos(vec3iSky.getX(),vec3iSky.getY()+127,vec3iSky.getZ()), new StructurePlaceSettings(), serverLevel.random, 2);
                }
            }
        });
    }
}
