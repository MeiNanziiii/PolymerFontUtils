# Polymer Font Utils

API for generating font files. Uses polymer resource pack api.

# Example

```java
public class PolymerFontUtilsTest implements ModInitializer {
    public static FontResourceManager manager;
    public static BitmapFontProvider bitmap;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfut");

        manager = FontResourceManager.create("pfut", "test");
        bitmap = manager.requestBitmap("font/icon.png", 128, 64);
    }
}
```

You can get list of all bitmap's from manager using command `/pfu pfut:test`

![Example](https://cdn.modrinth.com/data/cached_images/7b5072dcfef25fed385a3415f413730532a2ace4.png)
