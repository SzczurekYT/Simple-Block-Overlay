package dev.microcontrollers.simpleblockoverlay;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.microcontrollers.simpleblockoverlay.config.SimpleBlockOverlayConfig;
import dev.microcontrollers.simpleblockoverlay.util.ColorUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.awt.*;

public class SimpleBlockOverlay implements ModInitializer {
	@Override
	public void onInitialize() {
		SimpleBlockOverlayConfig.CONFIG.load();

		WorldRenderEvents.LAST.register(SimpleBlockOverlay::renderOutlineBox);
	}

	public static void renderOutlineBox(WorldRenderContext context) {
		HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;
		if (!(context.blockOutlines()  && hitResult != null && hitResult.getType() == HitResult.Type.BLOCK)) {
			return;
		}
		ClientWorld world = context.world();
		BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
		BlockState state = world.getBlockState(pos);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();

		buffer.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);

		Color color = ColorUtil.getColor();

		float cameraX = (float) context.camera().getPos().getX();
		float cameraY = (float) context.camera().getPos().getY();
		float cameraZ = (float) context.camera().getPos().getZ();

		state.getSidesShape(world, pos).forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
			// We make the cube a tiny bit lager to avoid z fighting
			double zFightingOffset = 0.001;
			WorldRenderer.renderFilledBox(context.matrixStack(),
					buffer,
					(float) (pos.getX() + minX - cameraX - zFightingOffset),
					(float) (pos.getY() + minY - cameraY - zFightingOffset),
					(float) (pos.getZ() + minZ - cameraZ - zFightingOffset),
					(float) (pos.getX() + maxX - cameraX + zFightingOffset),
					(float) (pos.getY() + maxY - cameraY + zFightingOffset),
					(float) (pos.getZ() + maxZ - cameraZ + zFightingOffset),
					color.getRed() / 255F,
					color.getGreen() / 255F,
					color.getBlue() / 255F,
					color.getAlpha() / 255F);
		});

		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		RenderSystem.setShader(GameRenderer::getPositionColorProgram);

		tessellator.draw();

		RenderSystem.disableBlend();
		RenderSystem.disableDepthTest();
	}
}