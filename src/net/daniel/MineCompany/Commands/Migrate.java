package net.daniel.MineCompany.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.daniel.MineCompany.Company;
import net.daniel.MineCompany.MineCompanyPlugin;
import net.daniel.MineCompany.Lang;
import net.daniel.MineCompany.MCUtils.MCUtils;
import net.daniel.MineCompany.Utils.CompanyFuns;

public class Migrate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!MCUtils.mustHasPerm(sender, "MineCompany.Admin.migrate")) {
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(Lang.MIGRATE_PLAYER_HELP.toString());
            return true;
        }

        String from = args[0];
        String to = args[1];
        Company company = CompanyFuns.getCompany(from);

        if (company == null) {
            sender.sendMessage(Lang.withPlaceHolder(Lang.PLAYER_HAS_NO_COMPANY, "%target%", from));
            return true;
        }

        if (CompanyFuns.getCompany(to) != null) {
            sender.sendMessage(Lang.withPlaceHolder(Lang.ALREADY_HAS_COMPANY, "%target%", to));
            return true;
        }

        if (company.isMember(from)) {

            company.removeMember(from);
            company.addMember(to);

            sender.sendMessage(Lang.withPlaceHolder(Lang.MIGRATED_PLAYER_MEMBER,
                    new String[]{"%before%", "%company%", "%after%"}, from, company.getName(), to));

        }

        if (company.isSubLeader(from)) {

            company.removeSubLeader(from);
            company.addMember(to);
            company.changeToSubLeader(to);

            sender.sendMessage(Lang.withPlaceHolder(Lang.MIGRATED_PLAYER_SUBLEADER,
                    new String[]{"%before%", "%company%", "%after%"}, from, company.getName(), to));

        }

        if (company.isLeader(from)) {
            MineCompanyPlugin.plugin.removePlayer(from);
            MineCompanyPlugin.playerCompanies.put(to.toLowerCase(), company.getName());
            company.setLeader(to);

            sender.sendMessage(Lang.withPlaceHolder(Lang.MIGRATED_PLAYER_LEADER,
                    new String[]{"%before%", "%company%", "%after%"}, from, company.getName(), to));
        }

        return true;
    }
}