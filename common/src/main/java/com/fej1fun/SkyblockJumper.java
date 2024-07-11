package com.fej1fun;

import com.fej1fun.registries.EventRegistry;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SkyblockJumper {
    public static final String MOD_ID = "skyblock_jumper";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);
    public static final DeferredSupplier<Block> CORUNDUM_ORE = BLOCKS.register("corundum_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final DeferredSupplier<Item> CORUNDUM_ORE_ITEM = ITEMS.register("corundum_ore", () -> new BlockItem(CORUNDUM_ORE.get(), new Item.Properties().arch$tab(CreativeModeTabs.NATURAL_BLOCKS)));
    public static final DeferredSupplier<Item> RAW_CORUNDUM = ITEMS.register("raw_corundum", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));
    public static final DeferredSupplier<Item> CORUNDUM_INGOT = ITEMS.register("corundum_ingot", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));


    public static void init() {
        // Write common init code here.

        EventRegistry.init();
        BLOCKS.register();
        ITEMS.register();

        //Client
        ClientLifecycleEvent.CLIENT_SETUP.register(minecraft -> {
        });

        LOG.info("finished loading");
    }
}
