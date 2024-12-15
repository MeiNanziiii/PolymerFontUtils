package ua.mei.pfu.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.pfu.api.FontResource;
import ua.mei.pfu.api.FontResourceManager;
import ua.mei.pfu.api.TextResource;

@ApiStatus.Internal
public class PolymerFontUtilsImpl implements ModInitializer {
    public static final FontResourceManager manager = FontResourceManager.create("pfu");
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Identifier.class, new Identifier.Serializer())
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfu");

        CommandRegistrationCallback.EVENT.register(Commands::register);

        PolymerResourcePackUtils.RESOURCE_PACK_CREATION_EVENT.register(builder -> {
            for (FontResource resource : FontResource.resources) {
                String path = "assets/" + resource.identifier.getNamespace() + "/font/" + resource.identifier.getPath() + ".json";

                byte[] bytes = ("{\"providers\":" + gson.toJson(resource.providers) + "}").getBytes();

                builder.addData(path, bytes);
            }
            for (TextResource resource : TextResource.resources) {
                String path = "assets/" + resource.identifier.getNamespace() + "/font/" + resource.identifier.getPath() + ".json";

                byte[] bytes = resource.json.getBytes();

                builder.addData(path, bytes);
            }
        });
    }
}
