package com.minetexas.nodroppingoffline;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;

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
	
	//Prevent players from Moving items to or from a chest after it is broken.
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInventoryClickEvent(InventoryClickEvent e) {
		Inventory source = e.getInventory();
		if (source.getType() == InventoryType.ENDER_CHEST) {
			return;
		}
		
		if (source.getHolder() == null) {
			MTLog.debug(e.getWhoClicked().getName() +" source null clicked item from invalid inventory.");
			e.setCancelled(true);
		}
	}
	
}
