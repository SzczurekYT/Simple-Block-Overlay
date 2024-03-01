package dev.microcontrollers.simpleblockoverlay.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SimpleBlockOverlayConfig {
    public static final ConfigClassHandler<SimpleBlockOverlayConfig> CONFIG = ConfigClassHandler.createBuilder(SimpleBlockOverlayConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("simpleblockoverlay.json"))
                    .build())
            .build();

    @SerialEntry public float red = 0;
    @SerialEntry public float blue = 0;
    @SerialEntry public float green = 0;
    @SerialEntry public float alpha = 102;
    @SerialEntry public boolean chroma = false;
    @SerialEntry public float saturation = 1F;
    @SerialEntry public float brightness = 1F;
    @SerialEntry public float speed = 0.25F;

    @SuppressWarnings("deprecation")
    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Simple Block Overlay"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Simple Block Overlay"))
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Red"))
                                .description(OptionDescription.of(Text.of("The red value for the overlay. Only applies when Chroma is not enabled.")))
                                .binding(0F, () -> config.red, newVal -> config.red = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value)))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Green"))
                                .description(OptionDescription.of(Text.of("The green value for the overlay. Only applies when Chroma is not enabled.")))
                                .binding(0F, () -> config.green, newVal -> config.green = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value)))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Blue"))
                                .description(OptionDescription.of(Text.of("The blue value for the overlay. Only applies when Chroma is not enabled.")))
                                .binding(0F, () -> config.blue, newVal -> config.blue = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value)))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Alpha"))
                                .description(OptionDescription.of(Text.of("The red value for the overlay. Applies always.")))
                                .binding(102F, () -> config.alpha, newVal -> config.alpha = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value)))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Chroma"))
                                .description(OptionDescription.of(Text.of("Enables a rainbow/chroma effect. Disregards the RGB values provided above, but alpha is still accepted.")))
                                .binding(defaults.chroma, () -> config.chroma, newVal -> config.chroma = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Saturation"))
                                .description(OptionDescription.of(Text.of("Saturation value for the chroma effect.")))
                                .binding(1F, () -> config.saturation, newVal -> config.saturation = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.1f", value)))
                                        .range(0F, 1F)
                                        .step(0.1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Brightness"))
                                .description(OptionDescription.of(Text.of("Brightness value for the chroma effect.")))
                                .binding(1F, () -> config.brightness, newVal -> config.brightness = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.1f", value)))
                                        .range(0F, 1F)
                                        .step(0.1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Speed"))
                                .description(OptionDescription.of(Text.of("Speed value for the chroma effect.")))
                                .binding(0.25F, () -> config.speed, newVal -> config.speed = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.1f", value)))
                                        .range(0F, 1F)
                                        .step(0.1F))
                                .build())
                        .build()
        ))).generateScreen(parent);
    }
}
