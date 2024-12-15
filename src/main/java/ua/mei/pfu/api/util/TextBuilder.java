package ua.mei.pfu.api.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.api.BitmapGlyph;
import ua.mei.pfu.api.provider.BaseFontProvider;

import java.util.ArrayList;
import java.util.List;

public class TextBuilder {
    public final List<Text> texts = new ArrayList<>();

    public TextBuilder text(Text text) {
        this.texts.add(text);
        return this;
    }

    public TextBuilder space(int advance) {
        if (advance != 0) this.texts.add(FontSpaceUtils.spaceMap.get(advance));
        return this;
    }

    public TextBuilder glyph(BitmapGlyph glyph) {
        this.texts.add(glyph.value);
        return this;
    }

    public TextBuilder provider(BaseFontProvider provider) {
        this.texts.add(provider.asText());
        return this;
    }

    public MutableText build() {
        MutableText text = Text.empty();

        texts.forEach(text::append);

        return text;
    }
}
