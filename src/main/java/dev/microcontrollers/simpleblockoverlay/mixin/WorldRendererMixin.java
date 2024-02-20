package dev.microcontrollers.simpleblockoverlay.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.microcontrollers.simpleblockoverlay.config.SimpleBlockOverlayConfig;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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