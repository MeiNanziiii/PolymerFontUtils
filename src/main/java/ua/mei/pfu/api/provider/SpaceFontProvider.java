package ua.mei.pfu.api.provider;

import com.google.gson.annotations.Expose;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.api.FontResource;

import java.util.Map;

public record SpaceFontProvider(@Expose String type, @Expose Map<String, Integer> advances,
                                FontResource font) implements BaseFontProvider {
    public SpaceFontProvider(Map<String, Integer> advances, FontResource font) {
        this("space", advances, font);
    }

    @Override
    public MutableText asText() {
        return Text.empty();
    }
}
