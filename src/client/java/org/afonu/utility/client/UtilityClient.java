package org.afonu.utility.client;

import net.fabricmc.api.ClientModInitializer;

public class UtilityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigSaver.DefaultConfig();
        ConfigSaver.ReadConfig();

        Action.init();
    }
}
