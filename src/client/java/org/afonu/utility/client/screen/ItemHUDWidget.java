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

public class ItemHUDWidget implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        if (OptionInit.Option.toggleItemHUD) {
            MinecraftClient client = MinecraftClient.getInstance();
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            TextRenderer renderer = client.textRenderer;

            assert player != null;
            ItemStack item = player.getMainHandStack();

            if (item.getMaxDamage() != 0) {
                drawContext.fill(OptionInit.Option.itemHUD_positionX, 0, OptionInit.Option.itemHUD_positionX + 40, 16, 0xFF0000FF);
                drawContext.drawText(renderer, String.valueOf(item.getMaxDamage() - item.getDamage()), OptionInit.Option.itemHUD_positionX + 16, OptionInit.Option.itemHUD_positionY + 4, item.getItemBarColor(), true);
                drawContext.drawItem(item, OptionInit.Option.itemHUD_positionX, OptionInit.Option.itemHUD_positionY);
            }
        }
    }
}
