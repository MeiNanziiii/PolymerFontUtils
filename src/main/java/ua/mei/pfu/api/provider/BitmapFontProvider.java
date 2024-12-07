package ua.mei.pfu.api.provider;

import com.google.gson.annotations.Expose;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ua.mei.pfu.api.FontResource;

import java.util.List;

public record BitmapFontProvider(@Expose String type, @Expose Identifier file, @Expose int height, @Expose int ascent,
                                 @Expose List<String> chars, FontResource font) implements BaseFontProvider {
    public BitmapFontProvider(Identifier file, int height, int ascent, List<String> chars, FontResource font) {
        this("bitmap", file, height, ascent, chars, font);
    }

    @Override
    public MutableText asText() {
        return Text.literal(this.chars.getFirst()).styled(style -> style.withFont(this.font.identifier));
    }
}
