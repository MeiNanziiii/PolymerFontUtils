package ua.mei.pfu.font.provider;

import java.util.Map;

public record SpaceFontProvider(Map<String, Integer> advances) implements FontProvider {
    public static final String type = "space";

    @Override
    public String getType() {
        return type;
    }
}
