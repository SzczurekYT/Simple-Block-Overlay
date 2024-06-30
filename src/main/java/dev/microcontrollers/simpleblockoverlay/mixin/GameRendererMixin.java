package dev.microcontrollers.simpleblockoverlay.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.microcontrollers.simpleblockoverlay.config.SimpleBlockOverlayConfig;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @ModifyReturnValue(
            method = "shouldRenderBlockOutline",
            at = @At("RETURN")
    )
    private boolean overrideRenderingCondition(boolean original) {
        return SimpleBlockOverlayConfig.CONFIG.instance().alwaysRenderOutline || original;
    }
}
