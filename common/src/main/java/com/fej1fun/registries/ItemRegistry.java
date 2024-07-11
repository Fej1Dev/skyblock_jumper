package com.fej1fun.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import static com.fej1fun.SkyblockJumper.MOD_ID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final DeferredSupplier<Item> CORUNDUM_ORE = ITEMS.register("corundum_ore", () -> new BlockItem(BlockRegistry.CORUNDUM_ORE.get(), new Item.Properties().arch$tab(CreativeModeTabs.NATURAL_BLOCKS)));
    public static final DeferredSupplier<Item> RAW_CORUNDUM = ITEMS.register("raw_corundum", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));
    public static final DeferredSupplier<Item> CORUNDUM_INGOT = ITEMS.register("corundum_ingot", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.INGREDIENTS)));
}
