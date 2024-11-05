package ua.mei.pfu.api.font;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.impl.PolymerFontUtilsImpl;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused"})
public class TextBuilder {
    public final List<Text> texts = new ArrayList<>();

    public TextBuilder text(MutableText text) {
        this.texts.add(text);
        return this;
    }

    public TextBuilder space(int advance) {
        if (advance != 0) this.texts.add(PolymerFontUtilsImpl.spaceMap.get(advance));
        return this;
    }

    public TextBuilder glyph(BitmapGlyph glyph) {
        this.texts.add(glyph.value);
        return this;
    }

    public MutableText build() {
        MutableText text = Text.empty();

        texts.forEach(text::append);

        return text;
    }
}
