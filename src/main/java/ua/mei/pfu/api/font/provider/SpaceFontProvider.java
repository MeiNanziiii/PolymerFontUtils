package ua.mei.pfu.api.font.provider;

import java.util.HashMap;

public record SpaceFontProvider(HashMap<String, Integer> advances) implements FontProvider {
    public static final String type = "space";

    @Override
    public String getType() {
        return type;
    }
}
