package net.linkedto.xp_hearts.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.linkedto.xp_hearts.XpHearts;
import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.linkedto.xp_hearts.item.PendantOfKeepingItem;
import net.linkedto.xp_hearts.item.PendantOfWisdomItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(XpHearts.MOD_ID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<AmuletOfKeepingItem> AMULET_OF_KEEPING = ITEMS.register("amulet_of_keeping", () ->
            new AmuletOfKeepingItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistrySupplier<AmuletOfWisdomItem> AMULET_OF_WISDOM = ITEMS.register("amulet_of_wisdom", () ->
            new AmuletOfWisdomItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistrySupplier<PendantOfKeepingItem> PENDANT_OF_KEEPING = ITEMS.register("pendant_of_keeping", () ->
            new PendantOfKeepingItem(new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistrySupplier<PendantOfWisdomItem> PENDANT_OF_WISDOM = ITEMS.register("pendant_of_wisdom", () ->
            new PendantOfWisdomItem(new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static void register() {
        ITEMS.register();
    }
}
