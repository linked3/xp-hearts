package net.linkedto.xp_hearts.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.linkedto.xp_hearts.XpHearts;
import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.linkedto.xp_hearts.item.PendantOfKeepingItem;
import net.linkedto.xp_hearts.item.PendantOfWisdomItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(XpHearts.MOD_ID, Registries.ITEM);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(XpHearts.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> XP_HEARTS_TAB = TABS.register("xp_hearts", () ->
            CreativeTabRegistry.create(
                    Component.translatable("category." + XpHearts.MOD_ID),
                    () -> new ItemStack(ModItems.AMULET_OF_KEEPING.get())
            ));

    public static final RegistrySupplier<AmuletOfKeepingItem> AMULET_OF_KEEPING = ITEMS.register("amulet_of_keeping", () ->
            new AmuletOfKeepingItem(new Item.Properties().stacksTo(1).arch$tab(XP_HEARTS_TAB)));

    public static final RegistrySupplier<AmuletOfWisdomItem> AMULET_OF_WISDOM = ITEMS.register("amulet_of_wisdom", () ->
            new AmuletOfWisdomItem(new Item.Properties().stacksTo(1).arch$tab(XP_HEARTS_TAB)));

    public static final RegistrySupplier<PendantOfKeepingItem> PENDANT_OF_KEEPING = ITEMS.register("pendant_of_keeping", () ->
            new PendantOfKeepingItem(new Item.Properties().stacksTo(64).arch$tab(XP_HEARTS_TAB)));

    public static final RegistrySupplier<PendantOfWisdomItem> PENDANT_OF_WISDOM = ITEMS.register("pendant_of_wisdom", () ->
            new PendantOfWisdomItem(new Item.Properties().stacksTo(64).arch$tab(XP_HEARTS_TAB)));

    public static void register() {
        TABS.register();
        ITEMS.register();
    }
}
