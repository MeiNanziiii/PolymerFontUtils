package ua.mei.pfu.api.font.provider;

import java.util.List;

public record BitmapFontProvider(String file, int height, int ascent, List<String> chars) implements FontProvider {
    public static final String type = "bitmap";

    @Override
    public String getType() {
        return type;
    }
}
