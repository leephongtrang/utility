package org.afonu.utility.client.screen;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;

import static org.afonu.utility.client.option.OptionInit.Option;

public class ArmorHUDWidget implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        if (Option.toggleArmorHUD) {
            MinecraftClient client = MinecraftClient.getInstance();

            assert MinecraftClient.getInstance() != null;
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            assert player != null;
            Iterable<ItemStack> armor = player.getArmorItems();
            TextRenderer renderer = client.textRenderer;


            boolean zeroX = Option.toggleOnlyArmor ? Option.armorHUD_positionX < 43 : Option.armorHUD_positionX < 27;
            boolean zeroY = Option.armorHUD_positionY < 152;

            int width = zeroX ? (Option.toggleOnlyArmor ? 0 : -16) : (int) (((double) Option.armorHUD_positionX / 2000) * (client.getWindow().getWidth())) - (Option.textPadding + 24);
            int height = zeroY ? 60 : (int) ((double) Option.armorHUD_positionY / 2000 * (client.getWindow().getHeight())) - 16;
            int relativePosition = 0;

            for (ItemStack itemStack : armor) {
                if (itemStack.getMaxDamage() != 0) {
                    if (Option.toggleOnlyArmor) drawContext.drawItem(itemStack, width, height + relativePosition);
                    if (Option.toggleOnlyArmorDamage) drawContext.drawText(renderer, String.valueOf(itemStack.getMaxDamage() - itemStack.getDamage()), width + Option.textPadding, height + relativePosition + 4, itemStack.getItemBarColor(), true);
                    relativePosition -= 20;
                }
            }
        }
    }
}
