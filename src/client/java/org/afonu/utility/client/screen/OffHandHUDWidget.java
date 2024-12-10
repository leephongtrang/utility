package org.afonu.utility.client.screen;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import org.afonu.utility.client.option.OptionInit;

public class OffHandHUDWidget implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        if (OptionInit.Option.toggleOffHandHUD) {
            MinecraftClient client = MinecraftClient.getInstance();
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            TextRenderer renderer = client.textRenderer;

            boolean zeroX = OptionInit.Option.toggleOnlyOffHand ? OptionInit.Option.offHandHUD_positionX < 16 : OptionInit.Option.offHandHUD_positionX < 27;
            boolean zeroY = OptionInit.Option.offHandHUD_positionY <= 27;

            int width = zeroX ? (OptionInit.Option.toggleOnlyOffHand ? 0 : -16) : (int) (((double) OptionInit.Option.offHandHUD_positionX / 2000) * (client.getWindow().getWidth())) - (OptionInit.Option.textPadding + 24);
            int height = zeroY ? 0 : (int) ((double) OptionInit.Option.offHandHUD_positionY / 2000 * (client.getWindow().getHeight())) - 16;

            assert player != null;
            ItemStack item = player.getOffHandStack();

            if (item.getMaxDamage() != 0) {
                //drawContext.fill(OptionInit.Option.itemHUD_positionX, 0, OptionInit.Option.itemHUD_positionX + 40, 16, 0xFF0000FF);
                if (OptionInit.Option.toggleOnlyOffHand) {
                    drawContext.drawItem(item, width, height);
                }
                if (OptionInit.Option.toggleOnlyDamageOffHand) {
                    drawContext.drawText(renderer, String.valueOf(item.getMaxDamage() - item.getDamage()), width + OptionInit.Option.textPadding, height + 4, item.getItemBarColor(), true);
                }
            }
        }
    }
}
