package com.minetexas.nodroppingoffline;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.minetexas.util.MTLog;

public class DropListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropEvent(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (!player.isOnline()) {
			MTLog.debug(player.getDisplayName() +" dropped item while offline!");
			e.setCancelled(true);
			
		}
	}
	
}
