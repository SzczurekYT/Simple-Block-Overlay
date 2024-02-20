package dev.microcontrollers.simpleblockoverlay;

import dev.microcontrollers.simpleblockoverlay.config.SimpleBlockOverlayConfig;
import net.fabricmc.api.ModInitializer;

public class SimpleBlockOverlay implements ModInitializer {
	@Override
	public void onInitialize() {
		SimpleBlockOverlayConfig.CONFIG.load();
	}
}