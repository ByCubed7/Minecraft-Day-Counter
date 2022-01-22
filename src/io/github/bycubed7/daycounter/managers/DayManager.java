package io.github.bycubed7.daycounter.managers;

import java.util.Random;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.bycubed7.managers.ConfigManager;

public class DayManager {

	static Integer day = 0;
	static JavaPlugin plugin;

	static Boolean startOnDayOne = true;

	public DayManager(JavaPlugin _plugin) {
		plugin = _plugin;

		startOnDayOne = ConfigManager.igetBool("startOnDayOne");

		day = GetDay();
	}

	@Nonnull
	public static Integer GetDay() {
		Integer days = (int) (Bukkit.getWorlds().get(0).getFullTime() / 24000);

		if (startOnDayOne)
			days++;

		// Debug.Log("Days: " + days.toString());
		return days;
	}

	public static void Tick() {
		Integer current = GetDay();
		if (current.equals(day))
			return;
		day = current;

		// On New day:
		sendDayAll();
	}

	public static void sendDayAll() {
		Bukkit.getOnlinePlayers().stream().forEach(p -> sendDay(p));
	}

	public static void sendDay(Player player) {
		player.playSound(player.getLocation(), Sound.BLOCK_BELL_RESONATE, 1, 0.01f);
		player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 0.1f, 1f);

		if (day % 10 == 0)
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.1f, 0.1f);

		if (day % 100 == 0) {
			player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.1f, 0.1f);

			Random r = new Random();

			new BukkitRunnable() {
				@Override
				public void run() {

					for (int i = 0; i < 5; i++) {
						Location loc = player.getLocation();
						loc = loc.add(r.nextInt(16) - 8, 0, r.nextInt(16) - 8);

						Firework fw = (Firework) player.getWorld().spawnEntity(loc, EntityType.FIREWORK);
						FireworkMeta fwm = fw.getFireworkMeta();

						Builder builder = FireworkEffect.builder();

						builder.flicker(true).trail(true);

						int typeNum = r.nextInt(4);
						switch (typeNum) {
						case 0:
						default:
							builder.withColor(Color.RED).withFade(Color.PURPLE);
							builder.with(Type.BALL);
							break;
						case 1:
							builder.withColor(Color.BLUE).withFade(Color.NAVY);
							builder.with(Type.BALL_LARGE);
							break;
						case 2:
							builder.withColor(Color.ORANGE).withFade(Color.YELLOW);
							builder.with(Type.BURST);
							break;
						case 3:
							builder.withColor(Color.GREEN).withFade(Color.AQUA);
							builder.with(Type.CREEPER);
							break;
						case 4:
							builder.withColor(Color.TEAL).withFade(Color.SILVER);
							builder.with(Type.STAR);
							break;
						}

						fwm.addEffect(builder.build());

						fwm.setPower(1);

						fw.setFireworkMeta(fwm);
					}
					cancel();
				}
			}.runTaskTimer(plugin, 50, 0);

		}

		player.sendTitle("DAY " + GetDay(), "", 40, 50, 10);
	}

}
