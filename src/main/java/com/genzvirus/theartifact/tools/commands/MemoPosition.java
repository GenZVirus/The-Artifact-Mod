package com.genzvirus.theartifact.tools.commands;

import com.genzvirus.theartifact.Config;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

/**
 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
 * This class creates two commands that memorizes two positions on the world.
 * @author - GenZVirus.
 * @since - Version: 1.0.
 */

public class MemoPosition {

	/**
	 * Global variables used to hold two positions in the world.
	 */
	
	public static BlockPos POS1, POS2;
	
	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * Registers all commands created in this class.
	 * @param dispatcher
	 */
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		registerSetPos1(dispatcher);
		registerSetPos2(dispatcher);
	}
	
	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * Registers a command that memorizes the first position.
	 * @param dispatcher
	 */
	
	public static void registerSetPos1(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setpos1").requires(context->{
			return Config.COMMON.testerMode.get();
		}).executes(source->{
			return setPos1(source.getSource().getEntity());
		}));
	}
	
	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * This method set the value of Pos1.
	 * @param entity 
	 * @return
	 */
	
	private static int setPos1(Entity entity) {
		POS1 = new BlockPos(entity.blockPosition());
		return 1;
	}
	
	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * Registers a command that memorizes the second position.
	 * @param dispatcher
	 */
	
	public static void registerSetPos2(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setpos2").requires(context->{
			return Config.COMMON.testerMode.get();
		}).executes(source->{
			return setPos2(source.getSource().getEntity());
		}));
	}
	
	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * This method set the value of Pos2.
	 * @param entity - player entity, used to get the current position of the player.
	 * @return
	 */
	
	private static int setPos2(Entity entity) {
		POS2 = new BlockPos(entity.blockPosition());
		return 1;
	}
}
