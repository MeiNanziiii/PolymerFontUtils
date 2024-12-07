package ua.mei.pfu.api.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.api.FontResource;
import ua.mei.pfu.api.provider.SpaceFontProvider;
import ua.mei.pfu.impl.PolymerFontUtilsImpl;

import java.util.HashMap;
import java.util.Map;

public class FontSpaceUtils {
    public static final FontResource spaceResource = PolymerFontUtilsImpl.manager.requestFont("spaces");
    public static final SpaceFontProvider spaceProvider = new SpaceFontProvider(new HashMap<>());
    public static final Map<Integer, MutableText> spaceMap = new HashMap<>(Map.of(0, Text.empty()));

    public static void requestAdvance(int advance) {
        if (advance == 0 || spaceMap.containsKey(advance)) return;

        if (spaceProvider.advances().isEmpty()) {
            spaceResource.providers.add(spaceProvider);
        }

        String symbol = spaceResource.nextSymbol();

        spaceProvider.advances().put(symbol, advance);
        spaceMap.put(advance, Text.literal(symbol).styled(style -> style.withFont(spaceResource.identifier)));
    }

    public static void requestAdvances(Integer... advances) {
        for (Integer advance : advances) {
            requestAdvance(advance);
        }
    }

    public static void requestRange(int from, int to) {
        if (from > to) return;

        for (int i = from; i <= to; i++) {
            requestAdvance(i);
        }
    }
}
