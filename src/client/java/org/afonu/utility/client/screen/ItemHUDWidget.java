package org.afonu.utility.client.screen;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.afonu.utility.client.option.OptionInit;

import java.awt.*;

//TODO Add OffHand Damage Indicator

public class ItemHUDWidget implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        if (OptionInit.Option.toggleItemHUD) {
            MinecraftClient client = MinecraftClient.getInstance();
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            TextRenderer renderer = client.textRenderer;

            boolean zeroX = OptionInit.Option.toggleOnlyItem ? OptionInit.Option.itemHUD_positionX < 16 : OptionInit.Option.itemHUD_positionX < 27;
            boolean zeroY = OptionInit.Option.itemHUD_positionY <= 27;

            int width = zeroX ? (OptionInit.Option.toggleOnlyItem ? 0 : -16) : (int) (((double) OptionInit.Option.itemHUD_positionX / 2000) * (client.getWindow().getWidth())) - (OptionInit.Option.textPadding + 24);
            int height = zeroY ? 0 : (int) ((double) OptionInit.Option.itemHUD_positionY / 2000 * (client.getWindow().getHeight())) - 16;

            assert player != null;
            ItemStack item = player.getMainHandStack();

            if (item.getMaxDamage() != 0) {
                //drawContext.fill(OptionInit.Option.itemHUD_positionX, 0, OptionInit.Option.itemHUD_positionX + 40, 16, 0xFF0000FF);
                if (OptionInit.Option.toggleOnlyItem) {
                    drawContext.drawItem(item, width, height);
                }
                if (OptionInit.Option.toggleOnlyDamageItem) {
                    drawContext.drawText(renderer, String.valueOf(item.getMaxDamage() - item.getDamage()), width + OptionInit.Option.textPadding, height + 4, item.getItemBarColor(), true);
                }
            }
        }
    }
}
