package ua.mei.pfu.api;

import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class FontResourceManager {
    public static final Set<FontResourceManager> managers = new HashSet<>();

    public final String modId;

    private FontResourceManager(String modId) {
        this.modId = modId;

        managers.add(this);
    }

    public static FontResourceManager create(String modId) {
        return managers.stream()
                .filter(manager -> manager.modId.equals(modId))
                .findFirst()
                .orElseGet(() -> new FontResourceManager(modId));
    }

    public FontResource requestFont(Identifier identifier) {
        return FontResource.resources.stream()
                .filter(resource -> resource.identifier.equals(identifier))
                .findFirst()
                .orElseGet(() -> new FontResource(identifier, this));
    }

    public FontResource requestFont(String namespace, String path) {
        return requestFont(Identifier.of(namespace, path));
    }

    public FontResource requestFont(String path) {
        return requestFont(this.modId, path);
    }

    public TextResource requestText(Identifier identifier, int ascent) {
        return TextResource.resources.stream()
                .filter(resource -> resource.identifier.equals(identifier) && resource.ascent == ascent)
                .findFirst()
                .orElseGet(() -> new TextResource(identifier, ascent, this));
    }

    public TextResource requestText(String namespace, String path, int ascent) {
        return requestText(Identifier.of(namespace, path), ascent);
    }

    public TextResource requestText(String path, int ascent) {
        return requestText(this.modId, path, ascent);
    }

    public TextResource requestText(int ascent) {
        return requestText(this.modId, "text/default_" + ascent, ascent);
    }
}
