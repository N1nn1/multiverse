package com.ninni.multiverse.item;

import com.ninni.multiverse.entities.ColorfulPainting;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Optional;

public class ColorfulPaintingItem extends Item {
    private final EntityType<? extends ColorfulPainting> type;

    public ColorfulPaintingItem(EntityType<? extends ColorfulPainting> entityType, Item.Properties properties) {
        super(properties);
        this.type = entityType;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Painting hangingEntity;
        BlockPos blockPos = useOnContext.getClickedPos();
        Direction direction = useOnContext.getClickedFace();
        BlockPos blockPos2 = blockPos.relative(direction);
        Player player = useOnContext.getPlayer();
        ItemStack itemStack = useOnContext.getItemInHand();
        if (player != null && !this.mayPlace(player, direction, itemStack, blockPos2)) {
            return InteractionResult.FAIL;
        }
        Level level = useOnContext.getLevel();
        if (this.type == MultiverseEntityTypes.COLORFUL_PAINTING) {
            Optional<Painting> optional = ColorfulPainting.create(level, blockPos2, direction);
            if (optional.isEmpty()) {
                return InteractionResult.CONSUME;
            }
            hangingEntity = optional.get();
        } else {
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null) {
            EntityType.updateCustomEntityTag(level, player, hangingEntity, compoundTag);
        }
        if (hangingEntity.survives()) {
            if (!level.isClientSide) {
                hangingEntity.playPlacementSound();
                level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingEntity.position());
                level.addFreshEntity(hangingEntity);
            }
            itemStack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.CONSUME;
    }

    protected boolean mayPlace(Player player, Direction direction, ItemStack itemStack, BlockPos blockPos) {
        return !direction.getAxis().isVertical() && player.mayUseItemAt(blockPos, direction, itemStack);
    }
}

