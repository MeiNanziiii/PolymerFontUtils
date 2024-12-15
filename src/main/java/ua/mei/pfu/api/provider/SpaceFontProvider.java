package ua.mei.pfu.api.provider;

import com.google.gson.annotations.Expose;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.Map;

public record SpaceFontProvider(@Expose String type,
                                @Expose Map<String, Integer> advances) implements BaseFontProvider {
    public SpaceFontProvider(Map<String, Integer> advances) {
        this("space", advances);
    }

    @Override
    public MutableText asText() {
        return Text.empty();
    }
}
