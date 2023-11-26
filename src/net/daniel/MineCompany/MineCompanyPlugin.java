
package net.daniel.MineCompany;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.daniel.MineCompany.Commands.CompanyCMD;
import net.daniel.MineCompany.Commands.CompanyManagement;
import net.daniel.MineCompany.Commands.Migrate;
import net.daniel.MineCompany.MCUtils.MCUtils;
import net.daniel.MineCompany.Utils.AutoTask;
import net.daniel.MineCompany.Utils.FileUtils;
import net.daniel.MineCompany.Utils.ThrowsRunnable;
import net.daniel.MineCompany.data.CompanyYML;
import net.daniel.MineCompany.data.DeleteConfirm;
import net.daniel.MineCompany.data.InviteHolder;
import net.daniel.MineCompany.data.MandateHolder;
import net.milkbowl.vault.economy.Economy;

public class MineCompanyPlugin extends JavaPlugin{
	public static MineCompanyPlugin plugin;

	// ��ҹ��� ���� ���� (�̸��� �Ѵ� �׻� �ҹ��ڷ� ����)
	public static HashMap<String, Company> existCompanies;
	// ��ҹ��� ���� ���� (MinePro�� MinePro �ٸ�)

	// player,company
	public static HashMap<String, String> playerCompanies;

	public static CompanyYML CompanyYML;

	private ArrayList<String> playerPriceKey;
	private ArrayList<Double> playerPriceValue;

	private ArrayList<String> allowedUnpaidKey;
	private ArrayList<Integer> allowedUnpaidValue;

	private ArrayList<String> playerKeepPriceKey;
	private ArrayList<Double> playerKeepPriceValue;

	public static HashMap<String, InviteHolder> invites;
	public static HashMap<String, DeleteConfirm> deleteConfirms;
	public static HashMap<String, MandateHolder> mandateConfirms;

	public static ArrayList<Company> sortedCompanyList;

	private static long lastsortedTime;

	public static double startup_Cost;
	public static double startup_min_money;
	public static double rename_cost;

	private double calcRankPeriod;
	private double autoSave;

	public static double default_company_value;

	public static int invite_Sec;
	public static int confirmSec;

	public static long keepTime;
	public static String keepTimeString;

	private final FileConfiguration langConfig = new YamlConfiguration();
	public static Economy Eco;

	public static long getLastsortedTime() {
		return lastsortedTime;
	}

	public static void calcCompanyList() {

		sortedCompanyList.clear();

		for (String key : existCompanies.keySet()) {
			sortedCompanyList.add(existCompanies.get(key));

		}

		for (int i = 0; i < sortedCompanyList.size() - 1; i++) {

			for (int j = 0; j < sortedCompanyList.size() - 1 - i; j++) {
				if (sortedCompanyList.get(j).getValueForRank() < sortedCompanyList.get(j + 1).getValueForRank()) {

					// swap

					Company temp = sortedCompanyList.get(j);
					sortedCompanyList.set(j, sortedCompanyList.get(j + 1));
					sortedCompanyList.set(j + 1, temp);

				}

			}
		}

		for (int i = 0; i < sortedCompanyList.size() - 1; i++) {

			for (int j = 0; j < sortedCompanyList.size() - 1 - i; j++) {
				if (sortedCompanyList.get(j).getValueForRank() == sortedCompanyList.get(j + 1).getValueForRank()) {

					if (sortedCompanyList.get(j).getStartDate() < sortedCompanyList.get(j + 1).getStartDate()) {
						// swap

						Company temp = sortedCompanyList.get(j);
						sortedCompanyList.set(j, sortedCompanyList.get(j + 1));
						sortedCompanyList.set(j + 1, temp);
					}

				}

			}

		}

		lastsortedTime = System.currentTimeMillis();

	}

	public void onEnable() {
		MineCompanyPlugin.plugin = this;
		// copy default config
		getConfig().options().copyDefaults(true);
		saveConfig();

		if (!this.SetupEconomy()) {
			Bukkit.getConsoleSender().sendMessage("��6��l[ Mine Company ] ��c��lEconomy ��f�÷������� �νĵ��� �ʾ����Ƿ�, ��Ȱ��ȭ �˴ϴ�.");
			this.getServer().getPluginManager().disablePlugin((Plugin) this);
			return;
		}

		CompanyYML = new CompanyYML();
		existCompanies = new HashMap<String, Company>();
		playerCompanies = new HashMap<String, String>();
		invites = new HashMap<String, InviteHolder>();
		deleteConfirms = new HashMap<String, DeleteConfirm>();

		playerPriceKey = new ArrayList<String>();

		playerPriceValue = new ArrayList<Double>();

		allowedUnpaidKey = new ArrayList<String>();
		allowedUnpaidValue = new ArrayList<Integer>();

		playerKeepPriceKey = new ArrayList<String>();
		playerKeepPriceValue = new ArrayList<Double>();

		mandateConfirms = new HashMap<String, MandateHolder>();
		sortedCompanyList = new ArrayList<Company>();
		this.reloadConfiguration();
		loadCompanyData();
		
		getCommand("ȸ��").setExecutor(new CompanyCMD());
		getCommand("ȸ�����").setExecutor(new CompanyManagement());
		getCommand("ȸ���������").setExecutor(new Migrate());




	}

	private boolean SetupEconomy() {
		if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
			Bukkit.getConsoleSender().sendMessage("��6��l[ Mine Company ] ��c��lVault ��f�÷������� �νĵ��� �ʾ����Ƿ�, ������ ���� �˴ϴ�.");
			Bukkit.shutdown();
			return false;
		}
		Bukkit.getConsoleSender().sendMessage("��6��l[ Mine Company ] ��a��lVault ��f�÷������� �ν� �Ǿ����ϴ�.");
		RegisteredServiceProvider<Economy> EconomyProvider = this.getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (EconomyProvider != null) {
			Eco = (Economy) EconomyProvider.getProvider();
		}
		if (Eco != null) {
			return true;
		}
		return false;
	}

	public void onDisable() {
		saveLangConfigurations();
		CompanyYML.saveData();
	}

	public File createCompanyDataFile() {
		return new File(getDataFolder(), "data.yml");
	}

	public File createLangFile() {
		return new File(getDataFolder(), "lang.yml");
	}

	public void loadLangConfiguration() {

		doInputOutput(() -> langConfig.load(createLangFile()), "Exception while lang load.");

	}

	public void loadCompanyData() {
		CompanyYML.setup();
		CompanyYML.reloadCompanies();
		playerCompanies.clear();
		existCompanies.clear();
		invites.clear();
		deleteConfirms.clear();
		mandateConfirms.clear();

		if (CompanyYML.data.getConfigurationSection("players") == null) {
			CompanyYML.data.createSection("players");

		}

		if (CompanyYML.data.getConfigurationSection("Companies") == null) {
			CompanyYML.data.createSection("Companies");

		}
		
		ConfigurationSection playerSection = CompanyYML.data.getConfigurationSection("players");

		
		for (String name : playerSection.getKeys(false)) {
			playerCompanies.put(name.toLowerCase(), playerSection.getString(name));
		}

		ConfigurationSection companySection = CompanyYML.data.getConfigurationSection("Companies");

		for (String name : companySection.getKeys(false)) {
			existCompanies.put(name.toLowerCase(), loadCompany(name.toLowerCase()));
		}

		calcCompanyList();

	}

	public ArrayList<Double> getaddMaxPriceValue() {
		return playerPriceValue;

	}

	public ArrayList<String> getaddMaxPriceKey() {
		return playerPriceKey;
	}

	public ArrayList<String> getAllowedUnpaidKey() {
		return allowedUnpaidKey;
	}

	public ArrayList<Integer> getAllowedUnpaidValue() {
		return allowedUnpaidValue;
	}

	public ArrayList<String> getKeepPriceKey() {
		return playerKeepPriceKey;
	}

	public ArrayList<Double> getKeepPriceValue() {
		return playerKeepPriceValue;
	}

	public void reloadConfiguration() {

		reloadConfig();

		playerKeepPriceKey.clear();
		playerKeepPriceValue.clear();
		playerPriceKey.clear();
		playerPriceValue.clear();
		allowedUnpaidKey.clear();
		allowedUnpaidValue.clear();

		invite_Sec = getConfig().getInt("invite_sec", 30);

		if (invite_Sec <= 1) {
			getLogger().warning("invite_sec ���� 0���� ū �������� �մϴ�.");
			invite_Sec = 30;
		}

		confirmSec = getConfig().getInt("confirmSec", 15);

		if (confirmSec <= 1) {
			getLogger().warning("confirmSec ���� 0���� ū �������� �մϴ�.");
			confirmSec = 30;
		}

		autoSave = getConfig().getDouble("save-period", 180.0);

		if (autoSave <= 1) {
			getLogger().warning("auto-save ���� 1�̻��� ���ڿ��� �մϴ�. (���� ��)");
			autoSave = 180.0;
		}

		Set<String> temp = getConfig().getConfigurationSection("Company.addSize-cost").getKeys(false);

		for (String key : temp) {
			playerPriceKey.add(key);
			playerPriceValue.add(getConfig().getDouble("Company.addSize-cost." + key));
		}

		temp = getConfig().getConfigurationSection("Company.maintenance_Fee_perPlayer").getKeys(false);

		for (String key : temp) {
			playerKeepPriceKey.add(key);
			playerKeepPriceValue.add(getConfig().getDouble("Company.maintenance_Fee_perPlayer." + key));
		}

		temp = getConfig().getConfigurationSection("Company.allowed_Max_Unpaid_times").getKeys(false);

		for (String key : temp) {
			allowedUnpaidKey.add(key);
			allowedUnpaidValue.add(getConfig().getInt("Company.allowed_Max_Unpaid_times." + key));
		}

		startup_min_money = getConfig().getDouble("Company.startup-minimum-balance", 20000000.0);

		if (startup_min_money < 0) {
			startup_min_money = 20000000.0;
			getLogger().warning("Company.startup-minimum-balance ���� 0�̻��� ���ڿ��� �մϴ�.");

		}

		startup_Cost = getConfig().getDouble("Company.startup-cost", 7000000.0);

		if (startup_Cost < 0) {
			startup_Cost = 7000000.0;
			getLogger().warning("Company.startup-cost ���� 0�̻��� ���ڿ��� �մϴ�.");

		}
		
		rename_cost = getConfig().getDouble("Company.rename-cost", 7000000.0);

		if (rename_cost < 0) {
			rename_cost = 2000000.0;
			getLogger().warning("Company.rename_cost ���� 0�̻��� ���ڿ��� �մϴ�.");

		}

		calcRankPeriod = getConfig().getDouble("calc-rank-period", 300.0);

		if (calcRankPeriod <= 0) {
			calcRankPeriod = 300.0;
			getLogger().warning("calcRankPeriod ���� 0���� ū ���ڿ��� �մϴ�. (���� ��)");

		}

		default_company_value = getConfig().getDouble("Company.defaultCompany-value", 5000000.0);
		if (default_company_value < 0) {
			default_company_value = 5000000.0;
			getLogger().warning("Company.defaultCompany-value ���� 0�̻��� ���ڿ��� �մϴ�.");

		}

		getConfig().options().copyDefaults(true);

		saveConfig();

		loadLangConfiguration();
		Lang.init(langConfig);
		saveLangConfigurations();

		String time = getConfig().getString("�����񳳺ΰ���", "4D 0H 0M 0S");
		String[] timePart = time.split(" ");

		keepTime = 0;
		keepTimeString = "";
		try {
			for (String t : timePart) {

				int timeAmount = 0;

				if (t.contains("D") || t.contains("d")) {
					t = t.replaceAll("D", "").replaceAll("d", "");

					timeAmount = Integer.parseInt(t);
					if (timeAmount > 0) {
						keepTime += timeAmount * 86400;
						keepTimeString += timeAmount + Lang.DAYS.toString() + " ";
					}

				}

				if (t.contains("H") || t.contains("h")) {
					t = t.replaceAll("H", "").replaceAll("h", "");

					timeAmount = Integer.parseInt(t);
					if (timeAmount > 0) {
						keepTime += timeAmount * 3600;
						keepTimeString += timeAmount + Lang.HOURS.toString() + " ";
					}

				}

				if (t.contains("M") || t.contains("m")) {
					t = t.replaceAll("M", "").replaceAll("m", "");

					timeAmount = Integer.parseInt(t);
					if (timeAmount > 0) {
						keepTime += timeAmount * 60;
						keepTimeString += timeAmount + Lang.MINUTES.toString() + " ";
					}

				}

				if (t.contains("S") || t.contains("s")) {
					t = t.replaceAll("S", "").replaceAll("s", "");

					timeAmount = Integer.parseInt(t);
					if (timeAmount > 0) {
						keepTime += timeAmount;
						keepTimeString += timeAmount + Lang.SECONDS.toString() + " ";
					}

				}

			}
			keepTime *= 1000;
			keepTimeString = MCUtils.removeLastChar(keepTimeString);

		} catch (Exception e) {
			getLogger().warning("MineCompanyPlugin �����񳳺ΰ��� ������ ��ȿ���� �ʽ��ϴ�.");
			keepTime = 4 * 24 * 3600 * 1000L;
			keepTimeString = "4" + Lang.DAYS.toString();
		}
		
		
		
		
		
		AutoTask.register(autoSave, calcRankPeriod, getConfig().getDouble("pay-maintanFee-interval", 600.0));

	}

	public void saveLangConfigurations() {

		doInputOutput(() -> langConfig.save(FileUtils.writeEnsure(createLangFile())), "Exception when lang save.");
	}

	private void doInputOutput(ThrowsRunnable runnable, String errorMessage) {
		try {
			runnable.run();
		} catch (FileNotFoundException ex) {
			// Ignore
		} catch (Exception ex) {
			getLogger().log(Level.WARNING, errorMessage, ex);
		}
	}

	public void Restart() {
		getServer().getPluginManager().disablePlugin(this);
		getServer().getPluginManager().enablePlugin(this);
	}

	public Company loadCompany(String name) {
		Company company = existCompanies.get(name);

		if (company == null) {
			company = new Company(name);

			
			ConfigurationSection companySection = CompanyYML.data.getConfigurationSection("Companies." + name);

			if (companySection == null) {

				return null;
			}
			
			company.setLeader(companySection.getString("leader", "Unknown Player"));
			company.setSub_leaders((ArrayList<String>) companySection.getStringList("sub_leaders"));
			company.setMembers((ArrayList<String>) companySection.getStringList("members"));
			company.setUnPaid_times(companySection.getInt("unPaid_times" , 0));
			company.setUnPaid(companySection.getDouble("unPaid_money" , 0));			
			company.setMaxSize(companySection.getInt("maxSize", 8));
			company.setCompanyValue(companySection.getDouble("value", 0L));
			company.setStartDate(companySection.getLong("startDate", System.currentTimeMillis()));
			company.lastPaid = companySection.getLong("lastPaid", company.getStartDate());

		}
		return company;
	}
	


	public void deleteCompany(Company company) {

		removePlayer(company.getLeader());

		for (String playerName : company.getSub_leaders()) {
			removePlayer(playerName);

		}
		for (String playerName : company.getMembers()) {
			removePlayer(playerName);
		}
		existCompanies.remove(company.getName());

		CompanyYML.data.getConfigurationSection("Companies").set(company.getName(), null);
		company = null;
	}

	public void updatePlayer(String player) {
		if (CompanyYML.data.getConfigurationSection("players") == null) {
			CompanyYML.data.createSection("players");

		}

		ConfigurationSection playerSection = CompanyYML.data.getConfigurationSection("players");

		{
			player = player.toLowerCase();
			String companyName = playerCompanies.get(player);
			//CompanyYML.data.createSection("players." + player.toLowerCase());
			
			playerSection.set(player, companyName.toLowerCase());
		}

	}

	public void removePlayer(String playerName) {
		playerName = playerName.toLowerCase();
		playerCompanies.remove(playerName);
		CompanyYML.data.getConfigurationSection("players").set(playerName, null);

	}

	public FileConfiguration getLangConfig() {
		return langConfig;
	}

}
