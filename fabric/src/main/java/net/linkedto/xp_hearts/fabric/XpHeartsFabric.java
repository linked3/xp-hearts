package net.linkedto.xp_hearts.fabric;

import eu.pb4.trinkets.api.TrinketDropRule;
import eu.pb4.trinkets.api.event.TrinketDropCallback;
import net.fabricmc.api.ModInitializer;
import net.linkedto.xp_hearts.XpHearts;
import net.linkedto.xp_hearts.item.AmuletOfKeepingItem;
import net.linkedto.xp_hearts.item.AmuletOfWisdomItem;
import net.minecraft.resources.Identifier;

public final class XpHeartsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Run our common setup.
        XpHearts.init();

        // Keep both amulets in their trinket slot on death (no ground drop).
        TrinketDropCallback.EVENT.register(
                Identifier.fromNamespaceAndPath(XpHearts.MOD_ID, "keep_amulets"),
                (rule, stack, slot, entity) ->
                        (stack.getItem() instanceof AmuletOfKeepingItem
                                || stack.getItem() instanceof AmuletOfWisdomItem)
                                ? TrinketDropRule.KEEP
                                : rule);
    }
}
