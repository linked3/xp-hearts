package net.linkedto.xp_hearts.handler;

import net.linkedto.xp_hearts.XpHearts;
import net.linkedto.xp_hearts.config.XpHeartsConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class XpHeartHandler {
    private static final ResourceLocation BONUS_HEALTH_ID = ResourceLocation.fromNamespaceAndPath(XpHearts.MOD_ID, "bonus_health");

    public static void update(ServerPlayer player) {
        XpHeartsConfig config = XpHeartsConfig.get();
        if (!config.xpHeartsEnabled) return;

        int bonusHearts = player.experienceLevel / config.levelsPerHeart;
        double targetBonus = bonusHearts * config.hpPerHeart;

        AttributeInstance attr = player.getAttribute(Attributes.MAX_HEALTH);
        if (attr == null) return;

        AttributeModifier mod = attr.getModifier(BONUS_HEALTH_ID);
        double currentBonus = mod != null ? mod.amount() : 0.0;

        if (Math.abs(currentBonus - targetBonus) < 0.01) return;

        attr.removeModifier(BONUS_HEALTH_ID);
        if (targetBonus > 0) {
            attr.addTransientModifier(new AttributeModifier(BONUS_HEALTH_ID, targetBonus, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}
