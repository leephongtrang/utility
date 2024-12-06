package org.afonu.utility.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.navigation.GuiNavigationPath;
import net.minecraft.client.gui.navigation.NavigationDirection;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

@Environment(EnvType.CLIENT)
public class MenuListWidget extends ElementListWidget {

    public MenuListWidget(MinecraftClient minecraftClient, int positionX, int j, int k, int l) {
        super(minecraftClient, positionX, j, k, l);
    }

    public MenuListWidget(MinecraftClient minecraftClient, int positionX, int j, int k, int l, int m) {
        super(minecraftClient, positionX, j, k, l, m);
    }
}
