package com.ninni.multiverse.client.gui;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ninni.multiverse.Multiverse;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WrittenBookItem;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Environment(EnvType.CLIENT)
public class LoreTabletScreen extends Screen {
    public static final ResourceLocation BOOK_LOCATION = new ResourceLocation(Multiverse.MOD_ID, "textures/gui/lore_tablet.png");
    private final BookViewScreen.BookAccess bookAccess;
    private int currentPage;
    private List<FormattedCharSequence> cachedPageComponents = Collections.emptyList();
    private int cachedPage = -1;
    private PageButton forwardButton;
    private PageButton backButton;
    private final boolean playTurnSound;

    private LoreTabletScreen(BookViewScreen.BookAccess bookAccess, boolean bl) {
        super(GameNarrator.NO_TITLE);
        this.bookAccess = bookAccess;
        this.playTurnSound = bl;
    }

    public LoreTabletScreen(BookViewScreen.BookAccess bookAccess) {
        this(bookAccess, true);
    }

    @Override
    protected void init() {
        this.createMenuControls();
        this.createPageControlButtons();
    }

    protected void createMenuControls() {
        this.addRenderableWidget(new Button(this.width / 2 - 100, 196, 200, 20, CommonComponents.GUI_DONE, button -> this.minecraft.setScreen(null)));
    }

    protected void createPageControlButtons() {
        int i = (this.width - 192) / 2;
        this.forwardButton = this.addRenderableWidget(new PageButton(i + 116, 159, true, button -> this.pageForward(), this.playTurnSound));
        this.backButton = this.addRenderableWidget(new PageButton(i + 43, 159, false, button -> this.pageBack(), this.playTurnSound));
        this.updateButtonVisibility();
    }

    protected void pageBack() {
        if (this.currentPage > 0) {
            --this.currentPage;
        }
        this.updateButtonVisibility();
    }

    protected void pageForward() {
        if (this.currentPage < this.getNumPages() - 1) {
            ++this.currentPage;
        }
        this.updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        this.forwardButton.visible = this.currentPage < this.getNumPages() - 1;
        this.backButton.visible = this.currentPage > 0;
    }

    @Override
    public void render(PoseStack poseStack, int i, int j, float f) {
        this.renderBackground(poseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BOOK_LOCATION);
        int k = (this.width - 192) / 2;
        this.blit(poseStack, k, 2, 0, 0, 192, 192);
        if (this.cachedPage != this.currentPage) {
            FormattedText formattedText = this.bookAccess.getPage(this.currentPage);
            this.cachedPageComponents = this.font.split(formattedText, 114);
        }
        this.cachedPage = this.currentPage;
        int n = Math.min(128 / this.font.lineHeight, this.cachedPageComponents.size());
        for (int o = 0; o < n; ++o) {
            FormattedCharSequence formattedCharSequence = this.cachedPageComponents.get(o);
            this.font.draw(poseStack, formattedCharSequence, (float)(k + 36), (float)(32 + o * this.font.lineHeight), 0);
        }
        Style style = this.getClickedComponentStyleAt(i, j);
        if (style != null) {
            this.renderComponentHoverEffect(poseStack, style, i, j);
        }
        super.render(poseStack, i, j, f);
    }

    private int getNumPages() {
        return this.bookAccess.getPageCount();
    }

    @Nullable
    public Style getClickedComponentStyleAt(double d, double e) {
        if (this.cachedPageComponents.isEmpty()) {
            return null;
        }
        int i = Mth.floor(d - (double)((this.width - 192) / 2) - 36.0);
        int j = Mth.floor(e - 2.0 - 30.0);
        if (i < 0 || j < 0) {
            return null;
        }
        int k = Math.min(128 / this.font.lineHeight, this.cachedPageComponents.size());
        if (i <= 114 && j < this.minecraft.font.lineHeight * k + k) {
            int l = j / this.minecraft.font.lineHeight;
            if (l < this.cachedPageComponents.size()) {
                FormattedCharSequence formattedCharSequence = this.cachedPageComponents.get(l);
                return this.minecraft.font.getSplitter().componentStyleAtWidth(formattedCharSequence, i);
            }
            return null;
        }
        return null;
    }

    static List<String> loadPages(CompoundTag compoundTag) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        BookViewScreen.loadPages(compoundTag, builder::add);
        return builder.build();
    }

    @Environment(EnvType.CLIENT)
    public static class LoreInfoAccess implements BookViewScreen.BookAccess {
        private final List<String> pages;

        public LoreInfoAccess(ItemStack itemStack) {
            this.pages = LoreTabletScreen.LoreInfoAccess.readPages(itemStack);
        }

        private static List<String> readPages(ItemStack itemStack) {
            CompoundTag compoundTag = itemStack.getTag();
            if (WrittenBookItem.makeSureTagIsValid(compoundTag)) {
                return LoreTabletScreen.loadPages(compoundTag);
            }
            return ImmutableList.of(Component.Serializer.toJson(Component.translatable("item.lore_tablet.message.cobblestone_golem").withStyle(ChatFormatting.BLACK)));
        }

        @Override
        public int getPageCount() {
            return this.pages.size();
        }

        @Override
        public FormattedText getPageRaw(int i) {
            String string = this.pages.get(i);
            try {
                MutableComponent formattedText = Component.Serializer.fromJson(string);
                if (formattedText != null) {
                    return formattedText;
                }
            }
            catch (Exception ignored) {
            }
            return FormattedText.of(string);
        }
    }

}
