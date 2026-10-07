package net.linkedto.xp_hearts.helper;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class CharmHelper {
    @ExpectPlatform
    public static void registerCharmAsCurio(Item charmItem) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean hasCharmOfKeeping(LivingEntity entity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean hasCharmOfWisdom(LivingEntity entity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Optional<ItemStack> getEquippedAmuletOfWisdom(LivingEntity entity) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean equipAmuletOfWisdom(LivingEntity entity, ItemStack stack) {
        throw new AssertionError();
    }
}
