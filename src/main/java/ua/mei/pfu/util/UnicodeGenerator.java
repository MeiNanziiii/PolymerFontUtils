package ua.mei.pfu.util;

public class UnicodeGenerator {
    private int latestUnicodePoint = 0x4E00;

    public String requestUnicode() {
        if (latestUnicodePoint > 0x9FFF) {
            throw new IllegalStateException("No more Unicode characters available.");
        }
        return new String(Character.toChars(latestUnicodePoint++));
    }
}
