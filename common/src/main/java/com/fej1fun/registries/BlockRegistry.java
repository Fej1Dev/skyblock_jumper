package com.fej1fun.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.fej1fun.SkyblockJumper.MOD_ID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final DeferredSupplier<Block> WOOD_PORTAL_BLOCK = BLOCKS.register("wood_portal_block", () -> new NetherPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL)));
}
