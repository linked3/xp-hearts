package net.linkedto.xp_hearts.helper.neoforge;

import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Optional;

public class CharmHelperImpl {
    public static void registerCharmAsCurio(Item charmItem) {
        CuriosApi.registerCurio(charmItem, new ICurioItem() {
            @Override
            public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
                return ICurio.DropRule.ALWAYS_KEEP;
            }
        });
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
        for (var entry : handler.getCurios().entrySet()) {
            var stacks = entry.getValue().getStacks();
            for (int i = 0; i < stacks.getSlots(); i++) {
                ItemStack stack = stacks.getStackInSlot(i);
                if (stack.getItem() instanceof AmuletOfWisdomItem) {
                    return Optional.of(stack.copy());
                }
            }
        }
        return Optional.empty();
    }

    public static boolean equipAmuletOfWisdom(LivingEntity entity, ItemStack stack) {
        var handler = CuriosApi.getCuriosInventory(entity).orElse(null);
        if (handler == null) return false;
        var slotHandler = handler.getCurios().get("necklace");
        if (slotHandler == null) return false;
        slotHandler.getStacks().setStackInSlot(0, stack);
        return true;
    }
}
