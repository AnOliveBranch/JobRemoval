package me.roboticplayer.jobremoval;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JobRemoval extends JavaPlugin implements Listener {

	private FileConfiguration config;

	@Override
	public void onEnable() {
		config = getConfig();
		saveDefaultConfig();
		if (getServer().getPluginManager().getPlugin("Jobs") == null && getServer().getPluginManager().getPlugin("jobs") == null)
			getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("JobRemoval has been enabled");
	}

	@Override
	public void onDisable() {
		getLogger().info("JobRemoval has been disabled");
	}

	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage().toLowerCase();
		if (message.startsWith("/jobs") || message.startsWith("/job")) {
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message")));
			e.setCancelled(true);
		}
	}

}
