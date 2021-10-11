package com.genzvirus.theartifact.tools.commands;

import com.genzvirus.theartifact.Config;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;

public class MemoPosition {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setpos1").requires(context->{
			return Config.COMMON.testerMode.get();
		}).executes(source->{
			return execute(source.getSource().getEntity());
		}));
	}
	
	private static int execute(Entity entity) {
		System.out.println(entity.getName().getContents());
		return 1;
	}
	
}
