package com.ninni.multiverse.init;

import com.ninni.multiverse.Multiverse;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class MultiversePaintingVariants {

    public static final PaintingVariant CONTRAST = register("contrast", new PaintingVariant(16, 16));
    public static final PaintingVariant THE_EYE = register("the_eye", new PaintingVariant(16, 32));
    public static final PaintingVariant VILLAGER_MONROE = register("villager_monroe", new PaintingVariant(32, 32));
    public static final PaintingVariant ABSTRACT = register("abstract", new PaintingVariant(16, 16));
    public static final PaintingVariant BALANCE = register("balance", new PaintingVariant(16, 16));
    public static final PaintingVariant WOLF = register("wolf", new PaintingVariant(16, 16));
    public static final PaintingVariant BLOCKY_NIGHT = register("blocky_night", new PaintingVariant(48, 32));
    public static final PaintingVariant MELTING_CLOCKS = register("melting_clocks", new PaintingVariant(48, 32));
    public static final PaintingVariant WAVES = register("waves", new PaintingVariant(32, 16));

    private static PaintingVariant register(String name, PaintingVariant paintingVariant) {
        return Registry.register(BuiltInRegistries.PAINTING_VARIANT, new ResourceLocation(Multiverse.MOD_ID, name), paintingVariant);
    }

}
