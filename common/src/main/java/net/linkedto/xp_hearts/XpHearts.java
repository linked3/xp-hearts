package net.linkedto.xp_hearts;

import net.linkedto.xp_hearts.config.XpHeartsConfig;
import net.linkedto.xp_hearts.handler.DeathHandler;
import net.linkedto.xp_hearts.helper.CharmHelper;
import net.linkedto.xp_hearts.init.ModItems;

public final class XpHearts {
    public static final String MOD_ID = "xp_hearts";

    public static void init() {
        ModItems.register();
        DeathHandler.register();

        ModItems.AMULET_OF_KEEPING.listen(charm -> CharmHelper.registerCharmAsCurio(charm));
        ModItems.AMULET_OF_WISDOM.listen(charm -> CharmHelper.registerCharmAsCurio(charm));
    }

    public static void initClient() {
        XpHeartsConfig.registerConfigScreen();
    }
}
