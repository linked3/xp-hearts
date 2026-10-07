package net.linkedto.xp_hearts.handler;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.linkedto.xp_hearts.config.XpHeartsConfig;
import net.linkedto.xp_hearts.helper.CharmHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DeathHandler {
    public static final Set<UUID> SKIP_XP_DROP = new HashSet<>();
    public static final Set<UUID> WISDOM_ACTIVE = new HashSet<>();
    public static final Map<UUID, ItemStack> CACHED_WISDOM_AMULETS = new HashMap<>();

    public static void register() {
        EntityEvent.LIVING_DEATH.register((entity, source) -> {
            if (!(entity instanceof ServerPlayer killed)) return EventResult.pass();
            if (!XpHeartsConfig.get().pvpXpTransfer) return EventResult.pass();
            if (!(source.getEntity() instanceof ServerPlayer killer)) return EventResult.pass();

            if (CharmHelper.hasCharmOfKeeping(killed)) {
                int xpToTransfer = killed.experienceLevel * XpHeartsConfig.get().xpDropMultiplier;
                killer.giveExperiencePoints(xpToTransfer);
                SKIP_XP_DROP.add(killed.getUUID());
            }

            return EventResult.pass();
        });
    }
}
