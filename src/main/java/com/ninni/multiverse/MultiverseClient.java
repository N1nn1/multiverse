package com.ninni.multiverse;

import com.ninni.multiverse.block.MultiverseBlocks;
import com.ninni.multiverse.client.models.CobblestoneGolemModel;
import com.ninni.multiverse.client.models.ExhaustedCobblestoneGolemModel;
import com.ninni.multiverse.client.models.GorbModel;
import com.ninni.multiverse.client.models.MultiverseModelLayers;
import com.ninni.multiverse.client.models.RainbowSheepModel;
import com.ninni.multiverse.client.renderer.CobblestoneGolemRenderer;
import com.ninni.multiverse.client.renderer.ColorfulPaintingRenderer;
import com.ninni.multiverse.client.renderer.ExhaustedCobblestoneGolemRenderer;
import com.ninni.multiverse.client.renderer.GorbRenderer;
import com.ninni.multiverse.client.renderer.RainbowSheepRenderer;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import com.ninni.multiverse.network.MultiverseNetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;

public class MultiverseClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(MultiverseEntityTypes.COBBLESTONE_GOLEM, CobblestoneGolemRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MultiverseModelLayers.COBBLESTONE_GOLEM, CobblestoneGolemModel::getLayerDefinition);
		EntityRendererRegistry.register(MultiverseEntityTypes.EXHAUSTED_COBBLESTONE_GOLEM, ExhaustedCobblestoneGolemRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MultiverseModelLayers.EXHAUSTED_COBBLESTONE_GOLEM, ExhaustedCobblestoneGolemModel::getLayerDefinition);
		EntityRendererRegistry.register(MultiverseEntityTypes.RAINBOW_SHEEP, RainbowSheepRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MultiverseModelLayers.RAINBOW_SHEEP, RainbowSheepModel::getLayerDefinition);
		EntityRendererRegistry.register(MultiverseEntityTypes.COLORFUL_PAINTING, ColorfulPaintingRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MultiverseModelLayers.GORB, GorbModel::getLayerDefinition);
		EntityRendererRegistry.register(MultiverseEntityTypes.GORB, GorbRenderer::new);

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
				MultiverseBlocks.COBBLESTONE_GOLEM_LORE_TABLET,
				MultiverseBlocks.SPRINKLER_LORE_TABLET,
				MultiverseBlocks.GRINDSTONE_GOLEM_LORE_TABLET,
				MultiverseBlocks.PLANK_GOLEM_LORE_TABLET
		);

		MultiverseNetwork.init();

	}

}
