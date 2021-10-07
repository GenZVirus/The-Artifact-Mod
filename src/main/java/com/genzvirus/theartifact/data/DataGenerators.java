package com.genzvirus.theartifact.data;

import com.genzvirus.theartifact.TheArtifactMod;
import com.genzvirus.theartifact.data.client.ModBlockStateProvider;
import com.genzvirus.theartifact.data.client.ModItemModelProvider;
import com.genzvirus.theartifact.initializer.Initializer;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
 * Automate model generation for all objects created in this software.
 * @author GenZVirus.
 */

@Mod.EventBusSubscriber(modid = TheArtifactMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * Gather data for each object in the registries such as blocks, items and entities.
	 * @author GenZVirus.
	 */
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		gen.addProvider(new ModBlockStateProvider(gen, existingFileHelper));
		gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));
	}

	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * Create item variant for each block in the registry.
	 * @author GenZVirus.gith
	 */
	@SubscribeEvent
	public static void onBlocksRegistry(final RegistryEvent.Register<Item> blockRegistryEvent) {
		final IForgeRegistry<Item> registry = blockRegistryEvent.getRegistry();

		Initializer.getBlockRegistryValues().forEach(block -> {
			final Item.Properties properties = new Item.Properties();
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}
}
