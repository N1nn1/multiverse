package com.ninni.multiverse.mixin;

import com.ninni.multiverse.client.renderer.ColorfulPaintingRenderer;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@Mixin(PaintingTextureManager.class)
public class PaintingTextureManagerMixin {

    @Shadow @Final private static ResourceLocation BACK_SPRITE_LOCATION;

    @Inject(at = @At("RETURN"), method = "getResourcesToLoad", cancellable = true)
    private void M$getResourcesToLoad(CallbackInfoReturnable<Stream<ResourceLocation>> cir) {
        cir.setReturnValue(Stream.concat(Registry.PAINTING_VARIANT.keySet().stream(), Stream.of(BACK_SPRITE_LOCATION, ColorfulPaintingRenderer.BACK_TEXTURE_ATLAS_LOCATION)));
    }

}
