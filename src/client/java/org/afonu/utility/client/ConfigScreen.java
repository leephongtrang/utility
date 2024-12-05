package org.afonu.utility.client;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import org.afonu.utility.client.option.Option;

public class ConfigScreen extends Screen {
    //Option
    private Option option;

    //Color
    int RED = 0xff1100;
    int GREEN = 0x00ab00;

    public ConfigScreen() {
        super(Text.of("Config screen?"));
    }

    //Widget
    //Button
    public ButtonWidget saveButton;
    public ButtonWidget buttonAttackBlock;
    public ButtonWidget button2;

    //Checker
    public CheckboxWidget checkerAllowAttackEntity;
    public CheckboxWidget checkerAllowAttackBlock;

    @Override
    protected void init() {
        option = ConfigSaver.ReadConfig();
        SetButtons();



//        checkerAllowAttackBlock = CheckboxWidget.builder(Text.literal("Allow Attack Block"), new TextRenderer(new Function<Identifier, FontStorage>() {
//        }, false)
//                .dimensions(width/2, height - 20, 200, 20)
//                .tooltip(Tooltip.of(Text.literal("Save button")))
//                .build();


        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
                    System.out.println("You clicked button2!");
                })
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();



        addDrawableChild(saveButton);
//        addDrawableChild(checkerAllowAttackBlock);
//        addDrawableChild(checkerAllowAttackEntity);
        addDrawableChild(buttonAttackBlock);
        addDrawableChild(button2);
    }

    private void UpdateText(ButtonWidget buttonWidget, String optionName, String value) {
        String textValue = value.equals("true") ? "§aTrue" : "§cFalse";
        buttonWidget.setMessage(Text.literal(optionName + textValue));
    }

    private void SetButtons() {
        saveButton = ButtonWidget.builder(Text.literal("Save"), button -> {
                ConfigSaver.SaveConfig(option);
                this.close();
            })
            .dimensions(width/2 - 100, height - 30, 200, 20)
            //.tooltip(Tooltip.of(Text.literal("Save button")))
            .build();

        buttonAttackBlock = ButtonWidget.builder(Text.literal("Attack Block: " + (option.allowAttackBlock ? "§aTrue§a"  : "§cFalse§c")), button -> {
                    System.out.println("You clicked buttonAttackBlock!");
                    option.allowAttackBlock = !option.allowAttackBlock;
                    UpdateText(buttonAttackBlock, "Attack Block: ", String.valueOf(option.allowAttackBlock));
                })
                .dimensions(width / 2 - 105, 20, 100, 20)
                .tooltip(Tooltip.of(Text.literal("§aTrue§f: Allow breaking block with player's tool.\n§cFalse§f: Prevent player from breaking block with its tool.")))
                .build();
    }
}
