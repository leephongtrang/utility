package org.afonu.utility.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Action {
    private static KeyBinding OPEN_CONFIG_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding("Open Menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, "Utility"));

    public static void init() {
        // Enregistrer le raccourci clavier
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (OPEN_CONFIG_KEY.isPressed()) {
                MinecraftClient.getInstance().setScreen(new ConfigScreen()); // Ouvrir l'écran de configuration
            }
        });
    }
}
