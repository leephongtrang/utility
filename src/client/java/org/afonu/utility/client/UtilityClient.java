package org.afonu.utility.client;

import net.fabricmc.api.ClientModInitializer;
import org.afonu.utility.client.option.OptionInit;

public class UtilityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigSaver.DefaultConfig();

        OptionInit.Init();

        Keybind.Init();
    }
}
