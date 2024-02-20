package dev.microcontrollers.simpleblockoverlay.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
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

    @SuppressWarnings("deprecation")
    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Simple Block Overlay"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Simple Block Overlay"))
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Red"))
                                .binding(0F, () -> config.red, newVal -> config.red = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value) + "%"))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Green"))
                                .binding(0F, () -> config.green, newVal -> config.green = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value) + "%"))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Blue"))
                                .binding(0F, () -> config.blue, newVal -> config.blue = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value) + "%"))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Alpha"))
                                .binding(102F, () -> config.alpha, newVal -> config.alpha = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%,.0f", value) + "%"))
                                        .range(0F, 255F)
                                        .step(1F))
                                .build())
                        .build()
        ))).generateScreen(parent);
    }
}
