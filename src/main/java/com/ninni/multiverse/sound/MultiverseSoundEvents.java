package com.ninni.multiverse.sound;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static com.ninni.multiverse.Multiverse.*;

public class MultiverseSoundEvents {

    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_AMBIENT = register("entity.cobblestone_golem.ambient");
    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_DEATH = register("entity.cobblestone_golem.death");
    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_HURT = register("entity.cobblestone_golem.hurt");

    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
