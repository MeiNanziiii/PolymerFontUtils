package ua.mei.pfu.api.font;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ua.mei.pfu.api.font.provider.BitmapFontProvider;
import ua.mei.pfu.api.font.provider.FontProvider;
import ua.mei.pfu.api.font.provider.SpaceFontProvider;
import ua.mei.pfu.api.font.util.UnicodeGenerator;

import java.util.*;

import static java.lang.reflect.Modifier.TRANSIENT;

public class FontResourceManager {
    public static final Set<FontResourceManager> managers = new HashSet<>();
    public static final Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(TRANSIENT)
            .registerTypeAdapter(Identifier.class, new Identifier.Serializer())
            .create();

    public final String modId;
    public final String namespace;
    public final String name;
    public final Identifier identifier;
    public final UnicodeGenerator generator;
    public final List<BitmapGlyph> glyphs = new ArrayList<>();
    public final SpaceFontProvider spaceProvider;

    private FontResourceManager(String modId, String namespace, String name) {
        this.modId = modId;
        this.namespace = namespace;
        this.name = name;
        this.identifier = Identifier.of(namespace, name);
        this.generator = new UnicodeGenerator();
        this.spaceProvider = new SpaceFontProvider(new HashMap<>());
    }

    public static FontResourceManager create(String modId, String namespace, String name) {
        if (namespace.equals("minecraft")) {
            throw new IllegalStateException("Cannot create manager with \"minecraft\" namespace.");
        }

        Optional<FontResourceManager> existingManager = managers.stream()
                .filter(manager -> manager.namespace.equals(namespace) && manager.name.equals(name))
                .findFirst();

        if (existingManager.isPresent()) {
            return existingManager.get();
        }

        FontResourceManager manager = new FontResourceManager(modId, namespace, name);
        managers.add(manager);
        return manager;
    }

    private static <K, V> Optional<K> getKeyByValue(Map<K, V> map, V value) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public byte[] getBytes() {
        List<FontProvider> providers = new ArrayList<>();

        if (!spaceProvider.advances().isEmpty()) {
            providers.add(spaceProvider);
        }
        providers.addAll(glyphs.stream().map(glyph -> glyph.provider).toList());

        return ("{\"providers\":" + gson.toJson(providers) + "}").getBytes();
    }

    public BitmapGlyph requestGlyph(String path, int height, int ascent) {
        Optional<BitmapGlyph> existingProvider = glyphs.stream()
                .filter(glyph -> glyph.provider.file().equals(namespace + ":" + path) && glyph.provider.height() == height && glyph.provider.ascent() == ascent)
                .findFirst();

        if (existingProvider.isPresent()) {
            return existingProvider.get();
        }

        BitmapGlyph glyph = BitmapGlyph.create(this, path, height, ascent);
        glyphs.add(glyph);
        return glyph;
    }

    public BitmapGlyph requestGlyph(String path, int ascent) {
        return requestGlyph(path, -1, ascent);
    }

    /**
     * @deprecated This method is deprecated and will be removed in future versions.
     * <hr/>
     * Use {@link #requestGlyph(String, int, int)} instead.
     */
    @Deprecated
    public BitmapFontProvider requestBitmap(String path, int height, int ascent) {
        return requestGlyph(path, height, ascent).provider;
    }

    public MutableText requestSpace(int advance) {
        if (advance == 0) {
            return Text.empty();
        }

        Map<String, Integer> advances = spaceProvider.advances();

        if (!advances.isEmpty()) {
            Optional<String> space = getKeyByValue(advances, advance);

            if (space.isPresent()) {
                return Text.literal(space.get()).styled(style -> style.withFont(identifier));
            }
        }

        String unicode = generator.requestUnicode();
        advances.put(unicode, advance);
        return Text.literal(unicode).styled(style -> style.withFont(identifier));
    }
}
