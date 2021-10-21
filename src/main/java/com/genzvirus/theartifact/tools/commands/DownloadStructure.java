package com.genzvirus.theartifact.tools.commands;

import com.genzvirus.theartifact.Config;
import com.genzvirus.theartifact.tools.FileManager;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

/**
 * <b>Created on 12th of October 2021 by GenZVirus.</b>
 * <p>
 * This class creates a commands that writes all blocks types and positions in a
 * file for later use.
 * 
 * @author - GenZVirus.
 * @since - Version: 1.0.
 */

public class DownloadStructure {

	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b>
	 * <p>
	 * Registers the download command.
	 * 
	 * @param dispatcher - used for command registration.
	 */

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("downloadstructure").requires(context -> {
			return Config.COMMON.testerMode.get();
		}).executes(source -> {
			return download(source.getSource().getEntity());
		}));
	}

	/**
	 * <b>Created on 12th of October 2021 by GenZVirus.</b>
	 * <p>
	 * Analyzes all the blocks between the two positions in the world and writes
	 * them into a file for later use.
	 * 
	 * @param entity - used to retrieve the current world.
	 * @return - returns 1 for success and 0 for failure.
	 */

	private static int download(Entity entity) {
		FileManager.openFileForWriting();
		if (MemoPosition.POS1 == null || MemoPosition.POS2 == null) {
			FileManager.closeFileForWriting();
			return 0;
		}
		BlockPos pos1 = new BlockPos(MemoPosition.POS1);
		BlockPos pos2 = new BlockPos(MemoPosition.POS2);
		int rangeX = Math.abs(pos1.getX() - pos2.getX());
		int rangeY = Math.abs(pos1.getY() - pos2.getY());
		int rangeZ = Math.abs(pos1.getZ() - pos2.getZ());
		int dirX = pos1.getX() < pos2.getX() ? 1 : -1;
		int dirY = pos1.getY() < pos2.getY() ? 1 : -1;
		int dirZ = pos1.getZ() < pos2.getZ() ? 1 : -1;

		for (int x = 0; x < rangeX; x++) {
			for (int y = 0; y < rangeY; y++) {
				for (int z = 0; z < rangeZ; z++) {
					BlockPos blockPos = new BlockPos(dirX * x + pos1.getX(), dirY * y + pos1.getY(), dirZ * z + pos1.getZ());
					String name = entity.level.getBlockState(blockPos).getBlock().getName().getString();
					String line = name + " " + x + " " + y + " " + z + '\n';
					if (!FileManager.canWriteLineToFile(line)) {
						FileManager.closeFileForWriting();
						return 0;
					}
				}
			}
		}
		FileManager.closeFileForWriting();
		return 1;
	}

}
