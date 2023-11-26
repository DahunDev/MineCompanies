package net.daniel.MineCompany.data;

import org.bukkit.entity.Player;

import net.daniel.MineCompany.Company;

public class DeleteConfirm {

	private Player player;
	private Company company;
	public long lastReqTime;
	private String companyName;

	public Player getPlayer() {
		return player;
	}

	public Company getCompany() {
		return company;
	}

	public DeleteConfirm(Player player, Company company) {
		this.company = company;
		lastReqTime = System.currentTimeMillis();
		companyName = company.getName();
	}

	public String getCompanyName() {
		return companyName;
	}
	
}




