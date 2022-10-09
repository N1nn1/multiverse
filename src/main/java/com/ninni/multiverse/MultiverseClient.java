package com.ninni.multiverse;

import com.ninni.multiverse.client.models.CobblestoneGolemModel;
import com.ninni.multiverse.client.renderer.CobblestoneGolemRenderer;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import com.ninni.multiverse.client.models.MultiverseModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MultiverseClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(MultiverseEntityTypes.COBBLESTONE_GOLEM, CobblestoneGolemRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MultiverseModelLayers.COBBLESTONE_GOLEM, CobblestoneGolemModel::getLayerDefinition);
	}

}
