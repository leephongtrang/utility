package org.afonu.utility.client.option;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class Option_AttackBlock {
    public static void AttackBlock(boolean value) {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) ->
        {
            if (player.getMainHandStack().isDamaged()) {
                if (!value) {
                    if (player.getMainHandStack().getDamage() >= player.getMainHandStack().getMaxDamage() - 10) {
                        world.playSound(player, player.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.BLOCKS, 1f, 1f);
                        return ActionResult.FAIL;
                    }
                }
            }
            return ActionResult.PASS;
        });
    }
}
