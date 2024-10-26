package ua.mei.pfu.ui;

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
import net.minecraft.util.collection.DefaultedList;
import ua.mei.pfu.font.FontResourceManager;
import ua.mei.pfu.font.provider.BitmapFontProvider;
import ua.mei.pfu.font.provider.FontProvider;

import java.util.List;

public class ManagerUI extends MicroUi {
    private static final int ITEMS_PER_PAGE = 45;

    private final List<BitmapFontProvider> items;
    private int page;

    public ManagerUI(FontResourceManager manager) {
        super(6);

        this.title(Text.literal(manager.identifier.toString()));

        this.items = DefaultedList.of();

        for (FontProvider provider : manager.providers) {
            if (provider instanceof BitmapFontProvider bitmap) {
                items.add(bitmap);
            }
        }
        this.page = 0;

        this.drawUi();
    }

    private void drawUi() {
        int start = page * ITEMS_PER_PAGE;
        int end = Math.min((page + 1) * ITEMS_PER_PAGE, this.items.size());

        for (int i = start; i < end; i++) {
            BitmapFontProvider bitmap = this.items.get(i);
            ItemStack stack = new ItemStack(Items.PAPER);

            stack.set(DataComponentTypes.ITEM_NAME, bitmap.getText());
            stack.set(DataComponentTypes.TOOLTIP_STYLE, Identifier.of("pfu:tooltip"));

            this.slot(i - start, stack, (player, slotIndex, button, actionType) -> {
                Text copyText = Text.literal("[Copy Symbol]").formatted(Formatting.GREEN).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, bitmap.chars().getFirst())));
                Text copySimplifiedFormat = Text.literal("[Simplified Text Format]").formatted(Formatting.GREEN).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, String.format("<font:'%s'>%s</font>", bitmap.font().toString(), bitmap.chars().getFirst()))));
                Text copyQuickFormat = Text.literal("[Quick Text Format]").formatted(Formatting.GREEN).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, String.format("<font '%s'>%s</font>", bitmap.font().toString(), bitmap.chars().getFirst()))));
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
            if (this.page >= this.items.size() / ITEMS_PER_PAGE) {
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
