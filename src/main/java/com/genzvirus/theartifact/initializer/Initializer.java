package com.genzvirus.theartifact.initializer;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.genzvirus.theartifact.Config;
import com.genzvirus.theartifact.TheArtifactMod;
import com.genzvirus.theartifact.blocks.StairsBlock;
import com.genzvirus.theartifact.items.WandOfEmpowerment;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
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
 * <b>Last update on 29th of October 2021 by GenZVirus.</b><p>
 * This Initializer is used to create and register objects the player can interact with such as blocks, items and entities.
 * @author - GenZVirus.
 * @since - Version: 1.0.
 */

public class Initializer {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheArtifactMod.MOD_ID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheArtifactMod.MOD_ID);
	private static final HashMap<String, RegistryObject<Block>> SIMPLE_BLOCK_REGISTRY = new HashMap<String, RegistryObject<Block>>();
	private static final HashMap<String, RegistryObject<Block>> COMPLEX_BLOCK_REGISTRY = new HashMap<String, RegistryObject<Block>>();
	private static final HashMap<String, RegistryObject<Item>> SIMPLE_ITEM_REGISTRY = new HashMap<String, RegistryObject<Item>>();
	private static final HashMap<String, RegistryObject<Item>> HANDHELD_ITEM_REGISTRY = new HashMap<String, RegistryObject<Item>>();
	private static final Logger LOGGER = LogManager.getLogger();

	/** 
	 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 27th of October 2021 by GenZVirus.</b><p>
	 * Creates and registers all blocks inside the method.
	 * @param eventBusIn - This event bus is used to call the register method.
	 */
	
	public static void InitializeBlocks(IEventBus eventBusIn) {
		addSimpleBlockEntry("empowered_stone", new Block(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 1;})));
		addSimpleBlockEntry("empowered_cracked_stone", new Block(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));
		addSimpleBlockEntry("empowered_carved_stone", new Block(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));
		addSimpleBlockEntry("empowered_chiseled_stone_brick", new Block(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));
		addSimpleBlockEntry("empowered_stone_brick", new Block(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 1;})));
		addSimpleBlockEntry("empowered_cracked_stone_brick", new Block(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));
		
		addComplexBlockEntry("empowered_stone_brick_stairs", new StairsBlock(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));
		addComplexBlockEntry("empowered_stone_stairs", new StairsBlock(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));
		addComplexBlockEntry("empowered_cracked_stone_stairs", new StairsBlock(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));
		addComplexBlockEntry("empowered_stone_slab", new SlabBlock(Block.Properties.of(Material.STONE).strength(10.0F, 1000.0F).sound(SoundType.STONE).harvestLevel(3).lightLevel((light)->{return 2;})));		
		
		BLOCKS.register(eventBusIn);
	}
	
	/** 
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 29th of October 2021 by GenZVirus.</b><p>
	 * Creates and registers all items inside the method.
	 * @param eventBusIn - This event bus is used to call the register method.
	 */
	
	public static void InitializeItems(IEventBus eventBusIn) {
		addHandheldItemEntry("wand_of_empowerment", new WandOfEmpowerment(new Item.Properties().durability(100).tab(ItemGroup.TAB_TOOLS)));
		
		ITEMS.register(eventBusIn);
	}

	/** 
	 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 1st of November 2021 by GenZVirus.</b><p>
	 * Add a new block entry to the hash map.
	 * @param nameIn - HashMap key.
	 * @param blockIn - HashMap value.
	 */
	
	private static void addSimpleBlockEntry(String nameIn, Block blockIn) {
		RegistryObject<Block> block = BLOCKS.register(nameIn, () -> blockIn);
		SIMPLE_BLOCK_REGISTRY.put(nameIn, block);
	}
	
	/** 
	 * <b>Created on 1s of November 2021 by GenZVirus.</b><p>
	 * Add a new block entry to the hash map.
	 * @param nameIn - HashMap key.
	 * @param blockIn - HashMap value.
	 */
	
	private static void addComplexBlockEntry(String nameIn, Block blockIn) {
		RegistryObject<Block> block = BLOCKS.register(nameIn, () -> blockIn);
		COMPLEX_BLOCK_REGISTRY.put(nameIn, block);
	}
	
	
	/** 
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 29th of October 2021 by GenZVirus.</b><p>
	 * Add a new item entry to the hash map.
	 * @param nameIn - HashMap key.
	 * @param itemIn - HashMap value.
	 */
	
	private static void addSimpleItemEntry(String nameIn, Item itemIn) {
		RegistryObject<Item> item = ITEMS.register(nameIn, () -> itemIn);
		SIMPLE_ITEM_REGISTRY.put(nameIn, item);
	}
	
	/** 
	 * <b>Created on 29th of October 2021 by GenZVirus.</b><p>
	 * Add a new item entry to the hash map.
	 * @param nameIn - HashMap key.
	 * @param itemIn - HashMap value.
	 */
	
	private static void addHandheldItemEntry(String nameIn, Item itemIn) {
		RegistryObject<Item> item = ITEMS.register(nameIn, () -> itemIn);
		HANDHELD_ITEM_REGISTRY.put(nameIn, item);
	}
	
	/** 
	 * <b>Created on 4th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 1st of November 2021 by GenZVirus.</b><p>
	 * Retrieve the block by name. The method checks if the name is valid, if it is not it returns an Air block.
	 * @param nameIn - The key used to search and retrieve the registry.
	 * @return - returns the block if the key is valid, otherwise it returns an Air block.
	 */
	public static Block getBlock(String nameIn) {
		Block block = Blocks.AIR;
		RegistryObject<Block> registry = SIMPLE_BLOCK_REGISTRY.get(nameIn);
		if(registry == null) registry = COMPLEX_BLOCK_REGISTRY.get(nameIn);
		if (registry != null)
			block = registry.get();
		else
			LOGGER.error("Invalid block name: " + nameIn);
		return block;
	}
	
	/** 
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 29th of October 2021 by GenZVirus.</b><p>
	 * Retrieve the item by name. The method checks if the name is valid, if it is not it returns an Air item.
	 * @param nameIn - The key used to search and retrieve the registry.
	 * @return - returns the item if the key is valid, otherwise it returns an Air item.
	 */
	public static Item getItem(String nameIn) {
		Item item = Items.AIR;
		RegistryObject<Item> registry = SIMPLE_ITEM_REGISTRY.get(nameIn);
		if(registry == null) registry = HANDHELD_ITEM_REGISTRY.get(nameIn);
		if (registry != null)
			item = registry.get();
		else
			LOGGER.error("Invalid item name: " + nameIn);
		return item;
	}
	
	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 1st of November 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available block values.
	 * @return - a list of blocks.
	 */
	
	public static List<Block> getSimpleBlockRegistryValues(){
		List<Block> list = Lists.newArrayList();
		SIMPLE_BLOCK_REGISTRY.forEach((key, value)->{
			list.add(value.get());
		});
		return list;
	}
	
	/**
	 * <b>Created on 1st of November 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available block values.
	 * @return - a list of blocks.
	 */
	
	public static List<Block> getComplexBlockRegistryValues(){
		List<Block> list = Lists.newArrayList();
		COMPLEX_BLOCK_REGISTRY.forEach((key, value)->{
			list.add(value.get());
		});
		return list;
	}
	
	/**
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 29th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available simple item values.
	 * @return - a list of items.
	 */
	
	public static List<Item> getSimpleItemRegistryValues(){
		List<Item> list = Lists.newArrayList();
		SIMPLE_ITEM_REGISTRY.forEach((key, value)->{
			list.add(value.get());
		});
		return list;
	}
	
	/**
	 * <b>Created on 29th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available hand held item values.
	 * @return - a list of items.
	 */
	
	public static List<Item> getHandheldItemRegistryValues(){
		List<Item> list = Lists.newArrayList();
		HANDHELD_ITEM_REGISTRY.forEach((key, value)->{
			list.add(value.get());
		});
		return list;
	}
	
	/**
	 * <b>Created on 6th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 1st of November 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available block keys.
	 * @return - a list of strings.
	 */
	
	public static List<String> getSimpleBlockRegistryKeys(){
		List<String> list = Lists.newArrayList();
		SIMPLE_BLOCK_REGISTRY.forEach((key, value)->{
			list.add(key);
		});
		return list;
	}
	
	/**
	 * <b>Created on 1st of November 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available block keys.
	 * @return - a list of strings.
	 */
	
	public static List<String> getComplexBlockRegistryKeys(){
		List<String> list = Lists.newArrayList();
		COMPLEX_BLOCK_REGISTRY.forEach((key, value)->{
			list.add(key);
		});
		return list;
	}
	
	/**
	 * <b>Created on 7th of October 2021 by GenZVirus.</b><p>
	 * <b>Last update on 29th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available simple item keys.
	 * @return - a list of strings.
	 */
	
	public static List<String> getSimpleItemRegistryKeys(){
		List<String> list = Lists.newArrayList();
		SIMPLE_ITEM_REGISTRY.forEach((key, value)->{
			list.add(key);
		});
		return list;
	}
	
	/**
	 * <b>Created on 29th of October 2021 by GenZVirus.</b><p>
	 * Create and retrieve a list of all available simple item keys.
	 * @return - a list of strings.
	 */
	
	public static List<String> getHandheldItemRegistryKeys(){
		List<String> list = Lists.newArrayList();
		HANDHELD_ITEM_REGISTRY.forEach((key, value)->{
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
