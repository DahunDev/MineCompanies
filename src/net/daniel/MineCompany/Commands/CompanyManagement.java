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
import org.bukkit.scheduler.BukkitRunnable;

import net.daniel.MineCompany.Company;
import net.daniel.MineCompany.MineCompanyPlugin;
import net.daniel.MineCompany.Lang;
import net.daniel.MineCompany.MCUtils.MCUtils;
import net.daniel.MineCompany.Utils.CompanyFuns;

public class CompanyManagement implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (MCUtils.mustHasPerm(sender, "MineCompany.Admin")) {
            sender.sendMessage(Lang.NO_PERM.toString());
            return true;

        }
        if (args.length < 1) {
            sender.sendMessage(Lang.COMPANY_MANAGE_HELP.toString());
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
                            sender.sendMessage(Lang.COMPANY_INFO_ADMIN_HELP.toString());
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

                    case "목록-소유자":
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

                        SimpleDateFormat format = new SimpleDateFormat(Lang.TIME_FORMAT_FOR_LIST.toString());
                        String time = format.format(MineCompanyPlugin.getLastsortedTime());
                        if (args.length == 2) {

                            if (!MCUtils.mustBeInteger(args[1], sender)) {
                                return;
                            }
                            int page = Integer.parseInt(args[1]);
                            if (page == 1 && size == 0) {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                        new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"},
                                        time, Lang.EMPTY_LIST, 1, maxPageString));

                                break;
                            } else if (page > 0 && page <= maxPage) {

                                String list = Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                        new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"},
                                        time, MCUtils.getCompanyListString(page, maxPage, perPage,
                                                Lang.NAME_WITH_OWNER),
                                        page, maxPageString);

                                sender.sendMessage(list);

                            } else {

                                sender.sendMessage(
                                        Lang.withPlaceHolder(Lang.INVAILED_PAGE, "%max%", maxPageString));
                            }
                        } else if (args.length == 1) {

                            if (size <= 0) {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                        new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"}, time,
                                        Lang.EMPTY_LIST, 1, maxPageString));
                                return;
                            }

                            String list = Lang.withPlaceHolder(Lang.COMPANY_LIST,
                                    new String[]{"%time%", "%company_list%", "%page%", "%maxPage%"}, time,
                                    MCUtils.getCompanyListString(1, maxPage, perPage, Lang.NAME_WITH_OWNER), 1,
                                    maxPageString);

                            sender.sendMessage(list);
                        } else {
                            sender.sendMessage(Lang.COMPANY_LIST_WITH_OWNER_HELP.toString());
                        }
                        break;

                    case "조회":
                        if (args.length == 2) {
                            Company company = CompanyFuns.getCompany(args[1]);
                            if (company == null) {
                                sender.sendMessage(
                                        Lang.withPlaceHolder(Lang.PLAYER_HAS_NO_COMPANY, "%target%", args[1]));
                                return;
                            }
                            sender.sendMessage(Lang.withPlaceHolder(Lang.PLAYER_LOOKUP_COMPANY,
                                    new String[]{"%target%", "%company%"}, args[1], company.getName()));
                        } else {
                            sender.sendMessage(Lang.LOOKUP_PLAYER_COMPANY_HELP.toString());
                        }

                        break;

                    case "회장이전":

                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_ADMIN_MANDATE_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (company.isMember(args[2]) || company.isSubLeader(args[2])) {
                                String leaderBefore = company.getLeader();

                                company.changeLeader(leaderBefore, args[2]);


                                MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_FORCE_MANDATE_DONE,
                                        new String[]{"%company%", "%from%", "%after%"}, company.getName(),
                                        leaderBefore, args[2]));

                            } else if (company.isLeader(args[2])) {


                                sender.sendMessage(Lang.withPlaceHolder(Lang.ALREADY_HAS_COMPANY,
                                        new String[]{"%target%", "%company%"}, args[2], args[1]));
                            } else {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.ADMIN_CANNOT_MANDATE_NOT_MEMEBER,
                                        new String[]{"%target%", "%company%"}, args[2], args[1]));


                            }

                        }

                        break;

                    case "해체":

                        if (args.length == 2) {
                            if (CompanyFuns.mustExistCompany(args[1], sender)) {
                                CompanyFuns.deleteCompany(args[1]);


                                MCUtils.broadcast(Lang.withPlaceHolder(Lang.DELETED_COMPANY_ADMIN,
                                        new String[]{"%company%", "%player%"}, args[1], sender.getName()));
                            }
                        } else {
                            sender.sendMessage(Lang.COMPANY_ADMIN_DELETE_COMPANY_HELP.toString());
                        }

                        break;

                    case "추가":

                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_ADMIN_ADD_MEMBER_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustHasNoCompany(args[2], sender)) {
                                return;
                            }
                            if (!(company.getMaxSize() > company.getTeamSize())) {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.CANNOT_ADD_MAXSIZE_LIMIT,
                                        "%max%", company.getMaxSize()));
                                return;

                            }

                            company.addMember(args[2]);


                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_FORCE_ADD_MEMBER,
                                    new String[]{"%target%", "%company%"}, args[2],
                                    company.getName()));


                            MCUtils.sendMsgTo(args[2],
                                    Lang.withPlaceHolder(Lang.COMPANY_FORCE_ADD_MEMBER_TO_TARGET,
                                            "%company%", company.getName()));
                        }

                        break;

                    case "창설":

                        if (args.length == 3) {

                            if (!MCUtils.mustVaildName(args[1], sender)) {
                                return;
                            }
                            if (!MCUtils.mustHasNoCompany(args[2], sender)) {
                                return;
                            }
                            if (!CompanyFuns.mustNonExistCompany(args[1], sender)) {
                                return;
                            }
                            if (!CompanyFuns.createCompany(args[1], args[2])) {
                                sender.sendMessage(Lang.withPlaceHolder(Lang.FAILED_CREATE_COMPANY,
                                        "%company%", args[1]));
                                return;
                            }

                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.CREATED_COMPANY_ADMIN,
                                    new String[]{"%company%", "%player%", "%target%"}, args[1],
                                    sender.getName(), args[2]));

                            MineCompanyPlugin.calcCompanyList();
                        } else if (args.length < 3) {

                            sender.sendMessage(Lang.COMPANY_CREATE_ADMIN_HELP.toString());

                        } else {
                            sender.sendMessage(Lang.NO_COMPANY_NAME_SPACE.toString());
                        }
                        break;


                    case "삭제":

                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_ADMIN_REMOVE_MEMBER_HELP.toString());
                            return;
                        }

                        if (!CompanyFuns.mustExistCompany(args[1], sender)) {
                            return;
                        }

                    {
                        Company company = CompanyFuns.getCompanyFromName(args[1]);
                        if (!company.hasThisPlayer(args[2])) {
                            sender.sendMessage(Lang.withPlaceHolder(Lang.NOT_MEMBER_TARGET,
                                    new String[]{"%target%", "%company%"}, args[2], company.getName()));
                            return;
                        }
                        if (!company.isLeader(args[2])) {
                            company.removeMember(args[2]);
                            company.removeSubLeader(args[2]);


                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_FORCE_REMOVE_MEMBER,
                                    new String[]{"%target%", "%company%"}, args[2],
                                    company.getName()));

                            MCUtils.sendMsgTo(args[2],
                                    Lang.withPlaceHolder(Lang.COMPANY_FORCE_REMOVE_MEMBER_TARGET,
                                            "%company%", company.getName()));

                        } else {
                            sender.sendMessage(
                                    Lang.withPlaceHolder(Lang.CANNOT_KICK_LEADER, "%target%", args[2]));
                        }
                    } //need this bracket to limit scope for company
                    break;

                    case "최대인원":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_ADMIN_MAXSIZE_HELP.toString());
                            return;
                        }

                        if (!MCUtils.mustBeInteger(args[2], sender)) {
                            return;
                        }

                        int max = MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company", 1000);
                        int min = MineCompanyPlugin.plugin.getConfig().getInt("Company.defaultSize", 8);
                        int maxSize = Integer.parseInt(args[2]);

                        if (!(maxSize >= min && maxSize <= max)) {
                            sender.sendMessage(Lang.withPlaceHolder(Lang.INVAILED_MAXSIZE,
                                    new String[]{"%max%", "%min%"}, max, min));
                            return;
                        }

                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);
                            if (maxSize >= company.getTeamSize()) {
                                company.setMaxSize(maxSize);

                                MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_FORCE_SET_MAXSIZE,
                                        new String[]{"%player%", "%company%", "%size%"},
                                        sender.getName(), company.getName(), args[2]));

                            } else {
                                sender.sendMessage(
                                        Lang.withPlaceHolder(Lang.INVAILED_MAXSIZE_NOT_ENOUGH, "%size%",
                                                company.getTeamSize()));
                            }

                        }

                        break;

                    case "설정리로드":
                        MineCompanyPlugin.plugin.reloadConfiguration();

                        sender.sendMessage(Lang.RELOADED_CONFIG.toString());

                        break;

                    case "데이터리로드":
                        MineCompanyPlugin.plugin.loadCompanyData();

                        sender.sendMessage(Lang.RELOADED_DATA.toString());
                        break;

                    case "가치금설정":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_SET_VALUE_ADMIN_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);
                            if (!MCUtils.mustBeNumber(args[2], sender)) {
                                return;
                            }

                            double bal = Double.parseDouble(args[2]);

                            if (MCUtils.mustPosNum(bal, sender)) {
                                company.setCompanyValue(bal);
                                MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_SET_VALUE_BY_ADMIN,
                                        new String[]{"%player%", "%company%", "%value%"},
                                        sender.getName(), company.getName(), args[2]));
                            }
                        }

                        break;

                    case "가치금추가":

                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_ADD_VALUE_ADMIN_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeNumber(args[2], sender)) {
                                return;
                            }
                            double bal = Double.parseDouble(args[2]);
                            if (MCUtils.mustPosNum(bal, sender)) {
                                company.addCompanyValue(bal);

                                MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_ADD_VALUE_BY_ADMIN,
                                        new String[]{"%player%", "%company%", "%amount%"},
                                        sender.getName(), company.getName(), args[2]));

                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_ADD_VALUE,
                                        new String[]{"%company%", "%amount%", "%value%"},
                                        company.getName(), args[2], company.getCompanyValue()));


                            }
                        }

                        break;

                    case "가치금차감":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_SUBTRACT_VALUE_ADMIN_HELP.toString());
                            return;
                        }

                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeNumber(args[2], sender)) {
                                return;
                            }
                            double bal = Double.parseDouble(args[2]);
                            if (MCUtils.mustPosNum(bal, sender)) {
                                MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_SUBTRACT_VALUE_BY_ADMIN,
                                        new String[]{"%player%", "%company%", "%amount%"},
                                        sender.getName(), company.getName(), args[2]));

                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_SUBTRACT_VALUE,
                                        new String[]{"%company%", "%amount%", "%value%"},
                                        company.getName(), args[2], company.getCompanyValue()));
                            }

                        }

                        break;

                    case "미납금추가":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_ADD_UNPAID_ADMIN_HELP.toString());
                            return;
                        }

                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeNumber(args[2], sender)) {
                                return;
                            }
                            double bal = Double.parseDouble(args[2]);
                            if (MCUtils.mustPosNum(bal, sender)) {
                                company.addunPaidValue(bal);

                                MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_ADD_UNPAID_BY_ADMIN,
                                        new String[]{"%player%", "%company%", "%amount%"},
                                        sender.getName(), company.getName(), args[2]));

                                sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_ADD_UNPAID,
                                        new String[]{"%company%", "%amount%", "%unPaid%"},
                                        company.getName(), args[2], company.getUnPaid()));

                            }
                        }

                        break;

                    case "미납금차감":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_SUBTRACT_UNPAID_ADMIN_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeNumber(args[2], sender)) {
                                return;
                            }
                            double bal = Double.parseDouble(args[2]);
                            if (!MCUtils.mustPosNum(bal, sender)) {
                                return;
                            }
                            company.subtractunPaidValue(bal);

                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_ADD_UNPAID_BY_ADMIN,
                                    new String[]{"%player%", "%company%", "%amount%"},
                                    sender.getName(), company.getName(), args[2]));

                            sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_SUBTRACT_UNPAID,
                                    new String[]{"%company%", "%amount%", "%unPaid%"},
                                    company.getName(), args[2], company.getUnPaid()));
                        }

                        break;
                    case "미납금설정":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_SET_UNPAID_ADMIN_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeNumber(args[2], sender)) {
                                return;

                            }
                            double bal = Double.parseDouble(args[2]);
                            if (!MCUtils.mustPosNum(bal, sender)) {
                                return;
                            }
                            company.setUnPaid(bal);

                            sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_SUBTRACT_UNPAID,
                                    new String[]{"%company%", "%amount%", "%unPaid%"},
                                    company.getName(), args[2], company.getUnPaid()));
                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_SUBTRACT_UNPAID,
                                    new String[]{"%player%", "%company%", "%unPaid%"},
                                    sender.getName(), company.getName(), args[2]));
                        }
                        break;

                    case "미납횟수추가":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_ADD_UNPAID_TIMES_ADMIN_HELP.toString());
                            return;
                        }

                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeInteger(args[2], sender)) {
                                return;
                            }
                            int times = Integer.parseInt(args[2]);
                            if (!MCUtils.mustPosInt(times, sender)) {
                                return;
                            }
                            company.addUnpaid_times(times);
                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_ADD_UNPAID_TIMES_BY_ADMIN,
                                    new String[]{"%player%", "%company%", "%amount%"},
                                    sender.getName(), company.getName(), args[2]));


                            sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_ADD_UNPAID_TIMES,
                                    new String[]{"%company%", "%amount%", "%unPaid_times%"},
                                    company.getName(), args[2], company.getUnpaid_times_String()));
                        }
                        break;

                    case "미납횟수차감":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_SUBTRACT_UNPAID_TIMES_ADMIN_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeInteger(args[2], sender)) {
                                return;

                            }
                            int times = Integer.parseInt(args[2]);
                            if (!MCUtils.mustPosInt(times, sender)) {
                                return;
                            }
                            company.subtractUnpaid_times(times);

                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_SUBTRACT_UNPAID_TIMES_BY_ADMIN,
                                    new String[]{"%player%", "%company%", "%amount%"},
                                    sender.getName(), company.getName(), args[2]));

                            sender.sendMessage(Lang.withPlaceHolder(Lang.COMPANY_SUBTRACT_UNPAID_TIMES,
                                    new String[]{"%company%", "%amount%", "%unPaid_times%"},
                                    company.getName(), args[2], company.getUnpaid_times_String()));
                        }
                        break;

                    case "미납횟수설정":
                        if (args.length != 3) {
                            sender.sendMessage(Lang.COMPANY_SET_UNPAID_TIMES_ADMIN_HELP.toString());
                            return;
                        }
                        if (CompanyFuns.mustExistCompany(args[1], sender)) {
                            Company company = CompanyFuns.getCompanyFromName(args[1]);

                            if (!MCUtils.mustBeInteger(args[2], sender)) {
                                return;
                            }
                            int times = Integer.parseInt(args[2]);
                            if (!MCUtils.mustPosInt(times, sender)) {
                                return;
                            }
                            company.setUnPaid_times(times);

                            MCUtils.broadcast(Lang.withPlaceHolder(Lang.COMPANY_SET_UNPAID_TIMES_BY_ADMIN,
                                    new String[]{"%player%", "%company%", "%unPaid_times%"},
                                    sender.getName(), company.getName(), args[2]));
                        }
                        break;

                    case "데이터저장":
                        MineCompanyPlugin.CompanyYML.saveData();
                        sender.sendMessage(Lang.COMPANY_DATA_SAVED.toString());
                        break;


                    case "순위정렬":
                        long start = System.nanoTime();
                        MineCompanyPlugin.calcCompanyList();
                        long end = System.nanoTime();

                        double took = (end - start) / 1000000.0;
                        sender.sendMessage(Lang.CALC_RANK_DONE.toString().replaceAll("%ms%", String.format("%.2f", took)));
                        break;

                    case "이름변경":

                        if (args.length == 3) {

                            if (CompanyFuns.mustExistCompany(args[1], sender)) {
                                Company company = CompanyFuns.getCompanyFromName(args[1]);
                                String oldName = company.getName();

                                if (!CompanyFuns.renameCompany(company, args[2], sender)) {
                                    return;
                                }
                                Bukkit.broadcastMessage(
                                        Lang.withPlaceHolder(Lang.COMPANY_CHNAGED_NAME_BROADCAST_ADMIN,
                                                new String[]{"%player%", "%oldCompany%", "%company%"},
                                                sender.getName(), oldName, company.getName()));
                            }

                        } else if (args.length < 3) {
                            sender.sendMessage(Lang.COMPANY_CHNAGE_NAME_ADMIN_HELP.toString());

                        } else {
                            sender.sendMessage(Lang.NO_COMPANY_NAME_SPACE.toString());
                        }
                        break;


                    default:
                        sender.sendMessage(Lang.COMPANY_MANAGE_HELP.toString());
                        break;
                }
            }

        }.runTaskAsynchronously(MineCompanyPlugin.plugin);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length < 2) {

            list.add("창설");
            list.add("회장이전");
            list.add("해체");
            list.add("정보");
            list.add("목록");
            list.add("목록-소유자");

            list.add("추가");
            list.add("삭제");
            list.add("최대인원");
            list.add("조회");
            list.add("순위정렬");

            list.add("데이터리로드");
            list.add("설정리로드");

            list.add("가치금설정");
            list.add("가치금추가");
            list.add("가치금차감");
            list.add("미납금추가");
            list.add("미납금차감");

            list.add("미납금설정");
            list.add("미납횟수추가");
            list.add("미납횟수차감");
            list.add("미납횟수설정");
            list.add("데이터저장");
            list.add("이름변경");
            String finalArg = args[args.length - 1];
            list.removeIf(s -> !s.startsWith(finalArg));

            return list;
        } else {
            return null; // Default completion

        }

    }

}