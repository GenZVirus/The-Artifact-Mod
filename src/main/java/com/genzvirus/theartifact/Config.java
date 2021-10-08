package com.genzvirus.theartifact;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = TheArtifactMod.MOD_ID, bus = Bus.MOD)
public abstract class Config {

	public static class Client {

	}

	public static class Common {

		public final BooleanValue testerMode;

		public Common(ForgeConfigSpec.Builder builder) {

			// Active Abilities

			builder.push("The Artifact");

			testerMode = builder.worldRestart().comment("Set Tester Mode to true if you want to enable tester commands").define("Tester Mode", true);
			builder.pop();
		}

	}

	public static class Server {

		public Server(ForgeConfigSpec.Builder builder) {

		}

	}

//	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final ForgeConfigSpec COMMON_SPEC;
//	public static final ForgeConfigSpec SERVER_SPEC;
//	public static final Client CLIENT;
	public static final Common COMMON;
//	public static final Server SERVER;

	static {
//		final Pair<Client, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(Client::new);
//		CLIENT_SPEC = specPairClient.getRight();
//		CLIENT = specPairClient.getLeft();
		final Pair<Common, ForgeConfigSpec> specPairCommon = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPairCommon.getRight();
		COMMON = specPairCommon.getLeft();
//		final Pair<Server, ForgeConfigSpec> specPairServer = new ForgeConfigSpec.Builder().configure(Server::new);
//		SERVER_SPEC = specPairServer.getRight();
//		SERVER = specPairServer.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading event) {
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading event) {

	}

}
