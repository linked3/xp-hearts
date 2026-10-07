package net.linkedto.xp_hearts.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class PlayerMixin {

    @Inject(
        method = "getExperienceReward(Lnet/minecraft/server/level/ServerLevel;Z)I",
        at = @At("RETURN"),
        cancellable = true
    )
    private void uncapXpDrop(ServerLevel level, boolean p, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(((ServerPlayer)(Object)this).experienceLevel * 7);
    }
}
