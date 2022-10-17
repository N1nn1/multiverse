package com.ninni.multiverse.entities;

import com.ninni.multiverse.Multiverse;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class MultiversePaintingVariants {

    public static final PaintingVariant CONTRAST = register("contrast", new PaintingVariant(16, 16));
    public static final PaintingVariant THE_EYE = register("the_eye", new PaintingVariant(16, 32));
    public static final PaintingVariant VILLAGER_MONROE = register("villager_monroe", new PaintingVariant(32, 32));

    private static PaintingVariant register(String name, PaintingVariant paintingVariant) {
        return Registry.register(Registry.PAINTING_VARIANT, new ResourceLocation(Multiverse.MOD_ID, name), paintingVariant);
    }

}
