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
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        TextRenderer renderer = client.textRenderer;

        ItemStack item = player.getMainHandStack();

        if (item.getMaxDamage() != 0) {
            drawContext.drawItem(item, OptionInit.Option.itemHUD_positionX, OptionInit.Option.armorHUD_positionY);
            //drawContext.drawText(renderer, String.valueOf(item.getMaxDamage() - item.getDamage()), OptionInit.Option.itemHUD_positionX-74, OptionInit.Option.itemHUD_positionY-(OptionInit.Option.itemHUD_positionY/2)-(-35)-6, Color.WHITE.getRGB(), true);
        }
    }
}
