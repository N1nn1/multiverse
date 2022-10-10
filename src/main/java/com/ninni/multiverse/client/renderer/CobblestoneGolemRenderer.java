package com.ninni.multiverse.client.renderer;

import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.client.models.CobblestoneGolemModel;
import com.ninni.multiverse.client.models.MultiverseModelLayers;
import com.ninni.multiverse.client.renderer.layer.CobblestoneGolemCrackinessLayer;
import com.ninni.multiverse.client.renderer.layer.CobblestoneMiningBlockLayer;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class CobblestoneGolemRenderer extends MobRenderer<CobblestoneGolemEntity, CobblestoneGolemModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Multiverse.MOD_ID, "textures/entity/cobblestone_golem/cobblestone_golem.png");

    public CobblestoneGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new CobblestoneGolemModel(context.bakeLayer(MultiverseModelLayers.COBBLESTONE_GOLEM)), 0.5F);
        this.addLayer(new CobblestoneGolemCrackinessLayer<>(this));
        this.addLayer(new CobblestoneMiningBlockLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(CobblestoneGolemEntity entity) {
        return TEXTURE;
    }

}
