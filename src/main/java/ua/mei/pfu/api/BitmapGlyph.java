package ua.mei.pfu.api;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.MutableText;
import ua.mei.pfu.api.provider.BitmapFontProvider;
import ua.mei.pfu.api.util.TextFormatter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Path;

public class BitmapGlyph {
    public final FontResource resource;
    public final FontResourceManager manager;
    public final BitmapFontProvider provider;

    public final BufferedImage image;

    public final int glyphWidth;
    public final int glyphHeight;

    public final String symbol;
    public final MutableText value;

    protected BitmapGlyph(FontResource resource, BitmapFontProvider provider) {
        this.resource = resource;
        this.manager = resource.manager;
        this.provider = provider;

        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

        ModContainer container = FabricLoader.getInstance().getModContainer(this.manager.modId).orElse(null);

        if (container != null) {
            Path path = container.findPath("assets/" + provider.file().getNamespace() + "/textures/" + provider.file().getPath()).orElse(null);

            if (path != null) {
                try (InputStream stream = path.getFileSystem().provider().newInputStream(path)) {
                    image = ImageIO.read(stream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        this.image = image;

        int[] size = getGlyphSize(image);

        this.glyphWidth = size[0];
        this.glyphHeight = size[1];

        this.symbol = provider.chars().getFirst();
        this.value = provider.asText();
    }

    private static int[] getGlyphSize(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int lastX = -1;
        int lastY = -1;

        for (int y = 0; y < height; y++) {
            for (int x = width - 1; x >= 0; x--) {
                if ((image.getRGB(x, y) >>> 24) != 0) {
                    lastX = Math.max(lastX, x);
                    lastY = y;
                    break;
                }
            }
        }

        return lastX == -1
                ? new int[]{width, height}
                : new int[]{lastX + 1, lastY + 1};
    }

    public TextFormatter formatter() {
        return new TextFormatter(this.value);
    }
}
