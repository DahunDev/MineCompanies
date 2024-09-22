package net.daniel.MineCompany;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Company {

    private String name;
    private ArrayList<String> members;
    private String leader;
    private ArrayList<String> sub_leaders;
    private int Unpaid_times;

    private double Unpaid;
    private double CompanyValue;
    private long startDate;
    private int maxSize;
    public long lastPaid;

    public Company(String name) {

        this.name = name.toLowerCase();
        setMembers(new ArrayList<String>());
        setSub_leaders(new ArrayList<String>());
        maxSize = MineCompanyPlugin.plugin.getConfig().getInt("Company.defaultSize", 8);
        CompanyValue = MineCompanyPlugin.default_company_value;
        startDate = System.currentTimeMillis();
        lastPaid = startDate;
        Unpaid = 0;
        Unpaid_times = 0;
    }

    public boolean setUnPaid_times(int times) {
        if (times >= 0) {
            this.Unpaid_times = times;
            return true;
        }
        return false;

    }


    public void setName(String name) {
        this.name = name;
    }


    public boolean setUnPaid(double unpaid) {
        if (unpaid >= 0) {
            this.Unpaid = unpaid;
            return true;
        }
        return false;

    }


    public boolean isMember(String player) {
        return this.getMembers().contains(player.toLowerCase());
    }

    public boolean isSubLeader(String player) {
        return this.getSub_leaders().contains(player.toLowerCase());
    }

    public boolean isLeader(String player) {
        if (player == null && this.getLeader() == null) {
            return true;
        }

        return this.getLeader().equalsIgnoreCase(player.toLowerCase());
    }

    public boolean hasThisPlayer(String player) {
        return (this.isMember(player) || this.isSubLeader(player) || this.isLeader(player));
    }

    public double getCompanyValue() {
        return this.CompanyValue;
    }


    public boolean addCompanyValue(double price) {

        if (price >= 0) {

            double addPrice = price - Unpaid;

            if (this.Unpaid > 0) {
                subtractunPaidValue(price);
            }

            if (addPrice > 0) {

                this.CompanyValue += addPrice;

            }

            return true;

        } else {
            return false;
        }

    }

    public double getValueForRank() {
        return CompanyValue - Unpaid;
    }

    public double getUnPaid() {
        return this.Unpaid;
    }

    public boolean addunPaidValue(double price) {

        if (price >= 0) {
            this.Unpaid += price;
            return true;

        } else {
            return false;
        }

    }

    public boolean subtractunPaidValue(double price) {

        if (price >= 0) {
            this.Unpaid -= price;

            if (this.Unpaid <= 0) {
                this.Unpaid_times = 0;
                this.Unpaid = 0;
            }

            return true;

        } else {
            return false;
        }

    }

    public String getName() {
        return this.name;
    }

    public int getTeamSize() {
        if (this.getLeader() != null) {
            return 1 + getMembers().size() + getSub_leaders().size();

        } else {

            return getMembers().size() + getSub_leaders().size();
        }

    }

    public boolean addMember(String playerName) {
        if (maxSize > getMembers().size() + getSub_leaders().size() + 1) {
            getMembers().add(playerName.toLowerCase());
            MineCompanyPlugin.playerCompanies.put(playerName.toLowerCase(), this.name.toLowerCase());
            MineCompanyPlugin.plugin.updatePlayer(playerName);

            return true;
        } else {
            return false;
        }

    }

    public boolean changeToSubLeader(String playerName) {

        if (isSubLeader(playerName)) {
            return true;
        }

        if (isMember(playerName)) {
            playerName = playerName.toLowerCase();
            members.remove(playerName);
            sub_leaders.add(playerName);
            MineCompanyPlugin.plugin.updatePlayer(playerName);

            return true;
        }
        return false;

    }

    public boolean changeToMember(String playerName) {

        if (isMember(playerName)) {
            return true;
        }

        if (isSubLeader(playerName)) {
            playerName = playerName.toLowerCase();
            sub_leaders.remove(playerName);
            members.add(playerName);
            MineCompanyPlugin.plugin.updatePlayer(playerName);

            return true;
        }
        return false;

    }

    public void removeMember(String name) {

        name = name.toLowerCase();
        members.remove(name);
        MineCompanyPlugin.plugin.removePlayer(name);

    }

    public void removeSubLeader(String name) {
        name = name.toLowerCase();

        getSub_leaders().remove(name);

        MineCompanyPlugin.plugin.removePlayer(name);
    }


    public void subtractCompanyValue(double price) {
        this.CompanyValue -= price;
        if (this.CompanyValue < 0) {
            this.CompanyValue = 0;
        }

    }

    public void setCompanyValue(double price) {
        if (price > 0) {
            this.CompanyValue = price;

        } else {
            this.CompanyValue = 0;
        }
    }

    public long getStartDate() {
        return this.startDate;
    }

    public void setStartDate(long date) {

        this.startDate = date;

    }

    public boolean addMaxSize() {
        return addMaxSize(1);
    }

    public boolean addMaxSize(int amount) {

        int before = maxSize;

        maxSize += amount;

        if (maxSize < MineCompanyPlugin.plugin.getConfig().getInt("Company.defaultSize", 8)) {
            maxSize = before;
            return false;

        } else if (maxSize > MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company", 1000)) {
            maxSize = MineCompanyPlugin.plugin.getConfig().getInt("maxSize_Company", 1000);
            return false;
        } else {
            return false;
        }

    }

    public boolean subtractMaxSize() {
        return subtractMaxSize(1);
    }

    public boolean quitCompany(Player player) {
        String name = player.getName();
        if (this.isMember(name) || isSubLeader(name)) {
            removeMember(name);
            removeSubLeader(name);


            player.sendMessage(Lang.withPlaceHolder(Lang.LEAVE_COMPANY, "%company%", this.name));

            return true;

        } else if (isLeader(name)) {


            player.sendMessage(Lang.withPlaceHolder(Lang.CANNOT_LEAVE_COMPANY_LEADER, "%company%", this.name));
            return false;
        } else {
            // 회사 직원/지도자가 어느것도 아닌경우
            return false;
        }
    }

    public boolean subtractMaxSize(int amount) {
        int before = maxSize;
        maxSize -= amount;

        if (maxSize < MineCompanyPlugin.plugin.getConfig().getInt("Company.defaultSize", 8)) {
            maxSize = before;
            return false;

        } else {
            return true;
        }
    }

    public boolean setMaxSize(int size) {
        if (size > 0) {
            this.maxSize = size;
            return true;
        } else {
            return false;
        }
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void saveCompany() {

        if (MineCompanyPlugin.CompanyYML.data.getConfigurationSection("Companies") == null) {
            MineCompanyPlugin.CompanyYML.data.createSection("Companies");
        }
        ConfigurationSection companySection = MineCompanyPlugin.CompanyYML.data.getConfigurationSection("Companies")
                .createSection(this.name);
        companySection.set("leader", this.getLeader());
        companySection.set("sub_leaders", this.getSub_leaders());
        companySection.set("members", this.getMembers());

        companySection.set("unPaid_times", Unpaid_times);
        companySection.set("unPaid_money", Unpaid);
        companySection.set("value", this.getCompanyValue());

        companySection.set("startDate", this.getStartDate());
        companySection.set("maxSize", this.getMaxSize());
        companySection.set("lastPaid", this.lastPaid);


    }

    public String getLeader() {
        return leader.toLowerCase();
    }

    public void setLeader(String playerName) {
        this.leader = playerName.toLowerCase();
    }

    public ArrayList<String> getSub_leaders() {
        return sub_leaders;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public void setSub_leaders(ArrayList<String> sub_leaders) {
        this.sub_leaders = sub_leaders;
    }

    public int getUnpaid_times() {
        return Unpaid_times;
    }


    public void addUnpaid_times() {
        addUnpaid_times(1);
    }

    public void subtractUnpaid_times() {
        subtractUnpaid_times(1);
    }

    public void addUnpaid_times(int times) {
        Unpaid_times += times;
    }

    public void subtractUnpaid_times(int times) {
        Unpaid_times -= times;
        if (Unpaid_times < 0) {
            Unpaid_times = 0;
        }
    }

    public String getUnpaid_times_String() {

        if (Unpaid_times <= 0) {
            Unpaid_times = 0;
            return Lang.SAFE_COLOR.toString() + Unpaid_times;
        } else {
            return Lang.WARN_COLOR.toString() + Unpaid_times;

        }

    }


    public String getUnpaid_fee_String() {

        if (Unpaid <= 0) {
            Unpaid = 0;
            return Lang.SAFE_COLOR.toString() + Unpaid;
        } else {
            return Lang.WARN_COLOR.toString() + Unpaid;

        }

    }


    public void increaseUnpaid_times(int unpaid_times) {
        this.Unpaid_times += unpaid_times;
    }

    public void resetUnpaid_times() {
        this.Unpaid_times = 0;
    }

    public void resetUnpaid() {
        this.Unpaid = 0;
    }

    public boolean changeLeader(String from, String to) {

        if (isLeader(from)) {
            if (hasThisPlayer(to)) {
                this.sub_leaders.remove(to.toLowerCase());
                this.members.remove(to.toLowerCase());
                this.leader = to.toLowerCase();
                if (from != null) {
                    this.members.add(from.toLowerCase());
                }
                return true;
            }

        }
        return false;
    }

    public String getSubLeaderList() {
        String list = String.valueOf(sub_leaders);
        list = list.substring(1);
        list = list.substring(0, list.length() - 1);

        if (list.isEmpty()) {
            return Lang.EMPTY_LIST.toString();
        }

        return list;

    }

    public String getMemberList() {
        String list = String.valueOf(members);
        list = list.substring(1);
        list = list.substring(0, list.length() - 1);

        if (list.isEmpty()) {
            return Lang.EMPTY_LIST.toString();
        }

        return list;

    }

    @Override
    public String toString() {
        return this.name;
    }


}