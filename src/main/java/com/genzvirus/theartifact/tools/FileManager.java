package com.genzvirus.theartifact.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileManager {

	private static final Logger LOGGER = LogManager.getLogger();
	private static FileOutputStream OUTPUT;
	private static FileInputStream INPUT;
	
	public static void setupDirectory() {
		File folder = new File("The Artifact/");
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				LOGGER.error("Failed to create config directory");
			}
		}
	}
	
	public static void openFileForWriting() {
		try {
			OUTPUT = new FileOutputStream("The Artifact/structure.dat");
		} catch (FileNotFoundException e) {
			LOGGER.error("Error encountered when opening the file.");
			e.printStackTrace();
		}
	}
	
	public static void closeFileForWriting() {
		try {
			OUTPUT.close();
		} catch (IOException e) {
			LOGGER.error("Error encountered when closing the file.");
			e.printStackTrace();
		}
	}
	
	public static boolean canWriteLineToFile(String line) {
		boolean canWrite = true;
		try {
			OUTPUT.write(line.getBytes());
		} catch (IOException e) {
			canWrite = false;
			LOGGER.error("Error encountered when writing a line to the file.");
			e.printStackTrace();
		}
		return canWrite;
	}
	
	public static void openFileForReading() {
		try {
			INPUT = new FileInputStream("The Artifact/structure.dat");
		} catch (FileNotFoundException e) {
			LOGGER.error("Error encountered when opening the file.");
			e.printStackTrace();
		}
	}
	
	public static void closeFileForReading() {
		try {
			INPUT.close();
		} catch (IOException e) {
			LOGGER.error("Error encountered when closing the file.");
			e.printStackTrace();
		}
	}
	
	public static boolean canReadLineFromFile(String line) {
		boolean canWrite = true;
		try {
			byte[] b = null;
			INPUT.read(b);
			String string = new String(b);
		} catch (IOException e) {
			canWrite = false;
			LOGGER.error("Error encountered when writing a line to the file.");
			e.printStackTrace();
		}
		return canWrite;
	}
	
}
