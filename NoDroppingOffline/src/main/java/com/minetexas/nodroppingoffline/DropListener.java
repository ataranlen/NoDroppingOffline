package com.minetexas.nodroppingoffline;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.minetexas.util.MTLog;



public class DropListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropEvent(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (!player.isOnline()) {
			e.setCancelled(true);
			
		}
		Item item = e.getItemDrop();

		if (item == null) {
			return;
		}
		ItemStack itemStack = item.getItemStack();

		if (itemStack == null) {
			return;
		}
		if (itemStack.getAmount() >= 65) {
			itemStack.setAmount(1);
			e.setCancelled(true);
			MTLog.debug("PlayerDropItemEvent: stacksize fail");
			return;
		}

		if (checkMat(itemStack.getType())) {

			if (itemStack.getAmount() >= 2) {
			itemStack.setAmount(1);
			MTLog.debug("PlayerDropItemEvent: typeFail");
			e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerItemMoveEvent(InventoryClickEvent e) {
		ItemStack itemStack = e.getCurrentItem();
		if (itemStack == null) {
			return;
		}
		if (itemStack.getAmount() >= 65) {
			itemStack.setAmount(1);
			e.setCancelled(true);
			MTLog.debug("InventoryClickEvent: stacksize fail");
			return;
		}

		if (checkMat(itemStack.getType())) {

			if (itemStack.getAmount() >= 2) {
			itemStack.setAmount(1);
			MTLog.debug("InventoryClickEvent: typeFail");
			e.setCancelled(true);
			}
		}
	}
	

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInvenMove(InventoryMoveItemEvent e) {
		ItemStack itemStack = e.getItem();
		if (itemStack == null) {
			return;
		}
		
		if (itemStack.getAmount() >= 65) {
			itemStack.setAmount(1);
			e.setCancelled(true);
			MTLog.debug("InventoryMoveItemEvent: stacksize fail");
			return;
		}
		if (checkMat(itemStack.getType())) {

			if (itemStack.getAmount() >= 2) {
			itemStack.setAmount(1);
			MTLog.debug("InventoryMoveItemEvent: typeFail");
			e.setCancelled(true);
			}
		}
	
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPickupEvent(PlayerPickupItemEvent e) {
		Item item = e.getItem();
		if (item == null) {
			return;
		}
		ItemStack itemStack = item.getItemStack();
		if (itemStack == null) {
			return;
		}
		
		if (itemStack.getAmount() >= 65) {
			itemStack.setAmount(1);
			e.setCancelled(true);
			MTLog.debug("PlayerPickupItemEvent: stacksize fail");
			return;
		}
		if (checkMat(itemStack.getType())) {

			if (itemStack.getAmount() >= 2) {
			itemStack.setAmount(1);
			MTLog.debug("PlayerPickupItemEvent: typeFail");
			e.setCancelled(true);
			}
		}
	}
	
	public boolean checkMat(Material mat) {

		if (mat == Material.DIAMOND_AXE || 
				mat == Material.DIAMOND_CHESTPLATE ||
				mat == Material.DIAMOND_HELMET ||
				mat == Material.DIAMOND_LEGGINGS ||
				mat == Material.DIAMOND_PICKAXE ||
				mat == Material.DIAMOND_SWORD ||
				mat == Material.DIAMOND_HELMET ||
				mat == Material.DIAMOND_SPADE ||
				mat == Material.BLACK_SHULKER_BOX ||
				mat == Material.BLUE_SHULKER_BOX ||
				mat == Material.BROWN_SHULKER_BOX ||
				mat == Material.CYAN_SHULKER_BOX ||
				mat == Material.GRAY_SHULKER_BOX ||
				mat == Material.GREEN_SHULKER_BOX ||
				mat == Material.LIGHT_BLUE_SHULKER_BOX ||
				mat == Material.LIME_SHULKER_BOX ||
				mat == Material.MAGENTA_SHULKER_BOX ||
				mat == Material.ORANGE_SHULKER_BOX ||
				mat == Material.PINK_SHULKER_BOX ||
				mat == Material.PURPLE_SHULKER_BOX ||
				mat == Material.RED_SHULKER_BOX ||
				mat == Material.SILVER_SHULKER_BOX ||
				mat == Material.WHITE_SHULKER_BOX ||
				mat == Material.YELLOW_SHULKER_BOX ||
				mat == Material.TOTEM ||
				mat == Material.SKULL_ITEM) {
			
				
			return true;
		}
		return false;
	}
			
	
	//Prevent players from Moving items to or from a chest after it is broken.
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInventoryClickEvent(InventoryClickEvent e) {
		Inventory source = e.getInventory();
		if (source.getType() != InventoryType.CHEST) {
			return;
		}
		
		if (source.getHolder() == null) {
			e.setCancelled(true);
		}
	}
	

	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (player.isInsideVehicle()) {
				player.leaveVehicle();
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
			Player player = (Player) e.getPlayer();
			if (player.isInsideVehicle()) {
				player.leaveVehicle();
		}
	}
		
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageEvent(EntityDamageEvent e) {
		if (e.getCause().equals(DamageCause.SUFFOCATION)) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				if (player.isInsideVehicle() && player.getHealth() <= 1.0) {
					player.leaveVehicle();
//					e.setCancelled(true);
				}
			}
		}
	}
//		EntityType source = e.getEntityType();
//		if (source == EntityType.HORSE) {
//			Entity en = e.getEntity();
//			if (en instanceof Mule) {
//				Mule donkey = (Mule) en;
//				for (ItemStack i : donkey.getInventory().getContents()) {
//					if (i != null) {
//						donkey.setHealth(donkey.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
////						e.setCancelled(true);
//						MTLog.debug("Donkey with inventory heal on portal entry");
//						return;
//					}
//				}
//			}
//		}
//	}
	
}
