package net.linkedto.xp_hearts.forge;

import net.linkedto.xp_hearts.XpHearts;
import net.minecraftforge.fml.common.Mod;

@Mod(XpHearts.MOD_ID)
public final class XpHeartsForge {
    public XpHeartsForge() {
        // Run our common setup.
        XpHearts.init();
    }
}
