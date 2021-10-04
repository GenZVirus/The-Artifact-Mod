package com.genzvirus.theartifact.initializer;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.genzvirus.theartifact.TheArtifactMod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
 * This Initializer is used to create and register objects the player can interact with such as blocks, items and entities.
 * @author - GenZVirus
 * @since - Version: 1.0
 */

public class Initializer {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheArtifactMod.getModId());
	private static final HashMap<String, RegistryObject<Block>> BLOCK_REGISTRY = new HashMap<String, RegistryObject<Block>>();
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Creates and registers all blocks inside the method
	 * @param eventBusIn - This event bus is used to call the register method
	 */
	
	public static void InitializeBlocks(IEventBus eventBusIn) {
		addBlockEntry("ExampleBlock", new Block(Block.Properties.of(Material.METAL).strength(10.0F, 1000.0F).sound(SoundType.METAL).harvestLevel(3)));

		BLOCKS.register(eventBusIn);
	}

	/**
	 * Add a new entry to the hash map
	 * @param nameIn - HashMap key
	 * @param blockIn - HashMap value
	 */
	
	private static void addBlockEntry(String nameIn, Block blockIn) {
		RegistryObject<Block> block = BLOCKS.register(nameIn, () -> blockIn);
		BLOCK_REGISTRY.put(nameIn, block);
	}
	
	/**
	 * Retrieve the block by name. The method checks if the name is valid, if it is not it returns an Air block
	 * @param nameIn - The key used to search and retrieve the registry
	 * @return - returns the block if the key is valid, otherwise it returns an Air block
	 */
	public static Block getBlock(String nameIn) {
		Block block = Blocks.AIR;
		RegistryObject<Block> registry = BLOCK_REGISTRY.get(nameIn);
		if (registry != null)
			block = registry.get();
		else
			LOGGER.error("Invalid block name: " + nameIn);
		return block;
	}

}
