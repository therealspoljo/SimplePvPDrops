package com.therealspoljo.simplepvpdrops.utilities;

import com.therealspoljo.simplepvpdrops.Main;

public class ConfigUtils {

    public static boolean shouldDenyDrops() {
	return Main.getInstance().getConfig().getBoolean("deny-drops", false);
    }

    public static boolean shouldRemoveOn(String type) {
	return Main.getInstance().getConfig().getBoolean(type + ".enabled", false);
    }

    public static long getDelay(String type) {
	return Main.getInstance().getConfig().getLong(type + ".delay", 5);
    }

    public static boolean isWorldEnabled(String type, String worldName) {
	for (String name : Main.getInstance().getConfig().getStringList(type + ".enabled-worlds")) {
	    if (name.equalsIgnoreCase(worldName)) {
		return true;
	    }
	}

	return false;
    }
}