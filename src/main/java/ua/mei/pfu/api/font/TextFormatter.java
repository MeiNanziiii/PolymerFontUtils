package ua.mei.pfu.api.font;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.impl.PolymerFontUtilsImpl;

@SuppressWarnings({"unused"})
public class TextFormatter {
    public MutableText value;

    public TextFormatter(MutableText value) {
        this.value = value;
    }

    public TextFormatter spaceBefore(int space, FontResourceManager manager) {
        value = Text.empty()
                .append(manager.requestSpace(space))
                .append(this.value);
        return this;
    }

    public TextFormatter spaceBefore(int space) {
        return spaceBefore(space, PolymerFontUtilsImpl.spaceManager);
    }

    public TextFormatter spaceAfter(int space, FontResourceManager manager) {
        this.value = Text.empty()
                .append(this.value)
                .append(manager.requestSpace(space));

        return this;
    }

    public TextFormatter spaceAfter(int space) {
        return spaceAfter(space, PolymerFontUtilsImpl.spaceManager);
    }

    public TextFormatter space(int before, int after, FontResourceManager manager) {
        this.value = Text.empty()
                .append(manager.requestSpace(before))
                .append(this.value)
                .append(manager.requestSpace(after));

        return this;
    }

    public TextFormatter space(int before, int after) {
        return space(before, after, PolymerFontUtilsImpl.spaceManager);
    }

    public TextFormatter space(int space, FontResourceManager manager) {
        return space(space, space, manager);
    }

    public TextFormatter space(int space) {
        return space(space, space);
    }

    public TextFormatter offset(int offset, FontResourceManager manager) {
        return space(offset, -offset, manager);
    }

    public TextFormatter offset(int offset) {
        return space(offset, -offset, PolymerFontUtilsImpl.spaceManager);
    }
}
