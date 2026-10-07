package net.linkedto.xp_hearts.mixin;

import net.linkedto.xp_hearts.handler.DeathHandler;
import net.linkedto.xp_hearts.handler.XpHeartHandler;
import net.linkedto.xp_hearts.helper.CharmHelper;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @Unique
    private boolean xpHearts$needsXpSync = false;

    @Unique
    private ItemStack xpHearts$pendingAmulet = null;

    @Unique
    private int xpHearts$amuletRetries = 0;

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void onRestoreFrom(ServerPlayer oldPlayer, boolean keepEverything, CallbackInfo ci) {
        if (keepEverything) return;
        ServerPlayer newPlayer = (ServerPlayer)(Object)this;

        // Use the Wisdom cache first (set during dropAllDeathLoot before trinkets are cleared),
        // fall back to direct charm check for non-standard death flows.
        boolean hasWisdom = DeathHandler.WISDOM_ACTIVE.remove(oldPlayer.getUUID());
        if (!hasWisdom) {
            hasWisdom = CharmHelper.hasCharmOfWisdom(oldPlayer);
        }
        boolean hasKeeping = CharmHelper.hasCharmOfKeeping(oldPlayer);

        if (hasKeeping) {
            var newInv = newPlayer.getInventory();
            var oldInv = oldPlayer.getInventory();
            for (int i = 0; i < newInv.items.size(); i++) {
                newInv.items.set(i, oldInv.items.get(i).copy());
            }
            for (int i = 0; i < newInv.armor.size(); i++) {
                newInv.armor.set(i, oldInv.armor.get(i).copy());
            }
            newInv.offhand.set(0, oldInv.offhand.get(0).copy());
        }

        if (hasWisdom) {
            newPlayer.experienceLevel = oldPlayer.experienceLevel;
            newPlayer.totalExperience = oldPlayer.totalExperience;
            newPlayer.experienceProgress = oldPlayer.experienceProgress;
            xpHearts$needsXpSync = true;

            ItemStack amulet = DeathHandler.CACHED_WISDOM_AMULETS.remove(oldPlayer.getUUID());
            if (amulet != null && !CharmHelper.equipAmuletOfWisdom(newPlayer, amulet)) {
                xpHearts$pendingAmulet = amulet;
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        if (xpHearts$needsXpSync) {
            xpHearts$needsXpSync = false;
            if (player.connection != null) {
                player.connection.send(new ClientboundSetExperiencePacket(
                    player.experienceProgress,
                    player.totalExperience,
                    player.experienceLevel
                ));
            }
        }

        if (xpHearts$pendingAmulet != null) {
            if (CharmHelper.equipAmuletOfWisdom(player, xpHearts$pendingAmulet)) {
                xpHearts$pendingAmulet = null;
                xpHearts$amuletRetries = 0;
            } else if (++xpHearts$amuletRetries > 100) {
                if (!player.getInventory().add(xpHearts$pendingAmulet)) {
                    player.drop(xpHearts$pendingAmulet, false);
                }
                xpHearts$pendingAmulet = null;
                xpHearts$amuletRetries = 0;
            }
        }

        XpHeartHandler.update(player);
    }
}
