package com.genzvirus.theartifact.initializer;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.genzvirus.theartifact.Config;
import com.genzvirus.theartifact.TheArtifactMod;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
 * This Initializer is used to create and register objects the player can interact with such as blocks, items and entities.
 * @author - GenZVirus.
 * @since - Version: 1.0.
 */

public class Initializer {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheArtifactMod.MOD_ID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheArtifactMod.MOD_ID);
	private static final HashMap<String, RegistryObject<Block>> BLOCK_REGISTRY = new HashMap<String, RegistryObject<Block>>();
	private static final HashMap<String, RegistryObject<Item>> ITEM_REGISTRY = new HashMap<String, RegistryObject<Item>>();
	private static final Logger LOGGER = LogManager.getLogger();

	/** 
	 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
	 * Creates and registers all blocks inside the method.
	 * @param eventBusIn - This event bus is used to call the register method.
	 */
	
	public static void InitializeBlocks(IEventBus eventBusIn) {
		addBlockEntry("wall", new Block(Block.Properties.of(Material.METAL).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3)));
		
		BLOCKS.register(eventBusIn);
	}
	
	/** 
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * Creates and registers all items inside the method.
	 * @param eventBusIn - This event bus is used to call the register method.
	 */
	
	public static void InitializeItems(IEventBus eventBusIn) {
		addItemEntry("example_item", new Item(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
		
		ITEMS.register(eventBusIn);
	}

	/** 
	 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
	 * Add a new block entry to the hash map.
	 * @param nameIn - HashMap key.
	 * @param blockIn - HashMap value.
	 */
	
	private static void addBlockEntry(String nameIn, Block blockIn) {
		RegistryObject<Block> block = BLOCKS.register(nameIn, () -> blockIn);
		BLOCK_REGISTRY.put(nameIn, block);
	}
	
	/** 
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * Add a new item entry to the hash map.
	 * @param nameIn - HashMap key.
	 * @param itemIn - HashMap value.
	 */
	
	private static void addItemEntry(String nameIn, Item itemIn) {
		RegistryObject<Item> item = ITEMS.register(nameIn, () -> itemIn);
		ITEM_REGISTRY.put(nameIn, item);
	}
	
	/** 
	 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
	 * Retrieve the block by name. The method checks if the name is valid, if it is not it returns an Air block.
	 * @param nameIn - The key used to search and retrieve the registry.
	 * @return - returns the block if the key is valid, otherwise it returns an Air block.
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
	
	/** 
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * Retrieve the item by name. The method checks if the name is valid, if it is not it returns an Air item.
	 * @param nameIn - The key used to search and retrieve the registry.
	 * @return - returns the item if the key is valid, otherwise it returns an Air item.
	 */
	public static Item getItem(String nameIn) {
		Item item = Items.AIR;
		RegistryObject<Item> registry = ITEM_REGISTRY.get(nameIn);
		if (registry != null)
			item = registry.get();
		else
			LOGGER.error("Invalid item name: " + nameIn);
		return item;
	}
	
	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available block values.
	 * @return - a list of blocks.
	 */
	
	public static List<Block> getBlockRegistryValues(){
		List<Block> list = Lists.newArrayList();
		BLOCK_REGISTRY.forEach((key, value)->{
			list.add(value.get());
		});
		return list;
	}
	
	/**
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available item values.
	 * @return - a list of items.
	 */
	
	public static List<Item> getItemRegistryValues(){
		List<Item> list = Lists.newArrayList();
		ITEM_REGISTRY.forEach((key, value)->{
			list.add(value.get());
		});
		return list;
	}
	
	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available block keys.
	 * @return - a list of strings.
	 */
	
	public static List<String> getBlockRegistryKeys(){
		List<String> list = Lists.newArrayList();
		BLOCK_REGISTRY.forEach((key, value)->{
			list.add(key);
		});
		return list;
	}
	
	/**
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available item keys.
	 * @return - a list of strings.
	 */
	
	public static List<String> getItemRegistryKeys(){
		List<String> list = Lists.newArrayList();
		ITEM_REGISTRY.forEach((key, value)->{
			list.add(key);
		});
		return list;
	}
	
	/**
	 * <b>Created on 8th of October 2021 by GenZVirus.</b><p>
	 * This method creates the configuration folder and the configuration file of the application.
	 */
	
	public static void setupConfig() {
		File folder = new File("config/The Artifact/");
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				LOGGER.debug("Failed to create config directory");
			}
		}
		ModLoadingContext.get().registerConfig(Type.COMMON, Config.COMMON_SPEC, "The Artifact/Configs.toml");
	}

}
