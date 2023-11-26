package net.daniel.MineCompany.MCUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.daniel.MineCompany.Company;
import net.daniel.MineCompany.MineCompanyPlugin;
import net.daniel.MineCompany.Lang;
import net.daniel.MineCompany.Utils.CompanyFuns;
import net.daniel.MineCompany.data.MandateHolder;

public class MCUtils {

	public static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public static boolean isLong(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public static boolean isNumber(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public static void replaceAll(StringBuffer builder, String from, String to) {

		if ((!isLong(to)) && isNumber(to)) {
			double val = Double.parseDouble(to);
			to = String.format("%.1f", val);
		}

		int index = builder.indexOf(from);
		while (index != -1) {
			builder.replace(index, index + from.length(), to);
			index += to.length(); // Move to the end of the replacement
			index = builder.indexOf(from, index);
		}
	}

	public static void replaceAll(StringBuffer builder, String from, Object to) {

		int index = builder.indexOf(from);
		String temp = String.valueOf(to);

		if ((!isLong(temp)) && isNumber(temp)) {
			double val = Double.parseDouble(temp);
			temp = String.format("%.1f", val);
		}
		while (index != -1) {

			builder.replace(index, index + from.length(), temp);
			index += temp.length(); // Move to the end of the replacement
			index = builder.indexOf(from, index);
		}
	}

	public static boolean mustHasMoney(Player player, double price, CommandSender sender) {

		double balance = MineCompanyPlugin.Eco.getBalance(player);
		if (balance >= price) {
			return true;
		} else {
			sender.sendMessage(
					Lang.NO_ENOUGH_MONEY.toString().replaceAll("%money_need%", String.format("%.1f", price - balance)));
			return false;
		}

	}

	public static void broadcast(String msg) {
		Bukkit.getScheduler().runTask(MineCompanyPlugin.plugin, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage(msg);
				// Bukkit.broadcastMessage is not thread-safe for async
			}
		});
	}

	public static boolean mustVaildName(String name, CommandSender sender) {

		int maxSize = MineCompanyPlugin.plugin.getConfig().getInt("Company.max-chars", 12);

		List<String> banned = MineCompanyPlugin.plugin.getConfig().getStringList("Banned-characters");
		String name_temp = name.toLowerCase();

		if (name.length() > maxSize) {

			sender.sendMessage(Lang.withPlaceHolder(Lang.TOO_LONG_LONG, new String[] { "%max%", "%chars%" }, maxSize,
					name.length()));
			return false;
		}

		if (name.length() >= 2 && name.substring(0, name.length() - 2).contains("회사")) {
			sender.sendMessage(Lang.NO_COMPANY_NAME_COMPANY.toString());
			return false;
		}

		String colored = ChatColor.translateAlternateColorCodes('&', name);
		int size = ChatColor.stripColor(colored).length();

		if (size != name.length()) {
			sender.sendMessage(Lang.NAME_CANNOT_HAVE_COLORCODE.toString());
			return false;
		}

		for (String chars : banned) {
			if (name_temp.contains(chars)) {

				sender.sendMessage(Lang.withPlaceHolder(Lang.CONTAINS_BANNED_CHAR, "%banned_word%", chars));
				return false;
			}

		}

		return true;

	}

	public static boolean mustBePlayer(CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		} else {
			sender.sendMessage(Lang.ONLY_PLAYER.toString());
			return false;
		}

	}

	public static boolean mustBeInteger(String input, CommandSender sender) {
		if (isInteger(input)) {
			return true;
		} else {

			sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_INTEGER, "%value%", input));
			return false;
		}

	}

	public static boolean mustBeNumber(String input, CommandSender sender) {
		if (isNumber(input)) {
			return true;
		} else {
			sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_NUMBER, "%value%", input));
			return false;
		}

	}

	public static boolean mustPosInt(int input, CommandSender sender) {
		if (input >= 0) {
			return true;
		} else {
			sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_POSITIVE_INTEGER, "%value%", input));
			return false;
		}

	}

	public static boolean mustPosNum(double num, CommandSender sender) {
		if (num >= 0) {
			return true;
		} else {
			sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_POSITIVE_NUMBER, "%value%", num));
			return false;
		}

	}

	public static boolean mustHasCompany(String player, CommandSender sender) {
		if (MineCompanyPlugin.playerCompanies.get(player.toLowerCase()) != null) {
			return true;
		} else {
			if (player.equalsIgnoreCase(sender.getName())) {
				sender.sendMessage(Lang.NO_COMPANY.toString());
			} else {
				sender.sendMessage(Lang.withPlaceHolder(Lang.PLAYER_HAS_NO_COMPANY, "%target%", player));
			}

			return false;
		}

	}

	public static boolean mustHasNoCompany(String player, CommandSender sender) {
		if (MineCompanyPlugin.playerCompanies.get(player.toLowerCase()) == null) {
			return true;
		} else {
			if (player.equalsIgnoreCase(sender.getName())) {
				sender.sendMessage(Lang.ALREADY_HAS_COMPANY_ME.toString());
			} else {

				sender.sendMessage(Lang.withPlaceHolder(Lang.ALREADY_HAS_COMPANY, "%target%", player));
			}

			return false;
		}

	}

	public static boolean mustHasCompany(Player p, CommandSender sender) {
		return mustHasCompany(p.getName(), sender);
	}

	/**
	 * @param 플레이어 p가 회사에 소속된 여부, 소속되지 않은 경우 p에게 소속된 회사가 있어야한다고 메세지 전송
	 * @return 플레이어 p가 회사에 소속된 여부,
	 */
	public static boolean mustHasCompany(Player p) {
		return mustHasCompany(p.getName(), p);
	}

	public static boolean mustBeOnline(String name, CommandSender sender) {
		if (Bukkit.getPlayerExact(name) != null) {
			return true;
		} else {

			sender.sendMessage(Lang.withPlaceHolder(Lang.PLAYER_NOT_ONLINE, "%target%", name));
			return false;
		}

	}

	public static boolean mustHasPerm(CommandSender sender, String perm) {

		if (sender.hasPermission(perm)) {
			return true;
		} else {
			sender.sendMessage(Lang.NO_PERM.toString());
			return false;
		}

	}

	public static String getStringFromArgs(String[] args, int startIndex) {
		StringBuilder str = new StringBuilder();
		for (int i = startIndex; i < args.length; i++) {
			str.append(args[i] + " ");
		}

		String msg = ChatColor.translateAlternateColorCodes('&', str.toString());
		msg = removeLastChar(msg);
		return msg;

	}

	public static String removeLastChar(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return str.substring(0, str.length() - 1);
	}

	public static boolean mustBeOwner(String player, Company company, CommandSender sender) {
		if (company.isLeader(player)) {
			return true;
		} else {
			sender.sendMessage(Lang.ONLY_LEADER.toString());
			return false;
		}
	}

	public static boolean mustMandateRequested(Player sender) {
		if (MCUtils.mustHasCompany(sender)) {
			Company company = CompanyFuns.getCompany(sender);

			MandateHolder temp = MineCompanyPlugin.mandateConfirms.get(company.getName());
			if (temp != null) {

				if (temp.target.equalsIgnoreCase(sender.getName())) {
					return true;
				} else {
					sender.sendMessage(Lang.MANDATE_LEADER_NOT_REQUESTED.toString());

				}

			} else {
				sender.sendMessage(Lang.MANDATE_LEADER_NOT_REQUESTED.toString());

			}

		}
		return false;

	}

	public static void sendMsgTo(String target, String message) {
		Player p = Bukkit.getPlayerExact(target);
		if (p != null) {
			p.sendMessage(message);
		}
	}

	public static String getDate(long ms) {
		DateFormat format = new SimpleDateFormat(Lang.DATE_FORMAT.toString());

		return format.format(ms);

	}

	public static String getTimeForList(long ms) {
		DateFormat format = new SimpleDateFormat(Lang.TIME_FORMAT_FOR_LIST.toString());
		return format.format(ms);

	}

	public static String getTimeForLastPaid(long ms) {
		DateFormat format = new SimpleDateFormat(Lang.TIME_FORMAT_FOR_LASTPAID.toString());
		return format.format(ms);

	}

	public static boolean mustHasPermforInvite(Player player) {
		if (mustHasCompany(player)) {
			Company company = CompanyFuns.getCompany(player);

			if (company.isLeader(player.getName()) || company.isSubLeader(player.getName())) {
				return true;
			} else {
				player.sendMessage(Lang.CANNOT_INVITE_NO_PERM.toString());
				return false;
			}
		}

		return false;
	}

	public static boolean mustHasPermforKick(Player player) {
		if (mustHasCompany(player)) {
			Company company = CompanyFuns.getCompany(player);

			if (company.isLeader(player.getName()) || company.isSubLeader(player.getName())) {
				return true;
			} else {
				player.sendMessage(Lang.CANNOT_KICK_NO_PERM.toString());
				return false;
			}
		}

		return false;
	}

	public static String getCompanyListString(int page, int maxPage, int perPage, Lang infoLine) {
		int maxIndex = perPage * page;
		int size = MineCompanyPlugin.sortedCompanyList.size();
		if (size < maxIndex) {
			maxIndex = size;
		}

		int start = perPage * (page - 1);
		StringBuffer companyList = new StringBuffer();

		if (start >= maxIndex) {
			return Lang.EMPTY_LIST.toString();
		}

		for (int i = start; i < maxIndex; i++) {
			Company company = MineCompanyPlugin.sortedCompanyList.get(i);

			StringBuffer line = new StringBuffer(infoLine.toString());
			MCUtils.replaceAll(line, "%rank%", i + 1);
			MCUtils.replaceAll(line, "%company%", company.getName());
			MCUtils.replaceAll(line, "%leader%", company.getLeader());
			MCUtils.replaceAll(line, "%values%", company.getCompanyValue());

			if (i < (maxIndex - 1)) {
				companyList.append(line + "\n");

			} else {
				companyList.append(line);
			}

		}
		return companyList.toString();
	}

}
