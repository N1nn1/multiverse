package com.ninni.multiverse.mixin;

import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PaintingTextureManager.class)
public class PaintingTextureManagerMixin {

    @Shadow @Final private static ResourceLocation BACK_SPRITE_LOCATION;

//    @Inject(at = @At("RETURN"), method = "get", cancellable = true)
//    private void M$getResourcesToLoad(CallbackInfoReturnable<TextureAtlasSprite> cir) {
//        cir.setReturnValue(Stream.concat(BuiltInRegistries.PAINTING_VARIANT.keySet().stream(), Stream.of(BACK_SPRITE_LOCATION, ColorfulPaintingRenderer.BACK_TEXTURE_ATLAS_LOCATION)));
//    }

}
