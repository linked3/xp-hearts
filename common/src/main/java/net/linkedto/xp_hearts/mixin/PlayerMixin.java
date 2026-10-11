package net.linkedto.xp_hearts.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(
        method = "getBaseExperienceReward(Lnet/minecraft/server/level/ServerLevel;)I",
        at = @At("HEAD"),
        cancellable = true
    )
    private void uncapXpDrop(ServerLevel level, CallbackInfoReturnable<Integer> cir) {
        if (!((Object)this instanceof ServerPlayer)) return;
        cir.setReturnValue(((ServerPlayer)(Object)this).experienceLevel * 7);
    }
}
