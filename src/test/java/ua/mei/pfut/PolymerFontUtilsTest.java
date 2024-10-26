package ua.mei.pfut;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import ua.mei.pfu.font.FontResourceManager;
import ua.mei.pfu.font.provider.BitmapFontProvider;

public class PolymerFontUtilsTest implements ModInitializer {
    public static FontResourceManager manager;
    public static BitmapFontProvider bitmap;
    public static Text space;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfut");

        manager = FontResourceManager.create("pfut", "test");
        bitmap = manager.requestBitmap("font/icon.png", 128, 64);
        // Spaces not displaying in /pfu pfut:test
        space = manager.requestSpace(356);
    }
}
