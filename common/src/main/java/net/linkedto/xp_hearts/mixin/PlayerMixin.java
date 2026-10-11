package net.linkedto.xp_hearts.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(
        method = "getExperienceReward",
        at = @At("RETURN"),
        cancellable = true
    )
    private void uncapXpDrop(Player player, CallbackInfoReturnable<Integer> cir) {
        if (!((Object)this instanceof ServerPlayer)) return;
        cir.setReturnValue(((ServerPlayer)(Object)this).experienceLevel * 7);
    }
}
