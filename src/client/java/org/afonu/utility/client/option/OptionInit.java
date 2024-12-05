package org.afonu.utility.client.option;

import org.afonu.utility.client.ConfigSaver;

public class OptionInit {
    public static Option Option;

    public static void Init() {
        Option = ConfigSaver.ReadConfig();
        Load();
    }

    public static void Load() {
        Option_AttackBlock.AttackBlock(Option.allowAttackBlock);
        Option_AttackEntity.AttackEntity(Option.allowAttackEntity);
    }
}
