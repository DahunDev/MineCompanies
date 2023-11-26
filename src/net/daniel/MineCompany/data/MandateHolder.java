package net.daniel.MineCompany.data;

import org.bukkit.entity.Player;

import net.daniel.MineCompany.Company;

public class MandateHolder {

	public Player sender;
	public String target;
	public Company company;
	public long lastReqTime;
	public String companyName;


	public MandateHolder(Player sender, Company company, String target) {
		this.sender = sender;
		this.company = company;
		lastReqTime = System.currentTimeMillis();
		companyName = company.getName();
		this.target = target.toLowerCase();
		
	}

}




