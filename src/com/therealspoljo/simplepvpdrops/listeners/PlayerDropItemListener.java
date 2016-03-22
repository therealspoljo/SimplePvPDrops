package com.therealspoljo.simplepvpdrops.listeners;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.therealspoljo.simplepvpdrops.Main;
import com.therealspoljo.simplepvpdrops.enums.Lang;
import com.therealspoljo.simplepvpdrops.utilities.ConfigUtils;
import com.therealspoljo.simplepvpdrops.utilities.Utils;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
	if (event.isCancelled()) {
	    return;
	}

	Player player = event.getPlayer();

	if (ConfigUtils.shouldDenyDrops() && !Utils.canBeDropped(event.getItemDrop().getItemStack())) {
	    event.setCancelled(true);
	    Lang.DENY_DROPS.send(player);
	    return;
	}

	if (!ConfigUtils.isWorldEnabled("drop", player.getWorld().getName())) {
	    return;
	}

	if (!ConfigUtils.shouldRemoveOn("drop")) {
	    return;
	}

	if (event.getItemDrop().getItemStack().getType() == Material.AIR) {
	    return;
	}

	new BukkitRunnable() {
	    @Override
	    public void run() {
		Item item = event.getItemDrop();
		
		if (item == null || item.isDead()) {
		    return;
		}

		event.getItemDrop().remove();
		event.getItemDrop().getWorld().playEffect(event.getItemDrop().getLocation(), Effect.SMOKE, 1);
		event.getItemDrop().getWorld().playSound(event.getItemDrop().getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
	    }
	}.runTaskLater(Main.getInstance(), 20 * ConfigUtils.getDelay("drop"));
    }
}