package net.linkedto.xp_hearts.config;

import dev.architectury.platform.Platform;
import net.linkedto.xp_hearts.XpHearts;
import net.minecraft.client.gui.screens.Screen;

public class XpHeartsConfig {
    private static XpHeartsConfig INSTANCE;

    private static final String CLOTH_CONFIG_CLASS = "me.shedaniel.clothconfig2.api.ConfigBuilder";
    private static final String CLOTH_CONFIG_SCREEN = "net.linkedto.xp_hearts.config.XpHeartsClothConfig";

    public boolean amuletOfKeepingEnabled = true;
    public boolean pvpXpTransfer = true;
    public boolean amuletOfWisdomEnabled = true;
    public int xpDropMultiplier = 7;
    public boolean xpHeartsEnabled = true;
    public int levelsPerHeart = 10;
    public int hpPerHeart = 2;

    public static XpHeartsConfig get() {
        if (INSTANCE == null) {
            INSTANCE = new XpHeartsConfig();
        }
        return INSTANCE;
    }

    public static void registerConfigScreen() {
        if (!isClothConfigAvailable()) {
            return;
        }
        Platform.getMod(XpHearts.MOD_ID).registerConfigurationScreen(XpHeartsConfig::createConfigScreen);
    }

    private static Screen createConfigScreen(Screen parent) {
        try {
            Class<?> clazz = Class.forName(CLOTH_CONFIG_SCREEN);
            return (Screen) clazz.getMethod("create", Screen.class).invoke(null, parent);
        } catch (Throwable t) {
            return parent;
        }
    }

    private static boolean isClothConfigAvailable() {
        try {
            Class.forName(CLOTH_CONFIG_CLASS);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
}
