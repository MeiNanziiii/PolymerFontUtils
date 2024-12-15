package ua.mei.pfut;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import ua.mei.pfu.api.BitmapGlyph;
import ua.mei.pfu.api.FontResource;
import ua.mei.pfu.api.FontResourceManager;
import ua.mei.pfu.api.TextResource;
import ua.mei.pfu.api.util.FontSpaceUtils;

public class PolymerFontUtilsTest implements ModInitializer {
    public static FontResourceManager manager;
    public static FontResource resource;
    public static TextResource text;
    public static BitmapGlyph glyph;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfut");

        manager = FontResourceManager.create("pfut");
        resource = manager.requestFont("test");
        text = manager.requestText(-20); // Font identifier: pfut:text/default_-20
        glyph = resource.requestGlyph("font/icon.png", 128, 32);

        FontSpaceUtils.requestAdvance(-256);
        FontSpaceUtils.requestAdvances(-128, 1, 3);
        FontSpaceUtils.requestRange(2, 19);
    }
}
