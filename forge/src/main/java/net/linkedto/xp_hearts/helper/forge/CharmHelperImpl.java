package net.linkedto.xp_hearts.helper.forge;

import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Optional;

public class CharmHelperImpl {
    public static void registerCharmAsCurio(Item charmItem) {
        // Curios 5.x marks items as curios via the curios:<slot> item tag
        // (see data/curios/tags/items/necklace.json).
    }

    public static boolean hasCharmOfKeeping(LivingEntity entity) {
        return !CuriosApi.getCuriosHelper()
                .findCurios(entity, s -> s.getItem() instanceof AmuletOfKeepingItem).isEmpty();
    }

    public static boolean hasCharmOfWisdom(LivingEntity entity) {
        return !CuriosApi.getCuriosHelper()
                .findCurios(entity, s -> s.getItem() instanceof AmuletOfWisdomItem).isEmpty();
    }

    public static Optional<ItemStack> getEquippedAmuletOfWisdom(LivingEntity entity) {
        return CuriosApi.getCuriosHelper()
                .findFirstCurio(entity, s -> s.getItem() instanceof AmuletOfWisdomItem)
                .map(result -> result.stack().copy());
    }

    public static boolean equipAmuletOfWisdom(LivingEntity entity, ItemStack stack) {
        var handler = CuriosApi.getCuriosHelper().getCuriosHandler(entity).orElse(null);
        if (handler == null) return false;
        var slotHandler = handler.getCurios().get("necklace");
        if (slotHandler == null) return false;
        slotHandler.getStacks().setStackInSlot(0, stack);
        return true;
    }
}
