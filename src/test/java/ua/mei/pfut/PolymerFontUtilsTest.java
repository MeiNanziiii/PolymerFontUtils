package ua.mei.pfut;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import ua.mei.pfu.api.font.BitmapGlyph;
import ua.mei.pfu.api.font.FontResourceManager;
import ua.mei.pfu.api.font.provider.BitmapFontProvider;

public class PolymerFontUtilsTest implements ModInitializer {
    public static FontResourceManager manager;
    public static BitmapGlyph bitmap;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfut");

        manager = FontResourceManager.create("pfut", "pfut", "test");
        bitmap = manager.requestGlyph("font/icon.png", 32);
    }
}
