package com.ninni.multiverse.entities;

import com.ninni.multiverse.MultiverseTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;

import static com.ninni.multiverse.Multiverse.MOD_ID;

public class MultiverseEntityTypes {

    public static final EntityType<CobblestoneGolem> COBBLESTONE_GOLEM = register(
            "cobblestone_golem",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(CobblestoneGolem::new)
                    .defaultAttributes(CobblestoneGolem::createAttributes)
                    .spawnGroup(MobCategory.MISC)
                    .dimensions(EntityDimensions.fixed(0.9F, 0.9F))
    );

    public static final EntityType<ExhaustedCobblestoneGolem> EXHAUSTED_COBBLESTONE_GOLEM = register(
            "exhausted_cobblestone_golem",
            FabricEntityTypeBuilder.createLiving()
                    .<ExhaustedCobblestoneGolem>entityFactory(ExhaustedCobblestoneGolem::new)
                    .defaultAttributes(LivingEntity::createLivingAttributes)
                    .spawnGroup(MobCategory.MISC)
                    .dimensions(EntityDimensions.fixed(0.9F, 0.9F))
    );

    public static final EntityType<RainbowSheep> RAINBOW_SHEEP = register(
            "rainbow_sheep",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(RainbowSheep::new)
                    .defaultAttributes(RainbowSheep::createAttributes)
                    .spawnRestriction(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RainbowSheep::checkRainbowSheepSpawnRules)
                    .spawnGroup(MobCategory.CREATURE)
                    .dimensions(EntityDimensions.fixed(0.9F, 1.3F))
                    .trackRangeChunks(10)
    );

    public static final EntityType<Gorb> GORB = register(
            "gorb",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(Gorb::new)
                    .defaultAttributes(Gorb::createAttributes)
                    .spawnGroup(MobCategory.CREATURE)
                    .dimensions(EntityDimensions.scalable(1.2F, 1.0F))
                    .trackRangeChunks(10)
    );

    public static final EntityType<ColorfulPainting> COLORFUL_PAINTING = register(
            "colorful_painting",
            FabricEntityTypeBuilder.create()
                    .<ColorfulPainting>entityFactory(ColorfulPainting::new)
                    .spawnGroup(MobCategory.MISC)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .trackRangeChunks(10)
    );


    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(MOD_ID, id), entityType.build());
    }

    static {
        BiomeModifications.addSpawn(BiomeSelectors.tag(MultiverseTags.RAINBOW_SHEEP_SPAWNS), MobCategory.CREATURE, MultiverseEntityTypes.RAINBOW_SHEEP, 12, 1, 1);
    }

}
