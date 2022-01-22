package io.github.bycubed7.daycounter;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.bycubed7.daycounter.commands.Day;
import io.github.bycubed7.daycounter.managers.DayManager;
import io.github.bycubed7.managers.ConfigManager;
import io.github.bycubed7.managers.Debug;

public class Main extends JavaPlugin {

	public static Main instance;

	@Override
	public void onEnable() {
		instance = this;
		new Debug(this);

		// Read config
		Debug.Log("Reading Config..");
		new ConfigManager(this, "default.yml");

		Debug.Log("Setting up Managers..");
		new DayManager(this);

		// Get intergration api / settings
		// Debug.Log("Looking for Compatible Plugins..");

		Debug.Log("Setting up Event Listeners..");
		// Bukkit.getServer().getPluginManager().registerEvents((Listener) new
		// OnLevelup(this), this);

		// Set up commands
		Debug.Log("Setting up Commands..");
		new Day(this);

		// Loading is done!
		Debug.Log(ChatColor.GREEN + "Done!");
		Debug.Banner();

		// Using a schedular so that it runs once ALL plugins are loaded.
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				onStart();
			}
		});
	}

	public void onStart() {
		// Called at the start and after /reload

		Debug.Log("Starting!");

		new BukkitRunnable() {
			@Override
			public void run() {
				DayManager.Tick();
			}
		}.runTaskTimer(this, 0, 20);

		DayManager.sendDayAll();

	}

}
