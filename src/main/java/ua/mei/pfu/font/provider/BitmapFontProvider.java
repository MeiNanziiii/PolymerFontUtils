package ua.mei.pfu.font.provider;

import com.google.gson.annotations.Expose;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ua.mei.pfu.PolymerFontUtils;

import java.util.List;

public record BitmapFontProvider(String file, int height, int ascent, List<String> chars, @Expose(serialize = false) Identifier font) implements FontProvider {
    public static final String type = "bitmap";

    public MutableText getText() {
        return Text.literal(chars.getFirst()).styled(style -> style.withFont(font));
    }

    public MutableText withSpace(int advance) {
        MutableText space = PolymerFontUtils.spaceMap.getOrDefault(advance, Text.empty());
        return Text.empty().append(space).append(getText());
    }

    public MutableText withDoubleSpace(int advance) {
        MutableText space = PolymerFontUtils.spaceMap.getOrDefault(advance, Text.empty());
        return Text.empty().append(space).append(getText()).append(space);
    }

    public MutableText withOffset(int advance) {
        MutableText space = PolymerFontUtils.spaceMap.getOrDefault(advance, Text.empty());
        MutableText offset = PolymerFontUtils.spaceMap.getOrDefault(-advance, Text.empty());
        return Text.empty().append(space).append(getText()).append(offset);
    }

    @Override
    public String getType() {
        return type;
    }
}
