package com.therealspoljo.simplepvpdrops.utilities;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.therealspoljo.simplepvpdrops.Main;

public class Utils {

    public static String colorize(String string) {
	return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static boolean canBeDropped(ItemStack itemStack) {
	if (itemStack == null) {
	    return false;
	}

	List<String> droppable = Main.getInstance().getDroppableItems();

	if (droppable == null || droppable.isEmpty()) {
	    return false;
	}

	for (String item : droppable) {
	    String[] item_ = item.split(":");

	    if (item_[0].isEmpty() || item_[0].equalsIgnoreCase("")) {
		return false;
	    }

	    if (item_.length == 1 && item_[0].equalsIgnoreCase(itemStack.getType().name())) {
		return true;
	    }

	    if (item_.length == 2 && item_[0].equalsIgnoreCase(itemStack.getType().name())) {
		if (item_[1].equalsIgnoreCase("all")) {
		    return true;
		} 

		if (item_[1].equalsIgnoreCase(String.valueOf(itemStack.getDurability()))) {
		    return true;
		}

		return false;
	    }
	}

	return false;
    }
}