package org.afonu.utility.client;

import net.fabricmc.api.ClientModInitializer;
import org.afonu.utility.client.option.OptionInit;
import org.afonu.utility.client.option.Option_AttackBlock;

public class UtilityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigSaver.DefaultConfig();

        OptionInit.Init(ConfigSaver.ReadConfig());

        Action.init();
    }
}
