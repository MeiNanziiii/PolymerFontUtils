package ua.mei.pfu.api;

import net.minecraft.util.Identifier;
import ua.mei.pfu.api.provider.BaseFontProvider;
import ua.mei.pfu.api.provider.BitmapFontProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FontResource {
    public static final Set<FontResource> resources = new HashSet<>();
    public final Identifier identifier;
    public final FontResourceManager manager;
    public final Set<BaseFontProvider> providers = new HashSet<>();
    public final Set<BitmapGlyph> glyphs = new HashSet<>();
    private int latestUnicodePoint = 0x4E00;

    protected FontResource(Identifier identifier, FontResourceManager manager) {
        this.identifier = identifier;
        this.manager = manager;

        resources.add(this);
    }

    public BitmapFontProvider requestBitmap(String path, int height, int ascent) {
        return (BitmapFontProvider) providers.stream()
                .filter(provider -> provider instanceof BitmapFontProvider bitmap && bitmap.file().getPath().equals(path) && bitmap.height() == height && bitmap.ascent() == ascent)
                .findFirst()
                .orElseGet(() -> {
                    BitmapFontProvider provider = new BitmapFontProvider(Identifier.of(this.identifier.getNamespace(), path), height, ascent, List.of(nextSymbol()), this);
                    providers.add(provider);
                    return provider;
                });
    }

    public BitmapGlyph requestGlyph(String path, int height, int ascent) {
        return glyphs.stream()
                .filter(glyph -> glyph.provider.file().getPath().equals(path) && glyph.provider.height() == height && glyph.provider.ascent() == ascent)
                .findFirst()
                .orElseGet(() -> {
                    BitmapGlyph glyph = new BitmapGlyph(this, requestBitmap(path, height, ascent));
                    glyphs.add(glyph);
                    return glyph;
                });
    }

    public String nextSymbol() {
        if (latestUnicodePoint > 0x9FFF) {
            throw new IllegalStateException("No more Unicode characters available.");
        }
        return new String(Character.toChars(latestUnicodePoint++));
    }
}
