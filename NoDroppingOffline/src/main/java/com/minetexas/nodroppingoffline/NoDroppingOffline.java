package com.minetexas.nodroppingoffline;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.minetexas.util.MTLog;

public class NoDroppingOffline extends JavaPlugin {
	@Override
	public void onEnable() {
		MTLog.init(this);
		MTLog.info("Enabled");
		final PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new DropListener(), this);


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
