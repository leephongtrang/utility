package org.afonu.utility.client;

import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.afonu.utility.client.option.Option;

import java.util.function.Function;

public class ConfigScreen extends Screen {
    //Option
    private Option option;

    public ConfigScreen() {
        super(Text.of("Config screen?"));

        option = ConfigSaver.ReadConfig();
    }

    //Widget
    //Button
    public ButtonWidget saveButton;
    public ButtonWidget button1;
    public ButtonWidget button2;

    //Checker
    public CheckboxWidget checkerAllowAttackEntity;
    public CheckboxWidget checkerAllowAttackBlock;

    @Override
    protected void init() {
        saveButton = ButtonWidget.builder(Text.literal("Save"), button -> {
                            ConfigSaver.SaveConfig(option);
                        })
                        .dimensions(width/2, height - 20, 200, 20)
                        .tooltip(Tooltip.of(Text.literal("Save button")))
                        .build();

//        checkerAllowAttackBlock = CheckboxWidget.builder(Text.literal("Allow Attack Block"), new TextRenderer(new Function<Identifier, FontStorage>() {
//        }, false)
//                .dimensions(width/2, height - 20, 200, 20)
//                .tooltip(Tooltip.of(Text.literal("Save button")))
//                .build();

        button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
                    System.out.println("You clicked button1!");
                })
                .dimensions(width / 2 - 205, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();


        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
                    System.out.println("You clicked button2!");
                })
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();



        addDrawableChild(saveButton);
//        addDrawableChild(checkerAllowAttackBlock);
//        addDrawableChild(checkerAllowAttackEntity);
        addDrawableChild(button1);
        addDrawableChild(button2);
    }
}
