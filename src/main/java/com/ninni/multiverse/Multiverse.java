package com.ninni.multiverse;

import com.google.common.reflect.Reflection;
import com.ninni.multiverse.init.MultiverseBlocks;
import com.ninni.multiverse.entities.ExhaustedCobblestoneGolem;
import com.ninni.multiverse.init.MultiverseCreativeModeTabs;
import com.ninni.multiverse.init.MultiverseEntityTypes;
import com.ninni.multiverse.init.MultiversePaintingVariants;
import com.ninni.multiverse.init.MultiverseItems;
import com.ninni.multiverse.init.MultiverseSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

public class Multiverse implements ModInitializer {
	public static final String MOD_ID = "multiverse";
//	public static final CreativeModeTab TAB = FabricItemGroupBuilder.create(new ResourceLocation(MOD_ID, MOD_ID)).icon(() -> new ItemStack(MultiverseItems.STONE_TILES)).build();

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		Reflection.initialize(
			MultiverseSoundEvents.class,
			MultiverseEntityTypes.class,
			MultiverseBlocks.class,
			MultiverseItems.class,
			MultiverseCreativeModeTabs.class,
			MultiversePaintingVariants.class
		);
		DispenserBlock.registerBehavior(MultiverseItems.EXHAUSTED_COBBLESTONE_GOLEM, new DefaultDispenseItemBehavior(){
			@Override
			public ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
				Direction direction = blockSource.getBlockState().getValue(DispenserBlock.FACING);
				BlockPos blockPos = blockSource.getPos().relative(direction);
				ServerLevel level = blockSource.getLevel();
				ExhaustedCobblestoneGolem golem = new ExhaustedCobblestoneGolem(level, (double)blockPos.getX() + 0.5, blockPos.getY(), (double)blockPos.getZ() + 0.5);
				EntityType.updateCustomEntityTag(level, null, golem, itemStack.getTag());
				golem.setYRot(direction.toYRot());
				level.addFreshEntity(golem);
				itemStack.shrink(1);
				return itemStack;
			}
		});
	}
}
