package com.genzvirus.theartifact.data.client;

import com.genzvirus.theartifact.TheArtifactMod;
import com.genzvirus.theartifact.initializer.Initializer;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
 * Automate model generation for all items created in this software. This includes block items.
 * @author GenZVirus
 */

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, TheArtifactMod.MOD_ID, existingFileHelper);
	}

	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * Retrieve data from registries and build the model files required for in game items.
	 */
	
	@Override
	protected void registerModels() {
		
		// from BLOCKS
		
		Initializer.getBlockRegistryKeys().forEach(key->{
			withExistingParent(key, modLoc("block/" + key));
		});
		
		
		// ITEMS
		
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		
		Initializer.getItemRegistryKeys().forEach(key->{
			build(itemGenerated, key);
		});
	}
	
	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * This method builds the model files required for items using the two parameters as deciding factor and for naming.
	 * @param itemGenerated - decides the type of the item.
	 * @param name - dictates the name of the item.
	 * @return - the item builder.
	 */
	
	private ItemModelBuilder build(ModelFile itemGenerated, String name) {
		return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
	}

}
