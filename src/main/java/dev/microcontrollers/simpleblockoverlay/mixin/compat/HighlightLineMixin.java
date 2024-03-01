package dev.microcontrollers.simpleblockoverlay.mixin.compat;

import dev.microcontrollers.simpleblockoverlay.util.ColorUtil;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

// this is compatability for the Highlight mod
@Pseudo
@Mixin(targets = "com.teamresourceful.resourcefullib.client.highlights.base.HighlightLine", remap = false)
public class HighlightLineMixin {
    @Dynamic
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;color(FFFF)Lnet/minecraft/client/render/VertexConsumer;"))
    private void changeHighlightLineColors(Args args) {
        ColorUtil.setLineColor(args, 6);
    }
}
