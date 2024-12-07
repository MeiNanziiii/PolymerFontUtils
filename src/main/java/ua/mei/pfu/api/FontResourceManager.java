package ua.mei.pfu.api;

import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class FontResourceManager {
    public static final Set<FontResourceManager> managers = new HashSet<>();

    public final String modId;

    private FontResourceManager(String modId) {
        this.modId = modId;
    }

    public static FontResourceManager create(String modId) {
        return managers.stream()
                .filter(manager -> manager.modId.equals(modId))
                .findFirst()
                .orElseGet(() -> {
                    FontResourceManager manager = new FontResourceManager(modId);
                    managers.add(manager);
                    return manager;
                });
    }

    public FontResource requestFont(Identifier identifier) {
        return FontResource.resources.stream()
                .filter(resource -> resource.identifier.equals(identifier))
                .findFirst()
                .orElseGet(() -> {
                    FontResource resource = new FontResource(identifier, this);
                    FontResource.resources.add(resource);
                    return resource;
                });
    }

    public FontResource requestFont(String namespace, String path) {
        return requestFont(Identifier.of(namespace, path));
    }

    public FontResource requestFont(String path) {
        return requestFont(this.modId, path);
    }
}
