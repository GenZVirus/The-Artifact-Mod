package com.genzvirus.theartifact.tools.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;

public class RegisterCommands {

	public static void registerCommands(CommandDispatcher<CommandSource> commandDispatcher) {
		MemoPosition.register(commandDispatcher);
	}
	
}
