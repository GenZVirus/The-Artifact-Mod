package com.genzvirus.theartifact.data.client;

import com.genzvirus.theartifact.TheArtifactMod;
import com.genzvirus.theartifact.blocks.StairsBlock;
import com.genzvirus.theartifact.initializer.Initializer;

import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, TheArtifactMod.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		Initializer.getSimpleBlockRegistryValues().forEach(block -> {
			simpleBlock(block);
		});

		Initializer.getComplexBlockRegistryValues().forEach(block -> {
			if (block instanceof StairsBlock)
				stairsBlock((StairsBlock) block, modLoc("block/" + block.getRegistryName().getPath().replace("_stairs", "")));
			else if (block instanceof SlabBlock) {
				ResourceLocation resource = modLoc("block/" + block.getRegistryName().getPath().replace("_slab", ""));
				slabBlock((SlabBlock) block, modLoc("block/" + block.getRegistryName().getPath()), resource);
			}
		});
	}

	public void stairsBlock(StairsBlock block, ResourceLocation texture) {
		stairsBlock(block, texture, texture, texture);
	}

	public void stairsBlock(StairsBlock block, String name, ResourceLocation texture) {
		stairsBlock(block, name, texture, texture, texture);
	}

	public void stairsBlock(StairsBlock block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		stairsBlockInternal(block, block.getRegistryName().toString(), side, bottom, top);
	}

	public void stairsBlock(StairsBlock block, String name, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		stairsBlockInternal(block, name + "_stairs", side, bottom, top);
	}

	private void stairsBlockInternal(StairsBlock block, String baseName, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		ModelFile stairs = models().stairs(baseName, side, bottom, top);
		ModelFile stairsInner = models().stairsInner(baseName + "_inner", side, bottom, top);
		ModelFile stairsOuter = models().stairsOuter(baseName + "_outer", side, bottom, top);
		stairsBlock(block, stairs, stairsInner, stairsOuter);
	}

	public void stairsBlock(StairsBlock block, ModelFile stairs, ModelFile stairsInner, ModelFile stairsOuter) {
		getVariantBuilder(block).forAllStatesExcept(state -> {
			Direction facing = state.getValue(StairsBlock.FACING);
			Half half = state.getValue(StairsBlock.HALF);
			StairsShape shape = state.getValue(StairsBlock.SHAPE);
			int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some reason
			if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
				yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
			}
			if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
				yRot += 90; // Top stairs are rotated 90 degrees clockwise
			}
			yRot %= 360;
			boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
			return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT ? stairs : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter)
					.rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).uvLock(uvlock).build();
		}, StairsBlock.WATERLOGGED);
	}

}
