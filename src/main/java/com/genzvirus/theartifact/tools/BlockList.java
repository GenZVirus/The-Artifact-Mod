package com.genzvirus.theartifact.tools;

import java.util.HashMap;

import net.minecraft.block.Block;

/**
 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
 * This class creates a collection of blocks to be retrieved by name.
 * @author - GenZVirus.
 * @since - Version: 1.0.
 */

public class BlockList {

	/**
	 * A collection of blocks mapped by their name.
	 */
	
	private static final HashMap<String, Block> BLOCK_MAP = new HashMap<>();
	
	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * Initialization of the collection of blocks.
	 */
	
	@SuppressWarnings("deprecation")
	public static void init() {
		Block.BLOCK_STATE_REGISTRY.forEach((block)->{
			BLOCK_MAP.put(block.getBlock().getName().getString(), block.getBlock());
		});
	}
	
	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * Retrieve a block by name from the collection of blocks.
	 * @param name - a string used to retrieve the block by it's name.
	 * @return - gives back the block requested.
	 */
	
	public static Block getBlockByName(String name) {
		return BLOCK_MAP.get(name);
	}
	
}
