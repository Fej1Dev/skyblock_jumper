package com.fej1fun.registries;

import com.fej1fun.SkyblockJumper;
import com.fej1fun.StateSaverAndLoader;
import dev.architectury.event.events.common.TickEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.jfr.event.ChunkGenerationEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.redstone.NeighborUpdater;

public class EventRegistry {
    public static final ResourceKey<Level> SKYBLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock"));
    public static final ResourceLocation SKYBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "skyblock");
    public static final ResourceKey<Level> STONEBLOCK_DIM = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(SkyblockJumper.MOD_ID, "stoneblock"));
    public static final ResourceLocation STONEBLOCK = new ResourceLocation(SkyblockJumper.MOD_ID, "stoneblock");


    public static void init() {
        TickEvent.SERVER_LEVEL_POST.register(instance -> {
            ServerLevel serverLevel = instance.getLevel();
            FriendlyByteBuf data = new FriendlyByteBuf(Unpooled.buffer());
            StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(serverLevel.getServer());

            if(generateStructure(serverLevel, SKYBLOCK_DIM, SKYBLOCK, serverState.GeneratedSkyblock)) {
                serverState.GeneratedSkyblock=true;
                data.writeBoolean(serverState.GeneratedSkyblock);
            }
            if(generateStructure(serverLevel, STONEBLOCK_DIM, STONEBLOCK, serverState.GeneratedStoneblock)) {
                serverState.GeneratedStoneblock=true;
                data.writeBoolean(serverState.GeneratedStoneblock);
            }
            serverState.setDirty();
        });

    }

    private static boolean generateStructure(ServerLevel serverLevel, ResourceKey<Level> dimension, ResourceLocation structure, boolean generated) {
        if (generated) return false;
        if (!serverLevel.dimension().equals(dimension)) return false;

        StructureTemplate structureTemplate = serverLevel.getStructureManager().getOrCreate(structure);
        Vec3i vec3i = structureTemplate.getSize();
        structureTemplate.placeInWorld(serverLevel, new BlockPos(0,127,0),
                new BlockPos(vec3i.getX(), vec3i.getY() + 127, vec3i.getZ()), new StructurePlaceSettings(), serverLevel.getRandom(), Block.UPDATE_ALL);
        return true;
    }
}
