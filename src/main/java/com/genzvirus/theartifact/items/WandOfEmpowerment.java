package com.genzvirus.theartifact.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.genzvirus.theartifact.initializer.Initializer;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;

/**
 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
 * This is the class of Wand of Empowerment item. It's purpose it to empower certain blocks.
 * @author - GenZVirus
 * @since - Version: 1.0
 */

public class WandOfEmpowerment extends Item {

	private static BlockState newState;
	
	// Logger - used for logging purposes.
	private static final Logger LOGGER = LogManager.getLogger();

	// Constructor - implemented because the compiler asked for it. 	
	public WandOfEmpowerment(Properties p_i48487_1_) {
		super(p_i48487_1_);
	}
	
	// When clicking on blocks, the wand consumes durability to empower the targeted block.
	// Blocks get empowered if they have an empowered version.
	// KNOWN ISSUE: first corner changed from a chain will not keep it's scorner shape.
	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

		if (context.getLevel().isClientSide()) {
			return ActionResultType.FAIL;
		}

		BlockState oldState = context.getLevel().getBlockState(context.getClickedPos());
		String name = "empowered_" + oldState.getBlock().getRegistryName().getPath().replace("bricks", "brick");
		
		if (!Initializer.getBlock(name).equals(Blocks.AIR) && stack.getDamageValue() < 100) {
			
			newState = Initializer.getBlock(name).defaultBlockState();
			
			if(oldState.hasProperty(HorizontalBlock.FACING)) {
				newState = newState.setValue(HorizontalBlock.FACING, oldState.getValue(HorizontalBlock.FACING));
			}
			
			if(oldState.hasProperty(BlockStateProperties.HALF)) {
				newState = newState.setValue(BlockStateProperties.HALF, oldState.getValue(BlockStateProperties.HALF));
			}
			
			if(oldState.hasProperty(BlockStateProperties.STAIRS_SHAPE)) {
				newState = newState.setValue(BlockStateProperties.STAIRS_SHAPE, oldState.getValue(BlockStateProperties.STAIRS_SHAPE));
			}
			
			if(oldState.hasProperty(BlockStateProperties.WATERLOGGED)) {
				newState = newState.setValue(BlockStateProperties.WATERLOGGED, oldState.getValue(BlockStateProperties.WATERLOGGED));
			}
			
			stack.setDamageValue(stack.getDamageValue() + 1);
			context.getLevel().setBlock(context.getClickedPos(), newState, 0);
			return ActionResultType.PASS;
		} else {
			LOGGER.error("Block " + name + " does not exist.");
			return ActionResultType.FAIL;
		}
	}

	// Set to true to display the durability of the wand.
	@Override
	public boolean isDamaged(ItemStack stack) {
		return true;
	}

	// The wand executes the entity if it below 25% health and add a charge to the wand.
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		LivingEntity livingEntity;
		if (entity instanceof LivingEntity) {
			livingEntity = (LivingEntity) entity;
		} else {
			return true;
		}

		if (livingEntity.getHealth() / livingEntity.getMaxHealth() <= 0.25f && stack.getDamageValue() > 0) {
			livingEntity.kill();
			stack.setDamageValue(stack.getDamageValue() - 1);
			return true;
		} else {
			return false;
		}
	}

}
