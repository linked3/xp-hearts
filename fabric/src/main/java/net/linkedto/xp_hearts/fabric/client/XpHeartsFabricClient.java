package net.linkedto.xp_hearts.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.linkedto.xp_hearts.XpHearts;

public final class XpHeartsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        XpHearts.initClient();
    }
}
