package net.daniel.MineCompany.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.daniel.MineCompany.MineCompanyPlugin;

public class CompanyYML extends YamlConfiguration {

	public FileConfiguration data;
	public File dataFile;
	
	public CompanyYML() {
	}

	public void setup() {
		if (!MineCompanyPlugin.plugin.getDataFolder().exists()) {
			MineCompanyPlugin.plugin.getDataFolder().mkdir();
		}

		dataFile = new File(MineCompanyPlugin.plugin.getDataFolder(), "data.yml");

		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The data.yml file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.RED + "Could not create the data.yml file in MineCompany");
			}
		}

		data = YamlConfiguration.loadConfiguration(dataFile);
	}
	
	

	public void reloadCompanies() {
		data = YamlConfiguration.loadConfiguration(dataFile);
	}
	
	
	
	public void saveData() {

		data.set("players", null);
		data.set("Companies", null);

		for (String com : MineCompanyPlugin.existCompanies.keySet()) {
			MineCompanyPlugin.existCompanies.get(com).saveCompany();

		}

		for (String player : MineCompanyPlugin.playerCompanies.keySet()) {
			MineCompanyPlugin.plugin.updatePlayer(player);

		}


		
		try {
			data.save(dataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
