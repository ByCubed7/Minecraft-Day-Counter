package io.github.bycubed7.daycounter.commands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.bycubed7.commands.Action;
import io.github.bycubed7.commands.ActionFailed;
import io.github.bycubed7.daycounter.managers.DayManager;

public class Day extends Action {

	public Day(JavaPlugin plugin) {
		super("day", plugin);
		plugin.getCommand(name).setExecutor(this);
	}

	@Override
	protected ActionFailed approved(Player player, String[] args) {

		return ActionFailed.NONE;
	}

	@Override
	protected boolean execute(Player player, String[] args) {

		DayManager.GetDay();

		return true;
	}

}
