package net.linkedto.xp_hearts.config;

import dev.architectury.platform.Platform;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.linkedto.xp_hearts.XpHearts;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class XpHeartsConfig {
    private static XpHeartsConfig INSTANCE;

    public boolean amuletOfKeepingEnabled = true;
    public boolean pvpXpTransfer = true;
    public boolean amuletOfWisdomEnabled = true;
    public int xpDropMultiplier = 7;
    public boolean xpHeartsEnabled = true;
    public int levelsPerHeart = 5;
    public int hpPerHeart = 2;

    public static XpHeartsConfig get() {
        if (INSTANCE == null) {
            INSTANCE = new XpHeartsConfig();
        }
        return INSTANCE;
    }

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config." + XpHearts.MOD_ID + ".title"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("config." + XpHearts.MOD_ID + ".general"));

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".amulet_of_keeping"),
                        get().amuletOfKeepingEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> get().amuletOfKeepingEnabled = v)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".pvp_xp_transfer"),
                        get().pvpXpTransfer)
                .setDefaultValue(true)
                .setSaveConsumer(v -> get().pvpXpTransfer = v)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".amulet_of_wisdom"),
                        get().amuletOfWisdomEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> get().amuletOfWisdomEnabled = v)
                .build());

        general.addEntry(entryBuilder.startIntField(
                        Component.translatable("config." + XpHearts.MOD_ID + ".xp_drop_multiplier"),
                        get().xpDropMultiplier)
                .setDefaultValue(7)
                .setMin(1)
                .setSaveConsumer(v -> get().xpDropMultiplier = v)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".xp_hearts_enabled"),
                        get().xpHeartsEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> get().xpHeartsEnabled = v)
                .build());

        general.addEntry(entryBuilder.startIntField(
                        Component.translatable("config." + XpHearts.MOD_ID + ".levels_per_heart"),
                        get().levelsPerHeart)
                .setDefaultValue(5)
                .setMin(1)
                .setSaveConsumer(v -> get().levelsPerHeart = v)
                .build());

        general.addEntry(entryBuilder.startIntField(
                        Component.translatable("config." + XpHearts.MOD_ID + ".hp_per_heart"),
                        get().hpPerHeart)
                .setDefaultValue(2)
                .setMin(1)
                .setSaveConsumer(v -> get().hpPerHeart = v)
                .build());

        return builder.build();
    }

    public static void registerConfigScreen() {
        Platform.getMod(XpHearts.MOD_ID).registerConfigurationScreen(XpHeartsConfig::createConfigScreen);
    }
}
