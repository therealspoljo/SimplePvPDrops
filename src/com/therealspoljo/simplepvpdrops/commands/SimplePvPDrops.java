package com.therealspoljo.simplepvpdrops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.therealspoljo.simplepvpdrops.Main;
import com.therealspoljo.simplepvpdrops.enums.Lang;
import com.therealspoljo.simplepvpdrops.enums.Permissions;

public class SimplePvPDrops implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	if (!Permissions.ADMIN.isAllowed(sender)) {
	    Lang.NO_PERMISSION.send(sender);
	    return true;
	}

	if (args.length == 1) {
	    switch (args[0].toLowerCase()) {
	    case "reload":
	    case "rl":
		Main.getInstance().getConfig().reload();
		Main.getInstance().getLangConfig().reload();
		Main.getInstance().reloadDroppableItems();
		Lang.CONFIGS_RELOADED.send(sender);
		return true;
	    }

	    sender.sendMessage("§cUsage: §7" + command.getUsage().replaceAll("<command>", commandLabel));
	    return true;
	}

	sender.sendMessage("§cUsage: §7" + command.getUsage().replaceAll("<command>", commandLabel));
	return true;
    }
}