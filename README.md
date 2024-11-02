# Polymer Font Utils

API for generating font files. Uses polymer resource pack api.

# Example

```groovy
repositories {
    maven { url "https://api.modrinth.com/maven" }
}

dependencies {
    modImplementation include("maven.modrinth:pfu:0.1.3+1.21.2")
}
```

```java
public class PolymerFontUtilsTest implements ModInitializer {
    public static FontResourceManager manager;
    public static BitmapGlyph glyph;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfut");

        manager = FontResourceManager.create("pfut", "test");
        glyph = manager.requestGlyph("font/icon.png", 128, 64);
    }
}
```

You can get list of all bitmaps from manager using command `/pfu pfut:test`

![Example](https://cdn.modrinth.com/data/cached_images/7b5072dcfef25fed385a3415f413730532a2ace4.png)
