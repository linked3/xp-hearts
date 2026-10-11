package net.linkedto.xp_hearts.helper.fabric;

import eu.pb4.trinkets.api.SlotType;
import eu.pb4.trinkets.api.TrinketAttachment;
import eu.pb4.trinkets.api.TrinketInventory;
import eu.pb4.trinkets.api.TrinketSlotAccess;
import eu.pb4.trinkets.api.TrinketsApi;
import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class CharmHelperImpl {

    public static void registerCharmAsCurio(Item charmItem) {
        // Trinkets Updated is data-driven; equippability is defined by item tags.
    }

    public static boolean hasCharmOfKeeping(LivingEntity entity) {
        return TrinketsApi.getAttachment(entity).isEquipped(s -> s.getItem() instanceof AmuletOfKeepingItem);
    }

    public static boolean hasCharmOfWisdom(LivingEntity entity) {
        return TrinketsApi.getAttachment(entity).isEquipped(s -> s.getItem() instanceof AmuletOfWisdomItem);
    }

    public static Optional<ItemStack> getEquippedAmuletOfWisdom(LivingEntity entity) {
        return TrinketsApi.getAttachment(entity)
                .findFirst(s -> s.getItem() instanceof AmuletOfWisdomItem)
                .map(access -> access.get().copy());
    }

    public static boolean equipAmuletOfWisdom(LivingEntity entity, ItemStack stack) {
        TrinketAttachment attachment = TrinketsApi.getAttachment(entity);
        for (TrinketInventory inventory : attachment.getInventories().values()) {
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                TrinketSlotAccess access = inventory.getSlotAccess(i);
                if (access != null && isNecklace(access.slotType())) {
                    return access.set(stack);
                }
            }
        }
        return false;
    }

    private static boolean isNecklace(SlotType slotType) {
        if (slotType == null || slotType.getId() == null) {
            return false;
        }
        return slotType.getId().contains("necklace");
    }
}
