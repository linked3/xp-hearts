package net.linkedto.xp_hearts.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.linkedto.xp_hearts.XpHearts;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * All Cloth Config references live here so this class is only loaded when Cloth Config is installed.
 */
public class XpHeartsClothConfig {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config." + XpHearts.MOD_ID + ".title"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("config." + XpHearts.MOD_ID + ".general"));

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".amulet_of_keeping"),
                        XpHeartsConfig.get().amuletOfKeepingEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> XpHeartsConfig.get().amuletOfKeepingEnabled = v)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".pvp_xp_transfer"),
                        XpHeartsConfig.get().pvpXpTransfer)
                .setDefaultValue(true)
                .setSaveConsumer(v -> XpHeartsConfig.get().pvpXpTransfer = v)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".amulet_of_wisdom"),
                        XpHeartsConfig.get().amuletOfWisdomEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> XpHeartsConfig.get().amuletOfWisdomEnabled = v)
                .build());

        general.addEntry(entryBuilder.startIntField(
                        Component.translatable("config." + XpHearts.MOD_ID + ".xp_drop_multiplier"),
                        XpHeartsConfig.get().xpDropMultiplier)
                .setDefaultValue(7)
                .setMin(1)
                .setSaveConsumer(v -> XpHeartsConfig.get().xpDropMultiplier = v)
                .build());

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.translatable("config." + XpHearts.MOD_ID + ".xp_hearts_enabled"),
                        XpHeartsConfig.get().xpHeartsEnabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> XpHeartsConfig.get().xpHeartsEnabled = v)
                .build());

        general.addEntry(entryBuilder.startIntField(
                        Component.translatable("config." + XpHearts.MOD_ID + ".levels_per_heart"),
                        XpHeartsConfig.get().levelsPerHeart)
                .setDefaultValue(10)
                .setMin(1)
                .setSaveConsumer(v -> XpHeartsConfig.get().levelsPerHeart = v)
                .build());

        general.addEntry(entryBuilder.startIntField(
                        Component.translatable("config." + XpHearts.MOD_ID + ".hp_per_heart"),
                        XpHeartsConfig.get().hpPerHeart)
                .setDefaultValue(2)
                .setMin(1)
                .setSaveConsumer(v -> XpHeartsConfig.get().hpPerHeart = v)
                .build());

        return builder.build();
    }
}
