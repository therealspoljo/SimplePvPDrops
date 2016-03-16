package com.therealspoljo.simplepvpdrops.listeners;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.therealspoljo.simplepvpdrops.Main;
import com.therealspoljo.simplepvpdrops.utilities.ConfigUtils;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
	if (event.getDrops().isEmpty()) {
	    return;
	}

	final Player player = event.getEntity();

	if (!ConfigUtils.isWorldEnabled("death", player.getWorld().getName())) {
	    return;
	}

	if (!ConfigUtils.shouldRemoveOn("death")) {
	    return;
	}

	long removeDelay = ConfigUtils.getDelay("death");

	for (int i = 0; i < event.getDrops().size(); i++) {
	    ItemStack itemStack = event.getDrops().get(i);

	    final Item item = player.getWorld().dropItemNaturally(player.getLocation(), itemStack);

	    new BukkitRunnable() {
		@Override
		public void run() {
		    if (item == null || item.isDead()) {
			return;
		    }

		    item.remove();
		    item.getWorld().playEffect(item.getLocation(), Effect.SMOKE, 1);
		    item.getWorld().playSound(item.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		}
	    }.runTaskLater(Main.getInstance(), 20 * removeDelay);
	}

	event.getDrops().clear();
    }
}