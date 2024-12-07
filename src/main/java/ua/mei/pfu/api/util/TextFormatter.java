package ua.mei.pfu.api.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

@SuppressWarnings({"unused"})
public class TextFormatter {
    public MutableText value;

    public TextFormatter(MutableText value) {
        this.value = value;
    }

    public TextFormatter spaceBefore(int space) {
        if (space != 0) {
            value = Text.empty()
                    .append(FontSpaceUtils.spaceMap.get(space))
                    .append(this.value);
        }
        return this;
    }

    public TextFormatter spaceAfter(int space) {
        if (space != 0) {
            this.value = Text.empty()
                    .append(this.value)
                    .append(FontSpaceUtils.spaceMap.get(space));
        }
        return this;
    }

    public TextFormatter space(int before, int after) {
        if (before != 0 && after != 0) {
            this.value = Text.empty()
                    .append(FontSpaceUtils.spaceMap.get(before))
                    .append(this.value)
                    .append(FontSpaceUtils.spaceMap.get(after));
        }
        return this;
    }

    public TextFormatter space(int space) {
        return space(space, space);
    }

    public TextFormatter offset(int offset) {
        return space(offset, -offset);
    }
}
