package net.linkedto.xp_hearts.fabric;

import net.linkedto.xp_hearts.XpHearts;
import net.fabricmc.api.ModInitializer;

public final class XpHeartsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        XpHearts.init();
    }
}
