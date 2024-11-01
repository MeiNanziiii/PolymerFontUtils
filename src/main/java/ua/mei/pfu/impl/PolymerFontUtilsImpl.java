package ua.mei.pfu.impl;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.pfu.api.font.FontResourceManager;

import java.util.HashMap;

@ApiStatus.Internal
public class PolymerFontUtilsImpl implements ModInitializer {
    public static FontResourceManager spaceManager;
    public static HashMap<Integer, MutableText> spaceMap = new HashMap<>();

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfu");

        spaceManager = FontResourceManager.create("pfu", "pfu", "spaces");

        for (int i = -256; i <= 256; i++) {
            if (i != 0) {
                MutableText space = spaceManager.requestSpace(i);
                spaceMap.put(i, space);
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
