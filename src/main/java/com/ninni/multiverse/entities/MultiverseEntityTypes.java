package com.ninni.multiverse.entities;

import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.MultiverseTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;

public class MultiverseEntityTypes {

    public static final EntityType<CobblestoneGolem> COBBLESTONE_GOLEM = register(
            "cobblestone_golem",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(CobblestoneGolem::new)
                    .defaultAttributes(CobblestoneGolem::createAttributes)
                    .spawnGroup(MobCategory.MISC)
                    .dimensions(EntityDimensions.fixed(0.9F, 0.9F)),
            null
    );

    public static final EntityType<ExhaustedCobblestoneGolem> EXHAUSTED_COBBLESTONE_GOLEM = register(
            "exhausted_cobblestone_golem",
            FabricEntityTypeBuilder.createLiving()
                    .<ExhaustedCobblestoneGolem>entityFactory(ExhaustedCobblestoneGolem::new)
                    .defaultAttributes(LivingEntity::createLivingAttributes)
                    .spawnGroup(MobCategory.MISC)
                    .dimensions(EntityDimensions.fixed(0.9F, 0.9F)),
            null
    );

    public static final EntityType<RainbowSheep> RAINBOW_SHEEP = registerMob(
        "rainbow_sheep",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(RainbowSheep::new)
                               .defaultAttributes(RainbowSheep::createAttributes)
                               .spawnRestriction(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RainbowSheep::checkRainbowSheepSpawnRules)
                               .spawnGroup(MobCategory.CREATURE)
                               .dimensions(EntityDimensions.fixed(0.9F, 1.3F))
                               .trackRangeChunks(10),
        new Tuple<>(0xFFFFFF, 0xFFFFFF)
    );

    public static final EntityType<ColorfulPainting> COLORFUL_PAINTING = register(
            "colorful_painting",
            FabricEntityTypeBuilder.create()
                    .<ColorfulPainting>entityFactory(ColorfulPainting::new)
                    .spawnGroup(MobCategory.MISC)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .trackRangeChunks(10),
            null
    );

    public static final EntityType<Gorb> GORB = register(
            "gorb",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(Gorb::new)
                    .defaultAttributes(Gorb::createAttributes)
                    .spawnGroup(MobCategory.CREATURE)
                    .dimensions(EntityDimensions.fixed(0.9F, 0.9F))
                    .trackRangeChunks(10),
            new int[]{0xFFFFFFFF, 0xFFFFFFFF}
    );

    private static <T extends Mob> EntityType<T> registerMob(String id, FabricEntityTypeBuilder<T> entityType, Tuple<Integer, Integer> spawnEggColors) {
        EntityType<T> builtEntityType = entityType.build();

        if (spawnEggColors != null) {
            Registry.register(Registry.ITEM, new ResourceLocation(Multiverse.MOD_ID, id + "_spawn_egg"), new SpawnEggItem(builtEntityType, spawnEggColors.getA(), spawnEggColors.getB(), new FabricItemSettings().maxCount(64).group(Multiverse.TAB)));
        }

        return Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Multiverse.MOD_ID, id), builtEntityType);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType, int[] spawnEggColors) {
        if (spawnEggColors != null)
            Registry.register(Registry.ITEM, new ResourceLocation(Multiverse.MOD_ID, id + "_spawn_egg"), new SpawnEggItem((EntityType<? extends Mob>) entityType, spawnEggColors[0], spawnEggColors[1], new Item.Properties().tab(Multiverse.TAB)));

        return Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Multiverse.MOD_ID, id), entityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, int[] spawnEggColors) {
        return register(id, entityType.build(), spawnEggColors);
    }

    static {
        BiomeModifications.addSpawn(BiomeSelectors.tag(MultiverseTags.RAINBOW_SHEEP_SPAWNS), MobCategory.CREATURE, MultiverseEntityTypes.RAINBOW_SHEEP, 12, 1, 1);
    }

}
