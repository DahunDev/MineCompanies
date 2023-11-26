package net.daniel.MineCompany.Utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.daniel.MineCompany.Company;
import net.daniel.MineCompany.MineCompanyPlugin;
import net.daniel.MineCompany.Lang;
import net.daniel.MineCompany.MCUtils.MCUtils;
import net.daniel.MineCompany.data.DeleteConfirm;
import net.daniel.MineCompany.data.InviteHolder;
import net.daniel.MineCompany.data.MandateHolder;

public class CompanyFuns {

	public static boolean isExistCompany(String name) {

		if (MineCompanyPlugin.existCompanies.get(name.toLowerCase()) == null) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean mustExistCompany(String name, CommandSender sender) {
		if (isExistCompany(name)) {
			return true;
		} else {

			sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_NOT_EXIST_THAT_NAME, "%company%", name));
			return false;

		}
	}
	
	

	public static boolean mustNonExistCompany(String name, CommandSender sender) {
		if (!isExistCompany(name)) {
			return true;
		} else {

			sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_ALREADY_EXIST, "%company%", name));
			return false;

		}
	}

	public static boolean createCompany(String companyName, String owner) {
		if (MineCompanyPlugin.playerCompanies.get(owner.toLowerCase()) != null) {
			return false;
		}

		if (isExistCompany(companyName)) {
			return false;
		}

		Company company = new Company(companyName.toLowerCase());
		company.setLeader(owner);
		company.setCompanyValue(MineCompanyPlugin.default_company_value);

		MineCompanyPlugin.existCompanies.put(companyName.toLowerCase(), company);
		MineCompanyPlugin.playerCompanies.put(owner.toLowerCase(), companyName.toLowerCase());
		new BukkitRunnable() {

			@Override
			public void run() {
				MineCompanyPlugin.calcCompanyList();

			}
		}.runTaskAsynchronously(MineCompanyPlugin.plugin);

		return true;

	}

	public static void deleteCompany(String companyName) {
		companyName = companyName.toLowerCase();

		Company company = MineCompanyPlugin.existCompanies.get(companyName);

		MineCompanyPlugin.deleteConfirms.remove(company.getLeader().toLowerCase());

		MineCompanyPlugin.playerCompanies.remove(company.getLeader().toLowerCase());

		for (String player : company.getSub_leaders()) {

			MineCompanyPlugin.playerCompanies.remove(player.toLowerCase());
			MineCompanyPlugin.plugin.removePlayer(player);

		}

		for (String player : company.getMembers()) {

			MineCompanyPlugin.playerCompanies.remove(player.toLowerCase());
			MineCompanyPlugin.plugin.removePlayer(player);

		}
		MineCompanyPlugin.plugin.removePlayer(company.getLeader());

		MineCompanyPlugin.existCompanies.remove(companyName);

		MineCompanyPlugin.CompanyYML.createSection("Companies");
		MineCompanyPlugin.CompanyYML.getConfigurationSection("Companies").set(companyName, null);
		MineCompanyPlugin.sortedCompanyList.remove(company);
		MineCompanyPlugin.mandateConfirms.remove(companyName);

	}

	
	public static boolean renameCompany(Company company, String newName, CommandSender sender) {

		newName = newName.toLowerCase();
		String oldName = company.getName();
		String owner = company.getLeader();

		if (MCUtils.mustVaildName(newName, sender)) {
			if (CompanyFuns.mustNonExistCompany(newName, sender)) {
				MineCompanyPlugin.mandateConfirms.remove(oldName);

				MineCompanyPlugin.invites.remove(oldName);

				company.setName(newName);

				MineCompanyPlugin.existCompanies.remove(oldName);
				MineCompanyPlugin.existCompanies.put(newName, company);
				MineCompanyPlugin.playerCompanies.replace(owner, oldName, newName);
				MineCompanyPlugin.plugin.updatePlayer(owner);

				MCUtils.sendMsgTo(owner,
						Lang.withPlaceHolder(Lang.COMPANY_CHNAGED_NAME_MEMBERS, "%company%", company.getName()));

				for (String player : company.getSub_leaders()) {
					MineCompanyPlugin.playerCompanies.replace(player, oldName, newName);
					MCUtils.sendMsgTo(player,
							Lang.withPlaceHolder(Lang.COMPANY_CHNAGED_NAME_MEMBERS, "%company%", company.getName()));
					MineCompanyPlugin.plugin.updatePlayer(player);

				}

				for (String player : company.getMembers()) {
					MineCompanyPlugin.playerCompanies.replace(player, oldName, newName);
					MCUtils.sendMsgTo(player,
							Lang.withPlaceHolder(Lang.COMPANY_CHNAGED_NAME_MEMBERS, "%company%", company.getName()));
					MineCompanyPlugin.plugin.updatePlayer(player);

				}

				MineCompanyPlugin.CompanyYML.createSection("Companies");
				MineCompanyPlugin.CompanyYML.getConfigurationSection("Companies").set(oldName, null);
				company.saveCompany();
				return true;

			}

		}

		return false;

	}
	
	
	
	public static boolean addMember(String companyName, String player) {
		companyName = companyName.toLowerCase();
		player = player.toLowerCase();

		if (isExistCompany(companyName)) {
			if (MineCompanyPlugin.playerCompanies.get(player) == null) {
				return MineCompanyPlugin.existCompanies.get(companyName).addMember(player);
			}

		}
		return false;

	}

	public static boolean removeMember(String companyName, String player) {
		companyName = companyName.toLowerCase();
		player = player.toLowerCase();

		if (isExistCompany(companyName)) {

			if (MineCompanyPlugin.playerCompanies.get(player).equalsIgnoreCase(companyName)) {

				Company company = MineCompanyPlugin.existCompanies.get(companyName);

				if (company.isMember(player)) {

					company.removeMember(player);

					return true;

				}
			}
		}
		return false;

	}

	public static void denyInvite(Player sender) {

		if (mustInvited(sender)) {
			InviteHolder invite = MineCompanyPlugin.invites.get(sender.getName().toLowerCase());
			sender.sendMessage(Lang.DENIED_INVITE_TARGET.toString());

			MCUtils.sendMsgTo(invite.getInviter(),
					Lang.withPlaceHolder(Lang.DENIED_INVITE_INVITER, "%target%", sender.getName()));

			MineCompanyPlugin.invites.remove(sender.getName().toLowerCase());

		}

	}

	public static void acceptInvite(Player sender) {

		if (mustInvited(sender)) {

			InviteHolder invite = MineCompanyPlugin.invites.get(sender.getName().toLowerCase());

			if (CompanyFuns.getCompany(sender) == null) {
				Company company = invite.getCompany();
				if(company.getMaxSize() > company.getTeamSize()) {
										
					company.addMember(sender.getName());

					sender.sendMessage(Lang.ACCEPTED_INVITE_TARGET.toString());

					MCUtils.sendMsgTo(invite.getInviter(),
							Lang.withPlaceHolder(Lang.ACCEPT_INVITE_INVITER, "%target%", sender.getName()));
				}else {
					sender.sendMessage(Lang.withPlaceHolder(Lang.CANNOT_ACCEPT_MAXSIZE_LIMIT, "%max%", company.getMaxSize()));
					

					MCUtils.sendMsgTo(invite.getInviter(),
							Lang.withPlaceHolder(Lang.CANNOT_ADD_MAXSIZE_LIMIT, "%max%",company.getMaxSize()));

				}


			} else {
				sender.sendMessage(Lang.ALREADY_HAS_COMPANY_ME.toString());
			}

			MineCompanyPlugin.invites.remove(sender.getName().toLowerCase());

		}

	}

	public static double calcAddMaxPrice(int nowMax) {
		nowMax = nowMax + 1;
		ArrayList<String> keys = MineCompanyPlugin.plugin.getaddMaxPriceKey();
		ArrayList<Double> values = MineCompanyPlugin.plugin.getaddMaxPriceValue();

		boolean needSum = MineCompanyPlugin.plugin.getConfig().getBoolean("maxSize_sum_price", false);
		int maxSize = MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company", 1000);
		double price = 0;
		double priceTemp = 0;

		for (int i = 0; i < keys.size(); i++) {
			int keyVal;
			String key = keys.get(i);
			if (key.equalsIgnoreCase("max")) {
				keyVal = maxSize;
			} else {
				keyVal = Integer.parseInt(key);
			}

			if (nowMax > maxSize) {
				return Double.NaN;
			}

			priceTemp = values.get(i);
			if (needSum) {
				price += priceTemp;
			}

			if (nowMax <= keyVal) {

				if (needSum) {
					return price;

				} else {
					return priceTemp;
				}

			}

		}
		return Double.NaN;

	}

	public static double calcKeepPrice(int nowMax) {
		ArrayList<String> keys = MineCompanyPlugin.plugin.getKeepPriceKey();
		ArrayList<Double> values = MineCompanyPlugin.plugin.getKeepPriceValue();

		boolean needSum = MineCompanyPlugin.plugin.getConfig().getBoolean("maintenanceFee_sum_price", false);
		int maxSize = MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company", 1000);
		double price = 0;
		double priceTemp = 0;

		for (int i = 0; i < keys.size(); i++) {
			int keyVal;
			String key = keys.get(i);
			if (key.equalsIgnoreCase("max")) {
				keyVal = maxSize;
			} else {
				keyVal = Integer.parseInt(key);
			}

			if (nowMax > maxSize) {
				return Double.NaN;
			}

			priceTemp = values.get(i);
			if (needSum) {
				price += priceTemp;
			}

			if (nowMax <= keyVal) {

				if (needSum) {
					return price * nowMax;

				} else {
					return priceTemp * nowMax;
				}

			}

		}
		return Double.NaN;

	}

	public static int calcAllowedUnpaid(int nowMax) {
		ArrayList<String> keys = MineCompanyPlugin.plugin.getAllowedUnpaidKey();
		ArrayList<Integer> values = MineCompanyPlugin.plugin.getAllowedUnpaidValue();

		int maxSize = MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company", 1000);

		int price = 0;

		for (int i = 0; i < keys.size(); i++) {
			int keyVal;
			String key = keys.get(i);
			if (key.equalsIgnoreCase("max")) {
				keyVal = maxSize;
			} else {
				keyVal = Integer.parseInt(key);
			}

			if (nowMax > maxSize) {
				return -1;
			}

			price = values.get(i);

			if (nowMax <= keyVal) {

				return price;

			}

		}
		return -1;

	}

	public static boolean mustInvited(Player player) {

		if (MineCompanyPlugin.invites.get(player.getName().toLowerCase()) != null) {

			return true;
		} else {

			player.sendMessage(Lang.NOT_INVITED.toString());
			return false;
		}

	}

	public static void inviteAutoExpire(String inviter, String target, Player inviterP) {

		if (mustBeOnline(target, inviterP)) {
			Player tar = Bukkit.getPlayerExact(target);

			InviteHolder temp = MineCompanyPlugin.invites.get(target.toLowerCase()); // null

			if (temp == null) {

				if (MineCompanyPlugin.invites.get(target.toLowerCase()) == null) {

					if (MCUtils.mustHasPermforInvite(inviterP)) {

						if (MineCompanyPlugin.playerCompanies.get(target.toLowerCase()) == null) {

							Company company = CompanyFuns.getCompany(inviterP);
		
							if(company.getMaxSize() > company.getTeamSize()) {
								
								MineCompanyPlugin.invites.put(target.toLowerCase(),
										new InviteHolder(target, company, inviter));

								inviterP.sendMessage(Lang.withPlaceHolder(Lang.INVITED_PLAYER, "%target%", target));

								tar.sendMessage(Lang.withPlaceHolder(Lang.INVITED_INFO_TO_TARGET,
										new String[] { "%company%", "%sec%" }, company.getName(),
										MineCompanyPlugin.invite_Sec));

								(new BukkitRunnable() {
									public void run() {

										if (MineCompanyPlugin.invites.get(target.toLowerCase()) != null) {

											MineCompanyPlugin.invites.remove(target.toLowerCase());

											MCUtils.sendMsgTo(target, Lang.INVITE_DENIED_BY_EXPIRED_TARGET.toString());

											MCUtils.sendMsgTo(inviter, Lang.withPlaceHolder(Lang.INVITE_DENIED_BY_EXPIRED,
													"%target%", target));

										}

									}

								}).runTaskLaterAsynchronously(MineCompanyPlugin.plugin, 20L * MineCompanyPlugin.invite_Sec);
							}else {
								inviterP.sendMessage(Lang.withPlaceHolder(Lang.CANNOT_INVITE_MAXSIZE_LIMIT, "%max%", company.getMaxSize()));
							}
							
							

						} else {

							inviterP.sendMessage(Lang.withPlaceHolder(Lang.ALREADY_HAS_COMPANY, "%target%", target));
						}

					}
				} else {

					inviterP.sendMessage(Lang.withPlaceHolder(Lang.ALREADY_INVITED, "%target%", target));

				}
			} else {

				if (temp.getInviter().equalsIgnoreCase(inviter)) {
					inviterP.sendMessage(Lang.withPlaceHolder(Lang.ALREADY_INVITED, "%target%", target));
				} else {

					inviterP.sendMessage(Lang.withPlaceHolder(Lang.INVITE_REQUESTED_BY_OTHER,
							new String[] { "%target%", "%other%" }, target, temp.getInviter()));
				}

			}
		}

	}

	public static void deleteConfirmAutoExpire(Player player) {
		String nick = player.getName().toLowerCase();

		if (MCUtils.mustHasCompany(player)) {

			Company company = CompanyFuns.getCompany(player);

			if (MCUtils.mustBeOwner(player.getName(), company, player)) {
				DeleteConfirm temp = MineCompanyPlugin.deleteConfirms.get(nick); // possible null

				if (temp == null) {
					MineCompanyPlugin.deleteConfirms.put(nick, new DeleteConfirm(player, company));
					temp = MineCompanyPlugin.deleteConfirms.get(nick);

				} else {
					player.sendMessage(Lang.DELETE_COMPANY_CONFIRM_CANCEL.toString());
					MineCompanyPlugin.deleteConfirms.remove(nick);
					MineCompanyPlugin.deleteConfirms.put(nick, new DeleteConfirm(player, company));
					temp = MineCompanyPlugin.deleteConfirms.get(nick);
				}

				player.sendMessage(
						Lang.withPlaceHolder(Lang.DELETE_COMPANY_CONFIRM, "%sec%", MineCompanyPlugin.confirmSec));

				new BukkitRunnable() {

					@Override
					public void run() {

						DeleteConfirm temp = MineCompanyPlugin.deleteConfirms.get(nick);

						if (temp != null) {

							long timepassed = (System.currentTimeMillis() / 1000L) - temp.lastReqTime;

							if (timepassed >= MineCompanyPlugin.confirmSec - 0.4) {
								MineCompanyPlugin.deleteConfirms.remove(nick);
								player.sendMessage(Lang.DELETE_COMPANY_CONFIRM_CANCEL.toString());

							}

						}

					}
				}.runTaskLaterAsynchronously(MineCompanyPlugin.plugin, 20L * MineCompanyPlugin.confirmSec);

			}

		}

	}

	public static void mandateConfirmAutoExpire(Player sender, Player target) {
		String nick = sender.getName().toLowerCase();
		String tarNick = target.getName().toLowerCase();
		if (!nick.equalsIgnoreCase(target.getName())) {

			if (MCUtils.mustHasCompany(sender)) {

				Company company = CompanyFuns.getCompany(sender);

				String companyName = company.getName();

				if (MCUtils.mustBeOwner(sender.getName(), company, sender)) {

					if (MCUtils.mustHasCompany(target, sender)) {
						Company targetCompany = CompanyFuns.getCompany(target);

						if (targetCompany.getName().equalsIgnoreCase(companyName)) {

							if (!company.isLeader(target.getName())) {

								MandateHolder temp = MineCompanyPlugin.mandateConfirms.get(companyName); // possible
																											// null

								if (temp == null) {
									MineCompanyPlugin.mandateConfirms.put(companyName,
											new MandateHolder(sender, company, target.getName()));
									temp = MineCompanyPlugin.mandateConfirms.get(nick);

								} else {
									if (!temp.target.equalsIgnoreCase(target.getName())) {

										temp.lastReqTime = System.currentTimeMillis();
										temp.target = tarNick;
										temp.sender = sender;

										sender.sendMessage(Lang.MANDATE_CONFIRM_CANCELED.toString());

										temp = MineCompanyPlugin.mandateConfirms.get(companyName);

									} else {

										sender.sendMessage(Lang.MANDATE_LEADER_ALREADY_REQUESTED.toString());
										return;

									}

								}

								sender.sendMessage(Lang.withPlaceHolder(Lang.MANDATE_LEADER_REQUESTED,
										new String[] { "%sec%", "%company%", "%target%" }, MineCompanyPlugin.confirmSec,
										company.getName(), target.getName()));

								target.sendMessage(Lang.withPlaceHolder(Lang.MANDATE_LEADER_REQUESTED_TARGET,
										new String[] { "%sec%", "%company%", "%target%" }, MineCompanyPlugin.confirmSec,
										company.getName(), target.getName()));

								new BukkitRunnable() {

									@Override
									public void run() {

										MandateHolder temp = MineCompanyPlugin.mandateConfirms.get(companyName);

										if (temp != null) {

											long timepassed = (System.currentTimeMillis() / 1000L) - temp.lastReqTime;

											if (timepassed >= MineCompanyPlugin.confirmSec - 0.4) {
												MineCompanyPlugin.mandateConfirms.remove(companyName);

												MCUtils.sendMsgTo(tarNick, Lang.MANDATE_DENIED_BY_EXPIRED.toString());
												MCUtils.sendMsgTo(nick, Lang.MANDATE_DENIED_BY_EXPIRED.toString());

											}

										}

									}
								}.runTaskLaterAsynchronously(MineCompanyPlugin.plugin,
										20L * MineCompanyPlugin.confirmSec);

							} else {
								sender.sendMessage(Lang.CANNOT_MANDATE_TO_OWNER.toString());
							}

						} else {

							sender.sendMessage(Lang.withPlaceHolder(Lang.CANNOT_MANDATE_ANOTHER_COMPANY, "%target%",
									target.getName()));

						}

					}

				}

			}
		} else {

			sender.sendMessage(Lang.CANNOT_MANDATE_MYSELF.toString());

		}

	}

	public static void denyMandate(Player sender) {

		if (MCUtils.mustMandateRequested(sender)) {

			Company company = getCompany(sender);
			String companyName = company.getName();

			MandateHolder mandate = MineCompanyPlugin.mandateConfirms.get(companyName);

			if (company.getName().equalsIgnoreCase(mandate.companyName)) {

				sender.sendMessage(Lang.withPlaceHolder(Lang.MANDATE_LEADER_DENIED, "%company%", company.getName()));

				if (mandate.sender != null) {
					
					mandate.sender.sendMessage(
							Lang.withPlaceHolder(Lang.MANDATE_LEADER_DENIED_TO_OWNER, "%target%", mandate.target));
				}

			} else {
				sender.sendMessage(Lang.CANNOT_MANDATE_CHANGED_COMPANY.toString());
				if (mandate.sender != null) {
					mandate.sender.sendMessage(Lang.CANNOT_MANDATE_CHANGED_COMPANY.toString());
				}
			}

			MineCompanyPlugin.mandateConfirms.remove(companyName);

		}

	}

	public static void excuteMaintainFee() {
		for (String name : MineCompanyPlugin.existCompanies.keySet()) {
			Company company = MineCompanyPlugin.existCompanies.get(name);

			if (company.lastPaid <= System.currentTimeMillis() - MineCompanyPlugin.keepTime) {
				company.lastPaid = System.currentTimeMillis();

				double price = CompanyFuns.calcKeepPrice(company.getMaxSize());
				if (company.getCompanyValue() >= price) {
					company.subtractCompanyValue(price);

					MCUtils.sendMsgTo(company.getLeader(),
							Lang.withPlaceHolder(Lang.JUST_PAID_MAINTAIN_FEE,
									new String[] { "%company%", "%price%", "%value%" }, company.getName(), price,
									company.getCompanyValue()));

					if (company.getCompanyValue() > company.getUnPaid() && company.getUnPaid() > 0) {
						company.subtractCompanyValue(company.getUnPaid());
						company.setUnPaid(0);
						company.setUnPaid_times(0);
						break;
					}

					if (MineCompanyPlugin.plugin.getConfig().getBoolean("Company.subtract_unPaid_times_when_pay_Fee",
							false)) {
						company.subtractUnpaid_times();

					}

					if (company.getCompanyValue() > 0 && company.getUnPaid() > 0) {

						if (MineCompanyPlugin.plugin.getConfig().getBoolean("Company.auto-pay-unPaid-fee", false)) {
							double temp = company.getCompanyValue();

							company.subtractunPaidValue(temp);
							company.setCompanyValue(0);
						}

					}

				} else {
					company.setCompanyValue(0);
					double unPaid = price - company.getCompanyValue();
					company.addunPaidValue(unPaid);
					company.addUnpaid_times();
					int allowedTimes = CompanyFuns.calcAllowedUnpaid(company.getMaxSize());

					MCUtils.sendMsgTo(company.getLeader(),
							Lang.withPlaceHolder(Lang.JUST_UNPAID_MAINTAIN_FEE,
									new String[] { "%company%", "%need%", "%value%", "%unPaid%", "%unPaid_times%",
											"%max_unPaid%" },
									company.getName(), unPaid, company.getCompanyValue(), company.getUnPaid(),
									company.getUnpaid_times(), allowedTimes));

					if (company.getUnpaid_times() > allowedTimes) {

						deleteCompany(name);

						Bukkit.getScheduler().runTask(MineCompanyPlugin.plugin, new Runnable() {
							@Override
							public void run() {

								MCUtils.broadcast(
										Lang.withPlaceHolder(Lang.COMPANY_DELETED_BY_UNPAID, "%company%", name));
							}
						});

					}

				}

			}

		}

	}

	public static void acceptMandate(Player sender) {
		if (MCUtils.mustMandateRequested(sender)) {

			Company company = getCompany(sender);
			String companyName = company.getName();

			MandateHolder mandate = MineCompanyPlugin.mandateConfirms.get(companyName);

			if (companyName.equalsIgnoreCase(mandate.companyName)) {

				if (company.isLeader(mandate.sender.getName())) {
					if (company.hasThisPlayer(sender.getName())) {

						String leaderName = company.getLeader();

						if (company.changeLeader(mandate.sender.getName(), sender.getName())) {

							if (mandate.sender != null) {

								leaderName = mandate.sender.getName();

								mandate.sender.sendMessage(Lang.MANDATE_LEADER_DONE.toString());

							}

							sender.sendMessage(Lang.MANDATE_LEADER_DONE.toString());
							

							MCUtils.broadcast(Lang.withPlaceHolder(Lang.MANDATE_LEADER_BROADCAST,
									new String[] { "%company%", "%leader%", "%target%" }, company.getName(), leaderName,
									sender.getName()));

						} else {
							
							
							sender.sendMessage(
									Lang.withPlaceHolder(Lang.MANDATE_LEADER_FAILED, "%company%", company.getName()));

						}

					} else {
						sender.sendMessage(Lang.CANNOT_MANDATE_CHANGED_COMPANY.toString());

					}

				} else {
					sender.sendMessage(Lang.CANNOT_MANDATE_CHANGED_LEADER.toString());

					if (mandate.sender != null) {
						mandate.sender.sendMessage(Lang.CANNOT_MANDATE_CHANGED_LEADER.toString());
					}
				}

			} else {
				sender.sendMessage(Lang.CANNOT_MANDATE_CHANGED_COMPANY.toString());
				if (mandate.sender != null) {
					mandate.sender.sendMessage(Lang.CANNOT_MANDATE_CHANGED_COMPANY.toString());
				}
			}

			MineCompanyPlugin.mandateConfirms.remove(companyName);

		}
	}

	public static void confirmDelete(Player player) {

		DeleteConfirm temp = MineCompanyPlugin.deleteConfirms.get(player.getName().toLowerCase());

		Company company = CompanyFuns.getCompany(player);

		if (temp != null) {

			if (company != null) {
				if (temp.getCompanyName().equals(company.getName())) {
					MineCompanyPlugin.deleteConfirms.remove(player.getName().toLowerCase());
					CompanyFuns.deleteCompany(company.getName());

					
					MCUtils.broadcast(Lang.withPlaceHolder(Lang.DELETED_COMPANY,
							new String[] { "%company%", "%player%" }, company.getName(), player.getName()));

					MineCompanyPlugin.calcCompanyList();

				} else {
					MineCompanyPlugin.deleteConfirms.remove(player.getName().toLowerCase());
					player.sendMessage(Lang.DELETE_COMPANY_CONFIRM_CANCEL_BY_CHANGE.toString());

				}
			} else {
				player.sendMessage(Lang.NO_COMPANY.toString());
			}

		} else {
			player.sendMessage(Lang.DELETE_COMPANY_CONFIRM_NOTSET.toString());

		}

	}

	public static boolean mustBeOnline(String name, CommandSender sender) {
		if (Bukkit.getPlayerExact(name) != null) {
			return true;
		} else {
			
			sender.sendMessage(Lang.withPlaceHolder(Lang.PLAYER_NOT_ONLINE, "%target%", name));
			return false;
		}

	}

	public static Company getCompany(Player player) {
		String name = MineCompanyPlugin.playerCompanies.get(player.getName().toLowerCase());
		if (name == null) {
			return null;
		} else {
			return MineCompanyPlugin.existCompanies.get(name);
		}

	}

	public static Company getCompany(String player) {
		String name = MineCompanyPlugin.playerCompanies.get(player.toLowerCase());
		if (name == null) {
			return null;
		} else {
			return MineCompanyPlugin.existCompanies.get(name);
		}

	}

	public static Company getCompanyFromName(String name) {
		return MineCompanyPlugin.existCompanies.get(name.toLowerCase());
	}

	public static String getCompanyInfo(Company company, Lang lang) {

		String[] placeholders = new String[] { "%company%", "%leader%", "%sub_leaders%", "%members%", "%size%",
				"%max_size%", "%value%", "%money_for_maintain%", "%maintain_time%", "%unPaid_bal%", "%amount_unpaid%",
				"%max_unPaid%", "%date_of_lastPaid%", "%startDate%" };
		Object[] values = new Object[] { company.getName(), company.getLeader(), company.getSubLeaderList(),
				company.getMemberList(), company.getTeamSize(), company.getMaxSize(), company.getCompanyValue(),
				CompanyFuns.calcKeepPrice(company.getMaxSize()), MineCompanyPlugin.keepTimeString,
				company.getUnpaid_fee_String(), company.getUnpaid_times_String(),
				CompanyFuns.calcAllowedUnpaid(company.getMaxSize()), MCUtils.getTimeForLastPaid(company.lastPaid),
				MCUtils.getDate(company.getStartDate()) };

		String result = Lang.withPlaceHolder(lang, placeholders, values);

		return result;

	}

}
