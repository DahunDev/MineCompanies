package net.daniel.MineCompany.Commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.daniel.MineCompany.Company;
import net.daniel.MineCompany.MineCompanyPlugin;
import net.daniel.MineCompany.Lang;
import net.daniel.MineCompany.MCUtils.MCUtils;
import net.daniel.MineCompany.Utils.CompanyFuns;

public class CompanyCMD implements CommandExecutor, TabCompleter {

    private final MineCompanyPlugin plugin = MineCompanyPlugin.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!MCUtils.mustHasPerm(sender, "MineCompany.use")) {
            return true;
        }

        if (!(args.length >= 1)) {
            sender.sendMessage(Lang.COMPANY_HELP.toString());
            return true;
        }

        new BukkitRunnable() {

            @Override
            public void run() {
                switch (args[0]) {
                    case "정보":

                        if (args.length == 2) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);
                            if (company != null) {
                                sender.sendMessage(CompanyFuns.getCompanyInfo(company, Lang.COMPANY_INFO));

                            } else {

                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_NOT_EXIST_THAT_NAME,
                                        "%company%", args[1]));
                            }

                        } else {
                            if (args.length != 1) {
                                sender.sendMessage(Lang.NO_COMPANY_NAME_SPACE.toString());
                                return;

                            }

                            if (!MCUtils.mustBePlayer(sender)) {
                                return;
                            }

                            Player player = (Player) sender;

                            if (MCUtils.mustHasCompany(player)) {
                                Company company = CompanyFuns.getCompany(player);
                                sender.sendMessage(
                                        CompanyFuns.getCompanyInfo(company, Lang.COMPANY_INFO_MINE));

                            }

                        }

                        break;

                    case "위임":

                        if (MCUtils.mustBePlayer(sender)) {

                            if (args.length != 3) {
                                sender.sendMessage(Lang.MANDATE_HELP.toString());
                                return;

                            }

                            Player player = (Player) sender;

                            if (!MCUtils.mustHasCompany(player)) {
                                return;
                            }

                            Company company = CompanyFuns.getCompany(player);

                            if (!MCUtils.mustBeOwner(player.getName(), company, sender)) {
                                return;
                            }
                            if (!(args[1].equalsIgnoreCase(company.getName()))) {
                                sender.sendMessage(Lang.NOT_MATCH_THE_COMPANY_NAME.toString());
                                return;

                            }
                            if (MCUtils.mustBeOnline(args[2], sender)) {

                                Player target = Bukkit.getPlayerExact(args[2]);
                                CompanyFuns.mandateConfirmAutoExpire(player, target);

                            }


                        }

                        break;

                    case "위임수락":

                        if (MCUtils.mustBePlayer(sender)) {

                            if (args.length != 3) {
                                sender.sendMessage(Lang.MANDATE_ACCEPT_HELP.toString());
                                return;
                            }
                            Player player = (Player) sender;

                            if (MCUtils.mustHasCompany(player)) {

                                Company company = CompanyFuns.getCompany(player);

                                if (!(args[1].equalsIgnoreCase(company.getName()))) {
                                    sender.sendMessage(Lang.NOT_MATCH_THE_COMPANY_NAME.toString());
                                    return;
                                }

                                if (args[2].equalsIgnoreCase(player.getName())) {
                                    CompanyFuns.acceptMandate(player);
                                } else {
                                    sender.sendMessage(Lang.NOT_MATCH_THE_PLAYER_NAME.toString());

                                }

                            }

                        }

                        break;
                    case "위임거절":

                        if (MCUtils.mustBePlayer(sender)) {

                            if (args.length != 3) {
                                sender.sendMessage(Lang.MANDATE_DENY_HELP.toString());
                                return;
                            }

                            Player player = (Player) sender;

                            if (!MCUtils.mustHasCompany(player)) {
                                return;

                            }

                            Company company = CompanyFuns.getCompany(player);

                            if (!(args[1].equalsIgnoreCase(company.getName()))) {
                                sender.sendMessage(Lang.NOT_MATCH_THE_COMPANY_NAME.toString());
                                return;
                            }

                            if (args[2].equalsIgnoreCase(player.getName())) {

                                CompanyFuns.denyMandate(player);

                            } else {
                                sender.sendMessage(Lang.NOT_MATCH_THE_PLAYER_NAME.toString());

                            }


                        }

                        break;
                    case "수락":

                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;
                            CompanyFuns.acceptInvite(player);
                        }

                        break;
                    case "거절":

                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;
                            CompanyFuns.denyInvite(player);
                        }

                        break;
                    case "초대":
                        if (args.length != 2) {
                            sender.sendMessage(Lang.INVITE_HELP.toString());
                            return;

                        }

                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;

                            if (!MCUtils.mustHasCompany(player)) {
                                return;
                            }
                            CompanyFuns.inviteAutoExpire(player.getName(), args[1], player);

                        }
                        break;
                    case "추방":

                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;

                            if (!MCUtils.mustHasCompany(player)) {
                                return;
                            }
                            Company company = CompanyFuns.getCompany(player);

                            if (!MCUtils.mustHasPermforKick(player)) {
                                return;
                            }


                            if (args.length != 2) {
                                sender.sendMessage(Lang.KICK_HELP.toString());
                                return;

                            }

                            if (args[1].equalsIgnoreCase(player.getName()) || company.isLeader(args[1])
                                    || company.isSubLeader(args[1])) {

                                sender.sendMessage(Lang.CANNOT_KICK_ME_LEADER_SUBLEDAERS.toString());

                                break;
                            }

                            if (company.isMember(args[1])) {

                                company.removeMember(args[1]);

                                sender.sendMessage(
                                        Lang.withPlaceHolder(Lang.KICKED_PLAYER, "%target%", args[1]));

                                MCUtils.sendMsgTo(args[1], Lang.withPlaceHolder(
                                        Lang.KICKED_FROM_COMPANY, "%company%", company.getName()));

                            } else {

                                sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_SAME_COMPANY_MEMBER,
                                        "%company%", company.getName()));
                            }

                        }

                        break;
                    case "사원":

                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;
                            if (!MCUtils.mustHasCompany(player)) {
                                return;
                            }

                            Company company = CompanyFuns.getCompany(player);

                            if (MCUtils.mustBeOwner(player.getName(), company, sender)) {

                                if (args.length != 2) {
                                    sender.sendMessage(Lang.COMPANY_SET_SUBLEADER_HELP.toString());
                                    return;
                                }


                                if (company.isLeader(args[1])) {
                                    sender.sendMessage(Lang.CANNOT_SET_LEADER_TO_MEMBER.toString());
                                    break;
                                }

                                if (!company.hasThisPlayer(args[1])) {

                                    sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_SAME_COMPANY_MEMBER,
                                            "%target%", args[1]));
                                    return;

                                }

                                if (company.changeToMember(args[1])) {

                                    MCUtils.sendMsgTo(args[1], Lang.withPlaceHolder(Lang.NOW_MEMBER_ME,
                                            "%company%", company.getName()));

                                    sender.sendMessage(Lang.withPlaceHolder(Lang.NOW_MEMBER_TARGET,
                                            new String[]{"%company%", "%target%"}, company.getName(),
                                            args[1]));

                                } else {

                                    sender.sendMessage(Lang.withPlaceHolder(Lang.FAILED_SET_MEMBER,
                                            new String[]{"%company%", "%target%"}, company.getName(),
                                            args[1]));
                                }
                            }
                        }

                        break;
                    case "부회장":

                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;
                            if (!MCUtils.mustHasCompany(player)) {
                                return;
                            }

                            Company company = CompanyFuns.getCompany(player);

                            if (!MCUtils.mustBeOwner(player.getName(), company, sender)) {
                                return;

                            }

                            if (args.length != 2) {
                                sender.sendMessage(Lang.COMPANY_SET_SUBLEADER_HELP.toString());
                                return;
                            }


                            if (args[1].equalsIgnoreCase(player.getName())) {
                                sender.sendMessage(Lang.CANNOT_CHANGE_RANK_MINE.toString());
                                break;
                            }

                            if (!company.hasThisPlayer(args[1])) {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_SAME_COMPANY_MEMBER,
                                        "%target%", args[1]));
                                return;
                            }

                            if (company.changeToSubLeader(args[1])) {

                                MCUtils.sendMsgTo(args[1], Lang.withPlaceHolder(
                                        Lang.NOW_SUBLEADER_ME, "%company%", company.getName()));

                                sender.sendMessage(Lang.withPlaceHolder(Lang.NOW_SUBLEADER_TARGET,
                                        new String[]{"%company%", "%target%"}, company.getName(),
                                        args[1]));

                            } else {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.FAILED_SET_SUBLEADER,
                                        new String[]{"%company%", "%target%"}, company.getName(),
                                        args[1]));
                            }
                        }

                        break;
                    case "창업":

                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;

                            if (args.length == 2) {

                                if (!MCUtils.mustVaildName(args[1], sender)) {
                                    return;
                                }
                                if (!MCUtils.mustHasNoCompany(player.getName(), player)) {
                                    return;
                                }

                                if (!CompanyFuns.mustNonExistCompany(args[1], sender)) {
                                    return;
                                }
                                if (!MCUtils.mustHasMoney(player, MineCompanyPlugin.startup_min_money,
                                        sender)) {
                                    return;
                                }

                                double price = MineCompanyPlugin.startup_Cost;
                                MineCompanyPlugin.Eco.withdrawPlayer(player, price);

                                if (CompanyFuns.createCompany(args[1], player.getName())) {

                                    sender.sendMessage(Lang.withPlaceHolder(Lang.PAID_MONEY,
                                            "%price%", price));

                                    MCUtils.broadcast(Lang.withPlaceHolder(Lang.CREATED_COMPANY,
                                            new String[]{"%company%", "%player%"}, args[1],
                                            player.getName()));

                                    MineCompanyPlugin.calcCompanyList();

                                } else {
                                    MineCompanyPlugin.Eco.depositPlayer(player, price);

                                    sender.sendMessage(Lang.withPlaceHolder(Lang.REFUND_MONEY,
                                            "%price%", price));

                                    sender.sendMessage(Lang.withPlaceHolder(
                                            Lang.FAILED_CREATE_COMPANY, "%company%", args[1]));

                                }


                            } else if (args.length < 2) {

                                sender.sendMessage(Lang.CREATE_COMPANY_HELP.toString());

                            } else {
                                sender.sendMessage(Lang.NO_COMPANY_NAME_SPACE.toString());
                            }

                        }
                        break;
                    case "탈퇴":
                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;

                            if (MCUtils.mustHasCompany(player)) {

                                Company company = CompanyFuns.getCompany(player);

                                if (company.isLeader(player.getName())) {
                                    sender.sendMessage(Lang.CANNOT_LEAVE_COMPANY_LEADER.toString());
                                    return;
                                }

                                company.removeMember(player.getName());
                                company.removeSubLeader(player.getName());

                                sender.sendMessage(Lang.withPlaceHolder(Lang.LEAVE_COMPANY, "%company%",
                                        company.getName()));
                            }

                        }

                        break;

                    case "이름변경":
                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;

                            if (args.length == 2) {
                                if (!MCUtils.mustHasCompany(player)) {
                                    return;
                                }

                                Company company = CompanyFuns.getCompany(player);
                                String oldName = company.getName();

                                if (!MCUtils.mustBeOwner(player.getName(), company, sender)) {
                                    return;
                                }
                                if (!MCUtils.mustHasMoney(player, MineCompanyPlugin.rename_cost, sender)) {
                                    return;
                                }
                                if (CompanyFuns.renameCompany(company, args[1], sender)) {
                                    MineCompanyPlugin.Eco.withdrawPlayer(player,
                                            MineCompanyPlugin.rename_cost);
                                    sender.sendMessage(Lang.withPlaceHolder(Lang.PAID_MONEY, "%price%",
                                            MineCompanyPlugin.rename_cost));
                                    Bukkit.broadcastMessage(Lang.withPlaceHolder(
                                            Lang.COMPANY_CHNAGED_NAME_BROADCAST,
                                            new String[]{"%player%", "%oldCompany%", "%company%"},
                                            player.getName(), oldName, company.getName()));
                                }

                            } else if (args.length < 2) {
                                sender.sendMessage(Lang.COMPANY_CHNAGE_NAME_HELP.toString());

                            } else {
                                sender.sendMessage(Lang.NO_COMPANY_NAME_SPACE.toString());
                            }

                        }

                        break;

                    case "가치입금":

                        if (args.length != 2) {
                            sender.sendMessage(Lang.COMPANY_ADD_VALUE_HELP.toString());
                            return;
                        }

                        if (MCUtils.mustBeNumber(args[1], sender)) {
                            double input = Double.parseDouble(args[1]);
                            if (!MCUtils.mustPosNum(input, sender)) {
                                return;
                            }
                            if (!MCUtils.mustBePlayer(sender)) {
                                return;

                            }
                            Player player = (Player) sender;

                            if (!MCUtils.mustHasCompany(player)) {
                                return;

                            }

                            Company company = CompanyFuns.getCompany(player);

                            if (MCUtils.mustHasMoney(player, input, sender)) {
                                company.addCompanyValue(input);
                                MineCompanyPlugin.Eco.withdrawPlayer(player, input);
                                sender.sendMessage(
                                        Lang.withPlaceHolder(Lang.PAID_MONEY, "%price%", input));
                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_ADD_VALUE,
                                        new String[]{"%company%", "%amount%", "%value%"},
                                        company.getName(), input, company.getCompanyValue()));

                            }
                        }

                        break;

                    case "해체":
                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;
                            if (!MCUtils.mustHasCompany(player)) {
                                return;
                            }
                            Company company = CompanyFuns.getCompany(player);

                            if (!MCUtils.mustBeOwner(player.getName(), company, sender)) {
                                return;

                            }

                            if (!(args.length >= 2)) {
                                CompanyFuns.deleteConfirmAutoExpire(player);
                                return;
                            }

                            if (args[1].equals("확인")) {
                                CompanyFuns.confirmDelete(player);
                            } else {
                                sender.sendMessage(Lang.DELETE_COMPANY_CMD_HELP.toString());
                            }
                        }

                        break;
                    case "목록": {
                        int size = MineCompanyPlugin.sortedCompanyList.size();
                        int perPage = MineCompanyPlugin.plugin.getConfig().getInt("companies-per-page", 15);
                        int maxPage = size / perPage;
                        if (maxPage * perPage < size) {
                            maxPage++;
                        }
                        String maxPageString = String.valueOf(maxPage);
                        if (maxPage < 1) {
                            maxPageString = "1";
                        }

                        if (args.length == 2) {

                            if (!MCUtils.mustBeInteger(args[1], sender)) {
                                return;
                            }

                            int page = Integer.parseInt(args[1]);
                            SimpleDateFormat format = new SimpleDateFormat(
                                    Lang.TIME_FORMAT_FOR_LIST.toString());
                            String time = format.format(MineCompanyPlugin.getLastsortedTime());
                            if (page == 1 && size == 0) {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                        new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"},
                                        time, Lang.EMPTY_LIST, 1, maxPageString));

                                break;
                            } else if (page > 0 && page <= maxPage) {

                                String list = Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                        new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"},
                                        time, MCUtils.getCompanyListString(page, maxPage, perPage,
                                                Lang.COMPANY_LIST_LINE),
                                        page, maxPageString);

                                sender.sendMessage(list);
                            } else {

                                sender.sendMessage(
                                        Lang.withPlaceHolder(Lang.INVAILED_PAGE, "%max%", maxPageString));
                            }
                        } else if (args.length == 1) {
                            SimpleDateFormat format = new SimpleDateFormat(Lang.TIME_FORMAT_FOR_LIST.toString());
                            String time = format.format(MineCompanyPlugin.getLastsortedTime());
                            if (size <= 0) {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                        new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"}, time,
                                        Lang.EMPTY_LIST, 1, maxPageString));
                                return;
                            }
                            String list = Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                    new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"}, time,
                                    MCUtils.getCompanyListString(1, maxPage, perPage, Lang.COMPANY_LIST_LINE),
                                    1, maxPageString);

                            sender.sendMessage(list);

                        } else {
                            sender.sendMessage(Lang.COMPANY_LIST_ADMIN_HELP.toString());
                        }

                    }
                    break;

                    case "유지비계산":
                        if (args.length != 2) {
                            sender.sendMessage(Lang.CALC_MAINTAIN_FEE_HELP.toString());
                            return;
                        }

                        if (!MCUtils.isInteger(args[1])) {
                            sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_INTEGER, "%value%", args[1]));
                            return;
                        }
                        int max = MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company", 1000);
                        int min = MineCompanyPlugin.plugin.getConfig().getInt("Company.defaultSize", 8);
                        int size = Integer.parseInt(args[1]);
                        if (size >= min && size <= max) {
                            double price = CompanyFuns.calcKeepPrice(size);
                            sender.sendMessage(Lang.withPlaceHolder(Lang.MAINTAIN_FEE_INFO,
                                    new String[]{"%size%", "%price%", "%interval%", "%max_unPaid%"},
                                    size, price, MineCompanyPlugin.keepTimeString,
                                    CompanyFuns.calcAllowedUnpaid(size)));

                        } else {

                            sender.sendMessage(Lang.withPlaceHolder(Lang.INVAILED_MAXSIZE,
                                    new String[]{"%max%", "%min%"}, max, min));
                        }

                        break;

                    case "조회":
                        if (args.length == 2) {
                            Company company = CompanyFuns.getCompany(args[1]);
                            if (company != null) {

                                sender.sendMessage(Lang.withPlaceHolder(Lang.PLAYER_LOOKUP_COMPANY,
                                        new String[]{"%target%", "%company%"}, args[1], company.getName()));
                            } else {

                                sender.sendMessage(
                                        Lang.withPlaceHolder(Lang.PLAYER_HAS_NO_COMPANY, "%target%", args[1]));
                            }

                        } else {
                            sender.sendMessage(Lang.LOOKUP_PLAYER_COMPANY_HELP.toString());
                        }

                        break;
                    case "추가":
                        if (MCUtils.mustBePlayer(sender)) {
                            Player player = (Player) sender;
                            if (!MCUtils.mustHasCompany(player)) {
                                return;
                            }
                            Company company = CompanyFuns.getCompany(player);

                            if (!MCUtils.mustBeOwner(player.getName(), company, sender)) {
                                return;
                            }


                            int maxSize = MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company",
                                    1000);

                            if (company.getMaxSize() < maxSize) {

                                double price = CompanyFuns.calcAddMaxPrice(company.getMaxSize());

                                if (price == Double.NaN) {
                                    sender.sendMessage(Lang.ERROR_CALC_PRICE.toString());
                                    return;
                                }

                                if (MCUtils.mustHasMoney(player, price, sender)) {
                                    MineCompanyPlugin.Eco.withdrawPlayer(player, price);

                                    sender.sendMessage(
                                            Lang.withPlaceHolder(Lang.PAID_MONEY, "%price%", price));
                                    company.addMaxSize();

                                    MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_SET_MAXSIZE,
                                            new String[]{"%company%", "%size%"}, company.getName(),
                                            company.getMaxSize()));
                                }
                            } else {

                                sender.sendMessage(Lang.withPlaceHolder(Lang.CANNOT_ADD_MAXSIZE_LIMIT,
                                        "%max%", maxSize));
                            }
                        }

                        break;

                    default:
                        sender.sendMessage(Lang.COMPANY_HELP.toString());

                        break;
                }
            }

        }.runTaskAsynchronously(plugin);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length < 2) {

            list.add("정보");
            list.add("위임");
            list.add("위임수락");
            list.add("위임거절");
            list.add("수락");
            list.add("거절");
            list.add("초대");
            list.add("추방");
            list.add("사원");
            list.add("부회장");
            list.add("창업");
            list.add("탈퇴");
            list.add("해체");
            list.add("목록");
            list.add("조회");
            list.add("추가");
            list.add("유지비계산");
            list.add("이름변경");

            String finalArg = args[args.length - 1];
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().startsWith(finalArg)) {
                    it.remove();
                }
            }

            return list;
        } else {
            return null; // Default completion

        }

    }

}