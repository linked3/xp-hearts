package net.linkedto.xp_hearts.handler;

import net.linkedto.xp_hearts.config.XpHeartsConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class XpHeartHandler {
    private static final UUID BONUS_HEALTH_UUID = UUID.fromString("b3a9e2c4-1d8f-4a7b-9c5e-6f2d8b4a1c3e");
    private static final String MODIFIER_NAME = "XP Hearts bonus";

    public static void update(ServerPlayer player) {
        XpHeartsConfig config = XpHeartsConfig.get();
        if (!config.xpHeartsEnabled) return;

        int bonusHearts = player.experienceLevel / config.levelsPerHeart;
        double targetBonus = bonusHearts * config.hpPerHeart;

        AttributeInstance attr = player.getAttribute(Attributes.MAX_HEALTH);
        if (attr == null) return;

        AttributeModifier mod = attr.getModifier(BONUS_HEALTH_UUID);
        double currentBonus = mod != null ? mod.getAmount() : 0.0;

        if (Math.abs(currentBonus - targetBonus) < 0.01) return;

        attr.removeModifier(BONUS_HEALTH_UUID);
        if (targetBonus > 0) {
            attr.addTransientModifier(new AttributeModifier(BONUS_HEALTH_UUID, MODIFIER_NAME, targetBonus, AttributeModifier.Operation.ADDITION));
        }
    }
}
