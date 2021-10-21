package com.genzvirus.theartifact.tools.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;

/**
 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
 * This class is used as an intermediary in order to register all available commands.
 * @author - GenZVirus.
 * @since - Version: 1.0.
 */

public class RegisterCommands {

	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b><p>
	 * Used to register all given commands.
	 * @param commandDispatcher - used for command registration.
	 */
	
	public static void registerCommands(CommandDispatcher<CommandSource> commandDispatcher) {
		MemoPosition.register(commandDispatcher);
		DownloadStructure.register(commandDispatcher);
	}
	
}
