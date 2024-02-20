package dev.microcontrollers.simpleblockoverlay.mixin;

import dev.microcontrollers.simpleblockoverlay.config.SimpleBlockOverlayConfig;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@ModifyArgs(method = "drawBlockOutline", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawCuboidShapeOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/util/shape/VoxelShape;DDDFFFF)V"))
	private void changeColors(Args args) {
		args.set(6, SimpleBlockOverlayConfig.CONFIG.instance().red / 255F);
		args.set(7, SimpleBlockOverlayConfig.CONFIG.instance().green / 255F);
		args.set(8, SimpleBlockOverlayConfig.CONFIG.instance().blue / 255F);
		args.set(9, SimpleBlockOverlayConfig.CONFIG.instance().alpha / 255F);
	}
}