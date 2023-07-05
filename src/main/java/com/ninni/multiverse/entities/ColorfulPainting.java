package com.ninni.multiverse.entities;

import com.ninni.multiverse.init.MultiverseTags;
import com.ninni.multiverse.init.MultiverseEntityTypes;
import com.ninni.multiverse.init.MultiverseItems;
import com.ninni.multiverse.mixin.accessors.PaintingAccessor;
import com.ninni.multiverse.init.MultiverseSoundEvents;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;

public class ColorfulPainting extends Painting {

    public ColorfulPainting(EntityType<? extends ColorfulPainting> entityType, Level level) {
        super(entityType, level);
    }

    private ColorfulPainting(Level level, BlockPos blockPos) {
        this(MultiverseEntityTypes.COLORFUL_PAINTING, level, blockPos);
    }

    public ColorfulPainting(EntityType<? extends ColorfulPainting> entityType, Level level, BlockPos blockPos) {
        this(entityType, level);
        this.pos = blockPos;
    }

    public static Optional<Painting> create(Level level, BlockPos blockPos, Direction direction) {
        ColorfulPainting painting = new ColorfulPainting(level, blockPos);
        ArrayList<Holder<PaintingVariant>> list = new ArrayList<>();
        BuiltInRegistries.PAINTING_VARIANT.getTagOrEmpty(MultiverseTags.COLORFUL_PLACEABLE).forEach(list::add);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        painting.setDirection(direction);
        list.removeIf(holder -> {
            ((PaintingAccessor)painting).callSetVariant(holder);
            return !painting.survives();
        });
        if (list.isEmpty()) {
            return Optional.empty();
        }
        int i = list.stream().mapToInt(PaintingAccessor::callVariantArea).max().orElse(0);
        list.removeIf(holder -> PaintingAccessor.callVariantArea(holder) < i);
        Optional<Holder<PaintingVariant>> optional = Util.getRandomSafe(list, painting.random);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        ((PaintingAccessor)painting).callSetVariant(optional.get());
        painting.setDirection(direction);
        return Optional.of(painting);
    }

    @Override
    public void dropItem(@Nullable Entity entity) {
        if (!this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            return;
        }
        this.playSound(MultiverseSoundEvents.BLOCK_COLORFUL_PAINTING_BREAK, 1.0f, 1.0f);
        if (entity instanceof Player player) {
            if (player.getAbilities().instabuild) {
                return;
            }
        }
        this.spawnAtLocation(MultiverseItems.COLORFUL_PAINTING);
    }

    @Override
    public void playPlacementSound() {
        this.playSound(MultiverseSoundEvents.BLOCK_COLORFUL_PAINTING_PLACE, 1.0F, 1.0F);
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(MultiverseItems.COLORFUL_PAINTING);
    }
}
