package org.afonu.utility.client.option;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class Option_AttackBlock {
    public static void AttackBlock() {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) ->
        {
            if (!OptionInit.Option.allowAttackBlock) {
                if (player.getMainHandStack().isDamaged()) {
                    if (player.getMainHandStack().getDamage() >= player.getMainHandStack().getMaxDamage() - 10) {
                        world.playSound(player, player.getBlockPos(), SoundEvents.ENTITY_PARROT_IMITATE_WITHER_SKELETON, SoundCategory.BLOCKS, 1f, 1f);
                        return ActionResult.FAIL;
                    }
                }
            }
            return ActionResult.PASS;
        });
    }
}
