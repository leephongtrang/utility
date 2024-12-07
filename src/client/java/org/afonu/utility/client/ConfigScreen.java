package org.afonu.utility.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.afonu.utility.client.option.Option;
import org.afonu.utility.client.option.OptionInit;
import org.afonu.utility.client.option.Option_PauseGameOnMenu;
import org.afonu.utility.client.screen.MenuListWidget;

import java.awt.*;

public class ConfigScreen extends Screen {
    //Option
    private Option option;

    //Color
    String GREEN = "§a";
    String RED = "§c";
    String WHITE = "§f";

    //Space
    int SPACE_BETWEEN_Y = 5;

    public ConfigScreen() {
        super(Text.of("Config screen?"));
    }

    //Widget
    //Button
    public ButtonWidget saveButton;
    public ButtonWidget buttonAttackBlock;
    public ButtonWidget buttonAttackEntity;
    public ButtonWidget buttonItemHUD;
    public ButtonWidget buttonArmorHUD;
    public ButtonWidget button2;
    public ButtonWidget buttonPauseGameOnMenu;

    //List
    public MenuListWidget elementListWidget;
    //public List


    public SliderWidget sliderItemHUDPositionX;
    public SliderWidget sliderItemHUDPositionY;


    //Checker
    public CheckboxWidget checkerAllowAttackEntity;
    public CheckboxWidget checkerAllowAttackBlock;

    @Override
    protected void init() {
        option = ConfigSaver.ReadConfig();
        SetButtons();
        SetList();
        SetSlider();

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

        addDrawableChild(sliderItemHUDPositionX);
        addDrawableChild(sliderItemHUDPositionY);
        addDrawableChild(buttonItemHUD);
        addDrawableChild(buttonArmorHUD);
        addDrawableChild(buttonPauseGameOnMenu);
        addDrawableChild(saveButton);
//        addDrawableChild(checkerAllowAttackBlock);
//        addDrawableChild(checkerAllowAttackEntity);
        addDrawableChild(buttonAttackBlock);
        addDrawableChild(buttonAttackEntity);
        addDrawableChild(button2);
        addDrawableChild(elementListWidget);
    }

    @Override
    public boolean shouldPause() {
        return !Option_PauseGameOnMenu.PauseGameOnMenu();
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

        buttonAttackBlock = ButtonWidget.builder(Text.literal("Attack Block: " + (option.allowAttackBlock ? GREEN + "True" : RED + "False")), button -> {
                    System.out.println("You clicked buttonAttackBlock!");
                    option.allowAttackBlock = !option.allowAttackBlock;
                    UpdateText(buttonAttackBlock, "Attack Block: ", String.valueOf(option.allowAttackBlock));
                })
                .dimensions(width / 2 - 105, 20, 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Allow breaking block with player's tool.\n" + RED + "False" + WHITE + ": Prevent player from breaking block with its tool.")))
                .build();

        buttonAttackEntity = ButtonWidget.builder(Text.literal("Attack Entity: " + (option.allowAttackEntity ? GREEN + "True" : RED + "False")), button -> {
                    System.out.println("You clicked buttonAttackEntity!");
                    option.allowAttackEntity = !option.allowAttackEntity;
                    UpdateText(buttonAttackEntity, "Attack Entity: ", String.valueOf(option.allowAttackEntity));
                })
                .dimensions(width / 2 - 105, 45, 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Allow attacking on entity with player's tool.\n"+ RED + "False" + WHITE + ": Prevent player from attacking entity with its tool.")))
                .build();

        buttonItemHUD = ButtonWidget.builder(Text.literal("Item HUD: " + (option.toggleItemHUD ? GREEN + "True" : RED + "False")), button -> {
                    System.out.println("You clicked buttonAttackBlock!");
                    option.toggleItemHUD = !option.toggleItemHUD;
                    UpdateText(buttonItemHUD, "Item HUD: ", String.valueOf(option.toggleItemHUD));
                })
                .dimensions(width / 2 - 105, (60 + (2*SPACE_BETWEEN_Y)), 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Allow breaking block with player's tool.\n" + RED + "False" + WHITE + ": Prevent player from breaking block with its tool.")))
                .build();

        buttonArmorHUD = ButtonWidget.builder(Text.literal("Armor HUD: " + (option.toggleArmorHUD ? GREEN + "True" : RED + "False")), button -> {
                    System.out.println("You clicked buttonArmorHUD!");
                    option.toggleArmorHUD = !option.toggleArmorHUD;
                    UpdateText(buttonArmorHUD, "Armor HUD: ", String.valueOf(option.toggleArmorHUD));
                })
                .dimensions(width / 2 - 105, (80 + (3*SPACE_BETWEEN_Y)), 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Show armor HUD. \n" + RED + "False" + WHITE + ": Hide armor HUD.")))
                .build();

        buttonPauseGameOnMenu = ButtonWidget.builder(Text.literal("pauseMenu"), button -> {
                    System.out.println("You clicked button2!");
                    option.pauseGameOnMenu = !option.pauseGameOnMenu;
                })
                .dimensions(width / 2 + 5, 40, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();
    }

    private void SetList() {
        elementListWidget = new MenuListWidget(this.client, width - 50, 100, 40, height/3 -20, 20);
    }

    private void SetSlider() {
        double dItemX = (double) option.itemHUD_positionX /10;
        double dItemY = (double) option.itemHUD_positionY /10;
        sliderItemHUDPositionX = new SliderWidget(100, 120, 100, 20, Text.literal("X: " + dItemX + "%"), (double) option.itemHUD_positionX /1000) {
            @Override
            protected void updateMessage() {
                double d = (double) option.itemHUD_positionX /10;
                this.setMessage(Text.literal("X: " + d + "%"));
            }

            @Override
            protected void applyValue() {
                option.itemHUD_positionX = (int) (this.value * 1000);
            }
        };
        sliderItemHUDPositionX.setTooltip(Tooltip.of(Text.literal("Tooltip")));

        sliderItemHUDPositionY = new SliderWidget(100, 100, 100, 20, Text.literal("Y: " + dItemY + "%"), (double) option.itemHUD_positionY /1000) {
            @Override
            protected void updateMessage() {
                double d = (double) option.itemHUD_positionY /10;
                this.setMessage(Text.literal("X: " + d + "%"));
            }

            @Override
            protected void applyValue() {
                option.itemHUD_positionY = (int) (this.value * 1000);
            }
        };
        sliderItemHUDPositionY.setTooltip(Tooltip.of(Text.literal("Tooltip")));


    }
}
