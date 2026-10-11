package net.linkedto.xp_hearts.helper.neoforge;

import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Optional;

public class CharmHelperImpl {
    public static void registerCharmAsCurio(Item charmItem) {
        CuriosApi.registerCurio(charmItem, new ICurioItem() {});
    }

    public static boolean hasCharmOfKeeping(LivingEntity entity) {
        var handler = CuriosApi.getCuriosInventory(entity).orElse(null);
        if (handler == null) return false;
        return !handler.findCurios(s -> s.getItem() instanceof AmuletOfKeepingItem).isEmpty();
    }

    public static boolean hasCharmOfWisdom(LivingEntity entity) {
        var handler = CuriosApi.getCuriosInventory(entity).orElse(null);
        if (handler == null) return false;
        return !handler.findCurios(s -> s.getItem() instanceof AmuletOfWisdomItem).isEmpty();
    }

    public static Optional<ItemStack> getEquippedAmuletOfWisdom(LivingEntity entity) {
        var handler = CuriosApi.getCuriosInventory(entity).orElse(null);
        if (handler == null) return Optional.empty();
        return handler.findFirstCurio(s -> s.getItem() instanceof AmuletOfWisdomItem)
                .map(result -> result.stack().copy());
    }

    public static boolean equipAmuletOfWisdom(LivingEntity entity, ItemStack stack) {
        var handler = CuriosApi.getCuriosInventory(entity).orElse(null);
        if (handler == null) return false;
        var stacksHandler = handler.getStacksHandler("necklace").orElse(null);
        if (stacksHandler == null) return false;
        var stacks = stacksHandler.getStacks();
        if (stacks.getSlots() < 1) return false;
        stacks.setStackInSlot(0, stack);
        return true;
    }
}
