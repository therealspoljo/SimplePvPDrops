package com.therealspoljo.simplepvpdrops.enums;

import org.bukkit.command.CommandSender;

public enum Permissions {

    ADMIN("simplepvpdrops.admin");

    private final String node;

    Permissions(String node) {
	this.node = node;
    }

    public String getNode() {
	return node;
    }

    public boolean isAllowed(CommandSender sender) {
	return sender.isOp() || sender.hasPermission(this.getNode());
    }

    public static String getNode(Permissions type) {
	return type.toString();
    }
}