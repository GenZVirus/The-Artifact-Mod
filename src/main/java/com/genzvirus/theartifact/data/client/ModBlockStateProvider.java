package com.genzvirus.theartifact.data.client;

import com.genzvirus.theartifact.TheArtifactMod;
import com.genzvirus.theartifact.initializer.Initializer;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, TheArtifactMod.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		Initializer.getBlockRegistryValues().forEach(block->{
			simpleBlock(block);
		});
	}

}
