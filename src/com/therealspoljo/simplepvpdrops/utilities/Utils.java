package com.therealspoljo.simplepvpdrops.utilities;

import org.bukkit.ChatColor;

public class Utils {

    public static String colorize(String string) {
	return ChatColor.translateAlternateColorCodes('&', string);
    }
}