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
    public static final ResourceKey<Level> SKYBLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock"));
    public static final ResourceLocation SKYBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock");
    public static final ResourceKey<Level> STONEBLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "stoneblock"));
    public static final ResourceLocation STONEBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "stoneblock");


    public static void init() {
        TickEvent.SERVER_LEVEL_POST.register(instance -> {
            ServerLevel serverLevel = instance.getLevel();
            generateStructure(serverLevel, SKYBLOCK_DIM, SKYBLOCK);
            generateStructure(serverLevel, STONEBLOCK_DIM, STONEBLOCK);
        });
    }

    private static void generateStructure(ServerLevel serverLevel, ResourceKey<Level> dimension, ResourceLocation structure) {
        if (serverLevel.dimension().equals(dimension)) {
            StructureTemplate structureTemplate = serverLevel.getStructureManager().getOrCreate(structure);
            Vec3i vec3i = structureTemplate.getSize();
            if (!serverLevel.structureManager().hasAnyStructureAt(new BlockPos(0,127,0))) {
                structureTemplate.placeInWorld(serverLevel, new BlockPos(0,127,0),
                        new BlockPos(vec3i.getX(), vec3i.getY() + 127, vec3i.getZ()), new StructurePlaceSettings(), serverLevel.getRandom(), 2);
            }
        }
    }
}
