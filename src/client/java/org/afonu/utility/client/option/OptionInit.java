package org.afonu.utility.client.option;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.afonu.utility.client.ConfigSaver;
import org.afonu.utility.client.screen.ArmorHUDWidget;
import org.afonu.utility.client.screen.ItemHUDWidget;

public class OptionInit {
    public static Option Option;

    public static void Init() {
        Option = ConfigSaver.ReadConfig();
        Load();
    }

    public static void Load() {
        Option_AttackBlock.AttackBlock();
        Option_AttackEntity.AttackEntity();

        //ArmorHUD
        HudRenderCallback.EVENT.register(new ArmorHUDWidget());
        HudRenderCallback.EVENT.register(new ItemHUDWidget());
    }
}
