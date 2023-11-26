package net.daniel.MineCompany.Utils;

import org.bukkit.Bukkit;

import net.daniel.MineCompany.MineCompanyPlugin;

public class AutoTask {

	AutoTask() {
	}

	public static void register(double autoSave, double autoRank, double payIntervalInSec) {
		Bukkit.getScheduler().cancelTasks(MineCompanyPlugin.plugin);

		long save = (long) (autoSave * 20L);

		Bukkit.getScheduler().runTaskTimerAsynchronously(MineCompanyPlugin.plugin, new Runnable() {

			@Override
			public void run() {
				MineCompanyPlugin.CompanyYML.saveData();

			}
		}, save, save);

		long rank = (long) (autoRank * 20L);

		Bukkit.getScheduler().runTaskTimerAsynchronously(MineCompanyPlugin.plugin, new Runnable() {

			@Override
			public void run() {

				MineCompanyPlugin.calcCompanyList();

			}
		}, rank, rank);

		long payInterval = (long) (payIntervalInSec * 20L);

		Bukkit.getScheduler().runTaskTimerAsynchronously(MineCompanyPlugin.plugin, new Runnable() {

			@Override
			public void run() {

				CompanyFuns.excuteMaintainFee();

			}
		}, payInterval, payInterval);
	}

}
