package net.daniel.MineCompany.data;

import net.daniel.MineCompany.Company;

public class InviteHolder {

	private String target;
	private String inviter;

	private Company company;
	//long time;
	
	public InviteHolder(String target, Company company, String inviter) {
		this.target = target;
		this.inviter = inviter;
		this.company = company;
	//	time = System.currentTimeMillis();
		
	}

	public String getTarget() {
		return target;
	}

	public String getInviter() {
		return inviter;
	}

	public Company getCompany() {
		return company;
	}


}
