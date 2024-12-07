package ua.mei.pfu.impl.ui;

import eu.pb4.polymer.core.impl.ui.MicroUi;
import eu.pb4.polymer.core.impl.ui.MicroUiElements;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.pfu.api.FontResource;
import ua.mei.pfu.api.provider.BitmapFontProvider;

import java.util.List;

@ApiStatus.Internal
public class ManagerUI extends MicroUi {
    private static final int ITEMS_PER_PAGE = 45;

    private final FontResource resource;
    private final List<BitmapFontProvider> bitmaps;

    private int page;

    public ManagerUI(FontResource resource) {
        super(6);

        this.title(Text.literal(resource.identifier.toString()));

        this.resource = resource;
        this.bitmaps = resource.providers.stream()
                .filter(provider -> provider instanceof BitmapFontProvider)
                .map(provider -> (BitmapFontProvider) provider)
                .toList();

        this.page = 0;

        this.drawUi();
    }

    private void drawUi() {
        int start = page * ITEMS_PER_PAGE;
        int end = Math.min((page + 1) * ITEMS_PER_PAGE, this.bitmaps.size());

        for (int i = start; i < end; i++) {
            BitmapFontProvider bitmap = bitmaps.get(i);
            String symbol = bitmap.chars().getFirst();

            ItemStack stack = new ItemStack(Items.PAPER);

            stack.set(DataComponentTypes.ITEM_NAME, bitmap.asText().styled(style -> style.withShadowColor(0)));
            stack.set(DataComponentTypes.TOOLTIP_STYLE, Identifier.of("pfu:tooltip"));

            this.slot(i - start, stack, (player, slotIndex, button, actionType) -> {
                Text copyText = Text.literal("[Copy Symbol]").formatted(Formatting.GREEN).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, symbol)));
                Text copySimplifiedFormat = Text.literal("[Simplified Text Format]").formatted(Formatting.GREEN).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, String.format("<font:'%s'>%s</font>", resource.identifier.toString(), symbol))));
                Text copyQuickFormat = Text.literal("[Quick Text Format]").formatted(Formatting.GREEN).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, String.format("<font '%s'>%s</font>", resource.identifier.toString(), symbol))));
                player.sendMessage(Text.empty().append(copyText).append("\n").append(copySimplifiedFormat).append("\n").append(copyQuickFormat));
                player.closeHandledScreen();
            });

            this.slot(ITEMS_PER_PAGE, MicroUiElements.EMPTY, MicroUiElements.EMPTY_ACTION);
            if (this.page == 0) {
                this.slot(ITEMS_PER_PAGE + 1, MicroUiElements.BUTTON_PREVIOUS_LOCK, MicroUiElements.EMPTY_ACTION);
            } else {
                this.slot(ITEMS_PER_PAGE + 1, MicroUiElements.BUTTON_PREVIOUS, (player, slotIndex, button, actionType) -> {
                    ManagerUI.this.page--;
                    playSound(player, SoundEvents.UI_BUTTON_CLICK);
                    this.drawUi();
                });
            }
            this.slot(ITEMS_PER_PAGE + 2, MicroUiElements.EMPTY, MicroUiElements.EMPTY_ACTION);
            this.slot(ITEMS_PER_PAGE + 3, MicroUiElements.EMPTY, MicroUiElements.EMPTY_ACTION);
            this.slot(ITEMS_PER_PAGE + 4, MicroUiElements.EMPTY, MicroUiElements.EMPTY_ACTION);
            this.slot(ITEMS_PER_PAGE + 5, MicroUiElements.EMPTY, MicroUiElements.EMPTY_ACTION);
            this.slot(ITEMS_PER_PAGE + 6, MicroUiElements.EMPTY, MicroUiElements.EMPTY_ACTION);
            if (this.page >= this.bitmaps.size() / ITEMS_PER_PAGE) {
                this.slot(ITEMS_PER_PAGE + 7, MicroUiElements.BUTTON_NEXT_LOCK, MicroUiElements.EMPTY_ACTION);
            } else {
                this.slot(ITEMS_PER_PAGE + 7, MicroUiElements.BUTTON_NEXT, (player, slotIndex, button, actionType) -> {
                    ManagerUI.this.page++;
                    playSound(player, SoundEvents.UI_BUTTON_CLICK);
                    this.drawUi();
                });
            }
            this.slot(ITEMS_PER_PAGE + 8, MicroUiElements.EMPTY, MicroUiElements.EMPTY_ACTION);
        }
    }
}
