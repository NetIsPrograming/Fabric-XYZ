package com.NetUserGet.XYZ;


import com.NetUserGet.XYZ.configStuffs.XYZConfig;
import com.NetUserGet.XYZ.hud.XYZHud;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class XYZ implements ClientModInitializer {

	public static final String MOD_ID = "xyz";
	public static String VERSION = "0.1.3";
	public static boolean isInAlpha = true;
	public static boolean isInBeta = false;
	public static boolean isInRelease = false;


	public static final String LOGGER_PREFIX = "["+ MOD_ID.toUpperCase() +"] ";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


	@Override
	public void onInitializeClient() {
		if (isInAlpha) {
			VERSION += "A";
		}
		if (isInBeta) {
			VERSION += "B";
		}
		if (isInRelease) {
			VERSION += "R";
		}

		LOGGER.info(LOGGER_PREFIX + "Initialization has Began");

		AutoConfig.register(XYZConfig.class, GsonConfigSerializer::new);

		XYZHud.register();

		LOGGER.info(LOGGER_PREFIX + "Initialization has Ended");
	}
}
