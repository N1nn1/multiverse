package com.ninni.multiverse.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Painting.class)
public interface PaintingAccessor {
    @Invoker
    static int callVariantArea(Holder<PaintingVariant> holder) {
        throw new UnsupportedOperationException();
    }

    @Invoker
    void callSetVariant(Holder<PaintingVariant> holder);
}
