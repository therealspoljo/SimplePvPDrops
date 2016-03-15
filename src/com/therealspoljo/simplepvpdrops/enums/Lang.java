package com.therealspoljo.simplepvpdrops.enums;

import org.bukkit.command.CommandSender;

import com.therealspoljo.simplepvpdrops.Main;
import com.therealspoljo.simplepvpdrops.utilities.Utils;

public enum Lang {

    TITLE("title", "&7[&eSPvPDrops&7]&r "),
    NO_PERMISSION("no-permission", "&cYou don't have permission to perform this action."),
    DENY_DROPS("deny-drops", "&cYou are not allowed to drop items."),
    CONFIGS_RELOADED("configs-reloaded", "&7Configs have been reloaded.");

    private String message;

    Lang(String path, String defaultMessage) {
	this.message = Main.getInstance().getLangConfig().getString(path, defaultMessage);
    }

    public String getMessage() {
	return message;
    }

    @Override
    public String toString() {
	return Utils.colorize(TITLE.getMessage()) + Utils.colorize(getMessage());
    }

    public void send(CommandSender sender) {
	sender.sendMessage(this.toString());
    }
}