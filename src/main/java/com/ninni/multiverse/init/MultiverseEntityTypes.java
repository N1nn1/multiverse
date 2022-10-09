package com.ninni.multiverse.init;

import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class MultiverseEntityTypes {

    public static final EntityType<CobblestoneGolemEntity> COBBLESTONE_GOLEM = register(
            "cobblestone_golem",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(CobblestoneGolemEntity::new)
                    .defaultAttributes(CobblestoneGolemEntity::createCobblestoneGolemAttributes)
                    .spawnGroup(MobCategory.MISC)
                    .dimensions(EntityDimensions.fixed(0.8F, 1F)),
            null
    );

    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType, int[] spawnEggColors) {
        if (spawnEggColors != null)
            Registry.register(Registry.ITEM, new ResourceLocation(Multiverse.MOD_ID, id + "_spawn_egg"), new SpawnEggItem((EntityType<? extends Mob>) entityType, spawnEggColors[0], spawnEggColors[1], new Item.Properties().tab(Multiverse.TAB)));

        return Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Multiverse.MOD_ID, id), entityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, int[] spawnEggColors) {
        return register(id, entityType.build(), spawnEggColors);
    }

}
