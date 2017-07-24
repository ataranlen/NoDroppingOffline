package com.minetexas.nodroppingoffline;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.minetexas.util.MTLog;

public class NoDroppingOffline extends JavaPlugin {
	private ProtocolManager protocolManager;

	@Override
	public void onEnable() {
		MTLog.init(this);
		MTLog.info("Enabled");
		final PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new DropListener(), this);
	    protocolManager = ProtocolLibrary.getProtocolManager();
	    protocolManager.addPacketListener(
	    		  new PacketAdapter(this, ListenerPriority.NORMAL, 
	    		          PacketType.Play.Client.AUTO_RECIPE) {
	    		    @Override
	    		    public void onPacketReceiving(PacketEvent event) {
	    		        // Item packets (id: 0x29)
	    		        if (event.getPacketType() == 
	    		                PacketType.Play.Client.AUTO_RECIPE ) {
	    		            event.setCancelled(true);
                                    // Instead of sending a transaction packet, we can do this instead!
                                    // (I couldn't get sending a transaction packet with false, this this will have to do :( )
                                    event.getPlayer().updateInventory();
	    		        }
	    		    }
	    		});

	}
	
	
	@Override
	public void onDisable() {
		MTLog.info("Disabled");
	}

	
	public boolean hasPlugin(String name) {
		Plugin p;
		p = getServer().getPluginManager().getPlugin(name);
		return (p != null);
	}
	
}
