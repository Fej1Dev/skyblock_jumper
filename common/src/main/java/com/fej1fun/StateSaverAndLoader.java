package com.fej1fun;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

public class StateSaverAndLoader extends SavedData {
    public boolean GeneratedSkyblock = false;
    public boolean GeneratedStoneblock = false;
    public boolean GeneratedWaterblock = false;
    public boolean GeneratedLavablock = false;

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("generated_skyblock", GeneratedSkyblock);
        tag.putBoolean("generated_stoneblock", GeneratedStoneblock);
        tag.putBoolean("generated_waterblock", GeneratedWaterblock);
        tag.putBoolean("generated_lavablock", GeneratedLavablock);
        setDirty();
        return tag;
    }

    public static StateSaverAndLoader createFromNbt(CompoundTag tag) {
        StateSaverAndLoader state = new StateSaverAndLoader();
        state.GeneratedSkyblock = tag.getBoolean("generated_skyblock");
        state.GeneratedStoneblock = tag.getBoolean("generated_stoneblock");
        state.GeneratedWaterblock = tag.getBoolean("generated_waterblock");
        state.GeneratedLavablock = tag.getBoolean("generated_lavablock");
        return state;
    }

    public static StateSaverAndLoader getServerState(MinecraftServer server) {
        DimensionDataStorage persistentStateManager = server.getLevel(Level.OVERWORLD).getDataStorage();

        StateSaverAndLoader state = persistentStateManager.computeIfAbsent(
                StateSaverAndLoader::createFromNbt,
                StateSaverAndLoader::new,
                SkyblockJumper.MOD_ID
        );
        state.setDirty();
        return state;
    }
}
