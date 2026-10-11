package net.linkedto.xp_hearts.mixin;

import net.linkedto.xp_hearts.handler.DeathHandler;
import net.linkedto.xp_hearts.helper.CharmHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(
        method = "dropAllDeathLoot",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onDropAllDeathLoot(ServerLevel level, DamageSource source, CallbackInfo ci) {
        if (!((Object)this instanceof ServerPlayer player)) return;
        boolean hasKeeping = CharmHelper.hasCharmOfKeeping(player);
        boolean hasWisdom = CharmHelper.hasCharmOfWisdom(player);

        if (hasWisdom) {
            DeathHandler.WISDOM_ACTIVE.add(player.getUUID());
            CharmHelper.getEquippedAmuletOfWisdom(player).ifPresent(stack ->
                    DeathHandler.CACHED_WISDOM_AMULETS.put(player.getUUID(), stack));
        }

        if (hasKeeping) {
            ci.cancel();
            if (!hasWisdom) {
                level.addFreshEntity(new ExperienceOrb(level, player.getX(), player.getY(), player.getZ(), player.experienceLevel * 7));
            }
        }
    }

    @Inject(
        method = "dropExperience(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onDropExperience(ServerLevel level, Entity killer, CallbackInfo ci) {
        if (!((Object)this instanceof ServerPlayer player)) return;

        if (DeathHandler.WISDOM_ACTIVE.contains(player.getUUID())) {
            ci.cancel();
            return;
        }
        if (DeathHandler.SKIP_XP_DROP.remove(player.getUUID())) {
            ci.cancel();
        }
    }
}
