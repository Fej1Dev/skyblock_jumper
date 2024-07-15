package com.fej1fun;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.TickEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class EventRegistry {
    public static final ResourceKey<Level> SKYBLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock"));
    public static final ResourceLocation SKYBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock");
    public static final ResourceKey<Level> STONEBLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "stoneblock"));
    public static final ResourceLocation STONEBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "stoneblock");
    public static final ResourceKey<Level> WATERBLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "waterblock"));
    public static final ResourceLocation WATERBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "waterblock");
    public static final ResourceKey<Level> LAVABLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "lavablock"));
    public static final ResourceLocation LAVABLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "lavablock");

    public static final TagKey<Biome> STONEBLOCK_BIOME_TAG = TagKey.create(Registries.BIOME, new ResourceLocation(SkyblockJumper.MOD_ID, "stoneblock_tag"));


    public static void init() {
        TickEvent.SERVER_LEVEL_POST.register(instance -> {
            ServerLevel serverLevel = instance.getLevel();
            FriendlyByteBuf data = new FriendlyByteBuf(Unpooled.buffer());
            StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(serverLevel.getServer());

            if(generateStructure(serverLevel, SKYBLOCK_DIM, SKYBLOCK, serverState.GeneratedSkyblock,0)) {
                serverState.GeneratedSkyblock=true;
                data.writeBoolean(serverState.GeneratedSkyblock);
            }
            if(generateStructure(serverLevel, STONEBLOCK_DIM, STONEBLOCK, serverState.GeneratedStoneblock,0)) {
                serverState.GeneratedStoneblock=true;
                data.writeBoolean(serverState.GeneratedStoneblock);
            }
            if(generateStructure(serverLevel, WATERBLOCK_DIM, WATERBLOCK, serverState.GeneratedWaterblock, -64)) {
                serverState.GeneratedWaterblock=true;
                data.writeBoolean(serverState.GeneratedWaterblock);
            }
            if(generateStructure(serverLevel, LAVABLOCK_DIM, LAVABLOCK, serverState.GeneratedLavablock,0)) {
                serverState.GeneratedLavablock=true;
                data.writeBoolean(serverState.GeneratedLavablock);
            }
            serverState.setDirty();
        });

    }

    private static boolean generateStructure(ServerLevel serverLevel, ResourceKey<Level> dimension, ResourceLocation structure, boolean generated, int YOffset) {
        if (generated) return false;
        if (!serverLevel.dimension().equals(dimension)) return false;

        StructureTemplate structureTemplate = serverLevel.getStructureManager().getOrCreate(structure);
        Vec3i vec3i = structureTemplate.getSize();
        structureTemplate.placeInWorld(serverLevel, new BlockPos(0,127,0),
                new BlockPos(vec3i.getX(), vec3i.getY()+127+YOffset, vec3i.getZ()), new StructurePlaceSettings(), serverLevel.getRandom(), Block.UPDATE_NEIGHBORS);
        return true;
    }
}
