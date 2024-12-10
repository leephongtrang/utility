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
    public ButtonWidget buttonItemHUDItem;
    public ButtonWidget buttonItemHUDDamage;
    public ButtonWidget buttonArmorHUDItem;
    public ButtonWidget buttonArmorHUDDamage;

    //List
    public MenuListWidget elementListWidget;
    //public List


    public SliderWidget sliderItemHUDPositionX;
    public SliderWidget sliderItemHUDPositionY;
    public SliderWidget sliderArmorHUDPositionX;
    public SliderWidget sliderArmorHUDPositionY;


    //Checker
    public CheckboxWidget checkerAllowAttackEntity;
    public CheckboxWidget checkerAllowAttackBlock;

    @Override
    protected void init() {
        option = ConfigSaver.ReadConfig();
        SetButtons();
        SetList();
        SetSlider();
        SetLeftRow();
        SetRightRow();
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
        addDrawableChild(sliderArmorHUDPositionX);
        addDrawableChild(sliderArmorHUDPositionY);
        addDrawableChild(buttonItemHUD);
        addDrawableChild(buttonArmorHUD);
        addDrawableChild(buttonPauseGameOnMenu);
        addDrawableChild(saveButton);
//        addDrawableChild(checkerAllowAttackBlock);
//        addDrawableChild(checkerAllowAttackEntity);
        addDrawableChild(buttonAttackBlock);
        addDrawableChild(buttonAttackEntity);
        addDrawableChild(button2);
        addDrawableChild(buttonItemHUDItem);
        addDrawableChild(buttonItemHUDDamage);
        addDrawableChild(buttonArmorHUDItem);
        addDrawableChild(buttonArmorHUDDamage);

        addDrawableChild(elementListWidget);
    }

    //TODO Fix the Buttons: PauseGame, AttackBlock, AttackEntity, Button 2

    @Override
    public boolean shouldPause() {
        return !Option_PauseGameOnMenu.PauseGameOnMenu();
    }

    private void UpdateText(ButtonWidget buttonWidget, String optionName, String value) {
        String textValue = value.equals("true") ? GREEN + "True" : RED + "False";
        buttonWidget.setMessage(Text.literal(optionName + textValue));
    }

    private void SetLeftRow() {
        buttonItemHUD = ButtonWidget.builder(Text.literal("Item HUD: " + (option.toggleItemHUD ? GREEN + "True" : RED + "False")), button -> {
                    option.toggleItemHUD = !option.toggleItemHUD;
                    UpdateText(buttonItemHUD, "Item HUD: ", String.valueOf(option.toggleItemHUD));
                })
                .dimensions(width / 2 - 210, (80 + (0*SPACE_BETWEEN_Y)), 205, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Show your main hand's item.\n" + RED + "False" + WHITE + ": Hide your main hand's item.")))
                .build();

        buttonItemHUDItem = ButtonWidget.builder(Text.literal("Show Item: " + (option.toggleOnlyItem ? GREEN + "True" : RED + "False")), button -> {
                    option.toggleOnlyItem = !option.toggleOnlyItem;
                    UpdateText(buttonItemHUDItem, "Show Item: ", String.valueOf(option.toggleOnlyItem));
                })
                .dimensions(width / 2 - 105, (100 + (1*SPACE_BETWEEN_Y)), 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Show item's icon.\n" + RED + "False" + WHITE + ": Hide item's icon.")))
                .build();

        buttonItemHUDDamage = ButtonWidget.builder(Text.literal("Show Item Durability: " + (option.toggleOnlyDamageItem ? GREEN + "True" : RED + "False")), button -> {
                    option.toggleOnlyDamageItem = !option.toggleOnlyDamageItem;
                    UpdateText(buttonItemHUDDamage, "Show Item Durability: ", String.valueOf(option.toggleOnlyDamageItem));
                })
                .dimensions(width / 2 - 105, (120 + (2*SPACE_BETWEEN_Y)), 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Show item remaining durability.\n" + RED + "False" + WHITE + ": Hide item remaining durability.")))
                .build();
    }

    private void SetRightRow() {
        buttonArmorHUD = ButtonWidget.builder(Text.literal("Armor HUD: " + (option.toggleArmorHUD ? GREEN + "True" : RED + "False")), button -> {
                    option.toggleArmorHUD = !option.toggleArmorHUD;
                    UpdateText(buttonArmorHUD, "Armor HUD: ", String.valueOf(option.toggleArmorHUD));
                })
                .dimensions(width / 2 + 5, (80 + (0*SPACE_BETWEEN_Y)), 205, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Show armor HUD.\n" + RED + "False" + WHITE + ": Hide armor HUD.")))
                .build();

        buttonArmorHUDItem = ButtonWidget.builder(Text.literal("Show Armor: " + (option.toggleOnlyArmor ? GREEN + "True" : RED + "False")), button -> {
                    option.toggleOnlyArmor = !option.toggleOnlyArmor;
                    UpdateText(buttonArmorHUDItem, "Show Armor: ", String.valueOf(option.toggleOnlyArmor));
                })
                .dimensions(width / 2 + 5, (100 + (1*SPACE_BETWEEN_Y)), 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Show armor's icon.\n" + RED + "False" + WHITE + ": Hide armor's icon.")))
                .build();

        buttonArmorHUDDamage = ButtonWidget.builder(Text.literal("Show Armor Durability: " + (option.toggleOnlyArmorDamage ? GREEN + "True" : RED + "False")), button -> {
                    option.toggleOnlyArmorDamage = !option.toggleOnlyArmorDamage;
                    UpdateText(buttonArmorHUDDamage, "Show Armor Durability: ", String.valueOf(option.toggleOnlyArmorDamage));
                })
                .dimensions(width / 2 + 5, (120 + (2*SPACE_BETWEEN_Y)), 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Show armor's remaining durability.\n" + RED + "False" + WHITE + ": Hide armor's remaining durability.")))
                .build();
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
                .dimensions(width / 2 - 100, 20, 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Allow breaking block with player's tool.\n" + RED + "False" + WHITE + ": Prevent player from breaking block with its tool.")))
                .build();

        buttonAttackEntity = ButtonWidget.builder(Text.literal("Attack Entity: " + (option.allowAttackEntity ? GREEN + "True" : RED + "False")), button -> {
                    System.out.println("You clicked buttonAttackEntity!");
                    option.allowAttackEntity = !option.allowAttackEntity;
                    UpdateText(buttonAttackEntity, "Attack Entity: ", String.valueOf(option.allowAttackEntity));
                })
                .dimensions(width / 2 - 100, 45, 100, 20)
                .tooltip(Tooltip.of(Text.literal(GREEN + "True" + WHITE + ": Allow attacking on entity with player's tool.\n"+ RED + "False" + WHITE + ": Prevent player from attacking entity with its tool.")))
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
        // Item Positions Sliders
        double dItemX = (double) option.itemHUD_positionX /10;
        double dItemY = (double) option.itemHUD_positionY /10;
        sliderItemHUDPositionX = new SliderWidget(width / 2 - 210, (100 + (1*SPACE_BETWEEN_Y)), 100, 20, Text.literal("Item X: " + dItemX + "%"), (double) option.itemHUD_positionX /1000) {
            @Override
            protected void updateMessage() {
                double d = (double) option.itemHUD_positionX /10;
                this.setMessage(Text.literal("Item X: " + d + "%"));
            }

            @Override
            protected void applyValue() {
                option.itemHUD_positionX = (int) (this.value * 1000);
            }
        };
        sliderItemHUDPositionX.setTooltip(Tooltip.of(Text.literal("X Position of Item")));

        sliderItemHUDPositionY = new SliderWidget(width / 2 - 210, (120 + (2*SPACE_BETWEEN_Y)), 100, 20, Text.literal("Item Y: " + dItemY + "%"), (double) option.itemHUD_positionY /1000) {
            @Override
            protected void updateMessage() {
                double d = (double) option.itemHUD_positionY /10;
                this.setMessage(Text.literal("Item Y: " + d + "%"));
            }

            @Override
            protected void applyValue() {
                option.itemHUD_positionY = (int) (this.value * 1000);
            }
        };
        sliderItemHUDPositionY.setTooltip(Tooltip.of(Text.literal("Y Position of Item")));

        // Armor Positions Sliders
        double dArmorX = (double) option.armorHUD_positionX /10;
        double dArmorY = (double) option.armorHUD_positionY /10;
        sliderArmorHUDPositionX = new SliderWidget(width / 2 + 110, (100 + (1*SPACE_BETWEEN_Y)), 100, 20, Text.literal("Armor X: " + dArmorX + "%"), (double) option.armorHUD_positionX /1000) {
            @Override
            protected void updateMessage() {
                double d = (double) option.armorHUD_positionX /10;
                this.setMessage(Text.literal("Armor X: " + d + "%"));
            }

            @Override
            protected void applyValue() {
                option.armorHUD_positionX = (int) (this.value * 1000);
            }
        };
        sliderArmorHUDPositionX.setTooltip(Tooltip.of(Text.literal("X Position of Armor")));

        sliderArmorHUDPositionY = new SliderWidget(width / 2 + 110, (120 + (2*SPACE_BETWEEN_Y)), 100, 20, Text.literal("Armor Y: " + dArmorY + "%"), (double) option.armorHUD_positionY /1000) {
            @Override
            protected void updateMessage() {
                double d = (double) option.armorHUD_positionY /10;
                this.setMessage(Text.literal("Armor Y: " + d + "%"));
            }

            @Override
            protected void applyValue() {
                option.armorHUD_positionY = (int) (this.value * 1000);
            }
        };
        sliderArmorHUDPositionY.setTooltip(Tooltip.of(Text.literal("Y Position of Armor")));

    }
}
