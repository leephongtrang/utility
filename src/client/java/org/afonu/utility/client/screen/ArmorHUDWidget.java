package org.afonu.utility.client.screen;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;

import java.awt.*;

import static org.afonu.utility.client.option.OptionInit.Option;

public class ArmorHUDWidget implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        assert MinecraftClient.getInstance() != null;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        assert player != null;
        Iterable<ItemStack> armor = player.getArmorItems();
        TextRenderer renderer = client.textRenderer;

        int relativePosition = 0;

        for (ItemStack itemStack : armor) {
            if (itemStack.getMaxDamage() != 0) {
                drawContext.drawItem(itemStack, Option.armorHUD_positionX, Option.armorHUD_positionY + relativePosition);
                drawContext.drawText(renderer, String.valueOf(itemStack.getMaxDamage() - itemStack.getDamage()), Option.armorHUD_positionX + 20, Option.armorHUD_positionY + relativePosition + 4, itemStack.getItemBarColor(), true);
                relativePosition -= 20;
            }
        }
    }
}
