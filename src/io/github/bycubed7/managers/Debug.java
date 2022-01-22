package io.github.bycubed7.managers;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Debug {

	public static ConsoleCommandSender logger;

	public static String prefix;
	public static String version;
	public static String serverVersion;

	public Debug(JavaPlugin plugin) {
		prefix = "[" + plugin.getDescription().getPrefix() + "] ";
		version = plugin.getDescription().getVersion();
		logger = plugin.getServer().getConsoleSender();

		String a = plugin.getServer().getClass().getPackage().getName();
		serverVersion = a.substring(a.lastIndexOf('.') + 1);
	}

	public static void Log(String s) {
		logger.sendMessage(prefix + s);
	}

	public static void Log(String s, ChatColor color) {
		Log(color + s);
	}

	public static void Tell(Player player, String s) {
		// logger.info(prefix + " " + s);
		Log(player.getDisplayName() + " was told: " + s);
		player.sendMessage(s);
	}

	public static void Error(String s) {

	}

	public static void Banner() {

		// .sendMessage(ChatColor.DARK_GRAY + "Reload Complete");
		ChatColor c = ChatColor.GRAY;
		ChatColor a = ChatColor.DARK_GRAY;
		ChatColor v = ChatColor.DARK_GREEN;

		logger.sendMessage(
				c + " ______                           ______                                 _                     ");
		logger.sendMessage(
				c + "|_   _ `.                       .' ___  |                               / |_                   ");
		logger.sendMessage(c
				+ "  | | `. \\  ,--.     _   __    / .'   \\_|   .--.    __   _    _ .--.   `| |-'  .---.   _ .--.  ");
		logger.sendMessage(c
				+ "  | |  | | `'_\\ :   [ \\ [  ]   | |        / .'`\\ \\ [  | | |  [ `.-. |   | |   / /__\\\\ [ `/'`\\] ");
		logger.sendMessage(c
				+ " _| |_.' / // | |,   \\ '/ /    \\ `.___.'\\ | \\__. |  | \\_/ |,  | | | |   | |,  | \\__.,  | |     ");
		logger.sendMessage(c
				+ "|______.'  \\'-;__/ [\\_:  /      `.____ .'  '.__.'   '.__.'_/ [___||__]  \\__/   '.__.' [___]    ");
		logger.sendMessage(c + "                    \\__.'  " + v + " v" + Debug.version + "  Running on "
				+ Debug.serverVersion + a + "   ~ By ByCubed7");
		logger.sendMessage("");
	}

}
