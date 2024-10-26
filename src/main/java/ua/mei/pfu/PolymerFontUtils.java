package ua.mei.pfu;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.MutableText;
import ua.mei.pfu.font.FontResourceManager;

import java.util.HashMap;
import java.util.Map;

public class PolymerFontUtils implements ModInitializer {
    public static FontResourceManager spaceManager;
    public static Map<Integer, MutableText> spaceMap = new HashMap<>();

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfu");

        spaceManager = FontResourceManager.create("pfu", "spaces");

        for (int i = -256; i <= 256; i++) {
            if (i != 0) {
                spaceMap.put(i, spaceManager.requestSpace(i));
            }
        }

        CommandRegistrationCallback.EVENT.register(Commands::register);

        PolymerResourcePackUtils.RESOURCE_PACK_CREATION_EVENT.register(builder -> {
            for (FontResourceManager manager : FontResourceManager.managers) {
                String path = "assets/" + manager.namespace + "/font/" + manager.name + ".json";
                builder.addData(path, manager.getBytes());
            }
        });
    }
}
