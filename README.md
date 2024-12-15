# Polymer Font Utils

API for generating font files. Uses polymer resource pack api.

# Example

```groovy
repositories {
    maven { url "https://api.modrinth.com/maven" }
}

dependencies {
    modImplementation include("maven.modrinth:pfu:0.2.3+1.21.4")
}
```

```java
public class PolymerFontUtilsTest implements ModInitializer {
    public static FontResourceManager manager;
    public static FontResource resource;
    public static TextResource text;
    public static BitmapGlyph glyph;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("pfut");

        manager = FontResourceManager.create("pfut");
        resource = manager.requestFont("test");
        text = manager.requestText(-20); // Font identifier: pfut:text/default_-20
        glyph = resource.requestGlyph("font/icon.png", 128, 32);

        FontSpaceUtils.requestAdvance(-256);
        FontSpaceUtils.requestAdvances(-128, 1, 3);
        FontSpaceUtils.requestRange(2, 19);
    }
}
```

You can get list of all bitmaps from manager using command `/pfu pfut:test`

![Example](https://cdn.modrinth.com/data/cached_images/7b5072dcfef25fed385a3415f413730532a2ace4.png)
