package ua.mei.pfu.api.font;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.api.font.provider.BitmapFontProvider;
import ua.mei.pfu.impl.PolymerFontUtilsImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused"})
public class BitmapGlyph {
    public final FontResourceManager manager;
    public final BitmapFontProvider provider;

    public final BufferedImage image;

    public final int imageWidth;
    public final int imageHeight;

    public final int glyphWidth;
    public final int glyphHeight;

    public final String symbol;

    public MutableText value;

    private BitmapGlyph(FontResourceManager manager, BitmapFontProvider provider, BufferedImage image, int glyphWidth, int glyphHeight) {
        this.manager = manager;
        this.provider = provider;

        this.image = image;

        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();

        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;

        this.symbol = provider.chars().getFirst();

        this.value = Text.literal(provider.chars().getFirst()).styled(style -> style.withFont(manager.identifier));
    }

    protected static BitmapGlyph create(FontResourceManager manager, String path, int height, int ascent) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

        try {
            Optional<ModContainer> container = FabricLoader.getInstance().getModContainer(manager.modId);

            if (container.isPresent()) {
                Optional<Path> imagePath = container.get().findPath("assets/" + manager.namespace + "/textures/" + path);

                if (imagePath.isPresent()) {
                    image = ImageIO.read(imagePath.get().toFile());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int bitmapHeight = height == -1 ? image.getHeight() : height;

        String file = manager.namespace + ":" + path;
        String unicode = manager.generator.requestUnicode();

        BitmapFontProvider provider = new BitmapFontProvider(file, bitmapHeight, ascent, List.of(unicode));

        int[] glyphSize = getGlyphSize(image);

        return new BitmapGlyph(manager, provider, image, glyphSize[0], glyphSize[1]);
    }

    public static int[] getGlyphSize(BufferedImage image) {
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

    public MutableText spaceBefore(int space, FontResourceManager manager) {
        return Text.empty()
                .append(manager.requestSpace(space))
                .append(this.value);
    }

    public MutableText spaceBefore(int space) {
        return spaceBefore(space, PolymerFontUtilsImpl.spaceManager);
    }

    public MutableText spaceAfter(int space, FontResourceManager manager) {
        return Text.empty()
                .append(this.value)
                .append(manager.requestSpace(space));
    }

    public MutableText spaceAfter(int space) {
        return spaceAfter(space, PolymerFontUtilsImpl.spaceManager);
    }

    public MutableText space(int before, int after, FontResourceManager manager) {
        return Text.empty()
                .append(manager.requestSpace(before))
                .append(this.value)
                .append(manager.requestSpace(after));
    }

    public MutableText space(int before, int after) {
        return space(before, after, PolymerFontUtilsImpl.spaceManager);
    }

    public MutableText space(int space, FontResourceManager manager) {
        return space(space, space, manager);
    }

    public MutableText space(int space) {
        return space(space, space);
    }

    public MutableText offset(int offset, FontResourceManager manager) {
        return space(offset, -offset, manager);
    }

    public MutableText offset(int offset) {
        return space(offset, -offset, PolymerFontUtilsImpl.spaceManager);
    }
}
