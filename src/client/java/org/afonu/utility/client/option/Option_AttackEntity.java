package org.afonu.utility.client.option;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class Option_AttackEntity {
    public static void AttackEntity(boolean value) {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) ->
        {
            if (!value) {
                if (player.getMainHandStack().isDamaged()) {
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
