package ua.mei.pfu.font;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ua.mei.pfu.font.provider.BitmapFontProvider;
import ua.mei.pfu.font.provider.FontProvider;
import ua.mei.pfu.font.provider.SpaceFontProvider;
import ua.mei.pfu.util.UnicodeGenerator;

import java.util.*;

import static java.lang.reflect.Modifier.TRANSIENT;

public class FontResourceManager {
    public static final Set<FontResourceManager> managers = new HashSet<>();
    public static final Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(TRANSIENT)
            .registerTypeAdapter(Identifier.class, new Identifier.Serializer())
            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes attributes) {
                    return attributes.getAnnotation(Expose.class) != null;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    public final String namespace;
    public final String name;
    public final Identifier identifier;
    public final UnicodeGenerator generator;
    public final List<FontProvider> providers = new ArrayList<>();
    public final SpaceFontProvider spaceProvider;

    private FontResourceManager(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;
        this.identifier = Identifier.of(namespace, name);
        this.generator = new UnicodeGenerator();
        this.spaceProvider = new SpaceFontProvider(new HashMap<>());
    }

    public static FontResourceManager create(String namespace, String name) {
        if (managers.stream().anyMatch(manager -> manager.namespace.equals(namespace) && manager.name.equals(name))) {
            throw new IllegalStateException("This identifier already requested.");
        }

        FontResourceManager manager = new FontResourceManager(namespace, name);
        managers.add(manager);
        return manager;
    }

    public byte[] getBytes() {
        return ("{\"providers\":" + gson.toJson(providers) + "}").getBytes();
    }

    public BitmapFontProvider requestBitmap(String path, int height, int ascent) {
        String unicode = generator.requestUnicode();
        BitmapFontProvider provider = new BitmapFontProvider(namespace + ":" + path, height, ascent, List.of(unicode), identifier);
        providers.add(provider);
        return provider;
    }

    public MutableText requestSpace(int advance) {
        if (spaceProvider.advances().isEmpty()) {
            providers.add(spaceProvider);
        }
        String unicode = generator.requestUnicode();
        spaceProvider.advances().put(unicode, advance);
        return Text.literal(unicode).styled(style -> style.withFont(identifier));
    }
}
