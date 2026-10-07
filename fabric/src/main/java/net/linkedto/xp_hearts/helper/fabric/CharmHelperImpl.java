package net.linkedto.xp_hearts.helper.fabric;

import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class CharmHelperImpl {

    public static void registerCharmAsCurio(Item charmItem) {
        TrinketsApi.registerTrinket(charmItem, new Trinket() {});
    }

    public static boolean hasCharmOfKeeping(LivingEntity entity) {
        var component = TrinketsApi.getTrinketComponent(entity).orElse(null);
        if (component == null) return false;
        return component.isEquipped(s -> s.getItem() instanceof AmuletOfKeepingItem);
    }

    public static boolean hasCharmOfWisdom(LivingEntity entity) {
        var component = TrinketsApi.getTrinketComponent(entity).orElse(null);
        if (component == null) return false;
        return component.isEquipped(s -> s.getItem() instanceof AmuletOfWisdomItem);
    }

    public static Optional<ItemStack> getEquippedAmuletOfWisdom(LivingEntity entity) {
        var component = TrinketsApi.getTrinketComponent(entity).orElse(null);
        if (component == null) return Optional.empty();
        for (var group : component.getInventory().values()) {
            for (var inv : group.values()) {
                for (int i = 0; i < inv.getContainerSize(); i++) {
                    ItemStack stack = inv.getItem(i);
                    if (stack.getItem() instanceof AmuletOfWisdomItem) {
                        return Optional.of(stack.copy());
                    }
                }
            }
        }
        return Optional.empty();
    }

    public static boolean equipAmuletOfWisdom(LivingEntity entity, ItemStack stack) {
        var component = TrinketsApi.getTrinketComponent(entity).orElse(null);
        if (component == null) return false;
        var group = component.getInventory().get("chest");
        if (group == null) return false;
        var inv = group.get("necklace");
        if (inv == null) return false;
        inv.setItem(0, stack);
        return true;
    }
}
