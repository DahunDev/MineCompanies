package net.daniel.MineCompany;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import com.google.common.base.CaseFormat;

import net.daniel.MineCompany.MCUtils.MCUtils;


public enum Lang {
	// Class ���� ���ϸ�� ���� �ʿ�� ����
	// �̸��� ���Ͽ� �ִ� �޼����� ������, ����� _ �� ��ü (�޼��� X)

	ALREADY_HAS_COMPANY("&2[&9ȸ��&2]&f %target% ���� �̹� ������ �ֽ��ϴ�."),
	ALREADY_LEADER("&2[&9ȸ��&2]&f %target% ���� �̹� %company% ȸ���� ȸ���Դϴ�."),

	ALREADY_HAS_COMPANY_ME("&2[&9ȸ��&2]&f ����� �̹� ������ �ֽ��ϴ�."),
	MIGRATED_PLAYER_LEADER("&2[&9ȸ��&2]&f �������� ��ɾ��, %before%���� ȸ���� %company%ȸ���� ȸ���� %after%�� ����Ǿ����ϴ�." ),	
	MIGRATED_PLAYER_SUBLEADER("&2[&9ȸ��&2]&f �������� ��ɾ��, %before%���� ��ȸ���� %company%ȸ���� ��ȸ���� %before% ��� %after%�� ����Ǿ����ϴ�."),
	MIGRATED_PLAYER_MEMBER("&2[&9ȸ��&2]&f �������� ��ɾ��, %before%���� ����� %company%ȸ���� ��ȸ���� %before% ��� %after%�� ����Ǿ����ϴ�."),
	MIGRATE_PLAYER_HELP("&2[&9ȸ��&2]&f /ȸ��������� <�����г���> <���г���>"),
	
	COMPANY_MANAGE_HELP("&2[&9ȸ��&2]&f /ȸ����� â�� <ȸ���> <�г���>\n" +
	
 	"&2[&9ȸ��&2]&f /ȸ����� ȸ������ <ȸ���> <�г���>\n"+

	"&2[&9ȸ��&2]&f /ȸ����� ��ü <ȸ���>\n"+
	"&2[&9ȸ��&2]&f /ȸ����� ���� <ȸ���>\n"+

	"&2[&9ȸ��&2]&f /ȸ����� ��� [������]\n"+
	"&2[&9ȸ��&2]&f /ȸ����� ���-������\n"+	
	"&2[&9ȸ��&2]&f /ȸ����� �߰�/���� <ȸ���> <�г���>\n"+
	"&2[&9ȸ��&2]&f /ȸ����� �ִ��ο� <ȸ���> <�ִ��ο�>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �̸����� <����ȸ���̸�> <���̸�> &7: <����ȸ���̸�> ȸ���� ȸ�� �̸��� <���̸�>���� �����մϴ�.\n" +
	"&2[&9ȸ��&2]&f /ȸ����� ��ȸ <�г���>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �������ε�\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �����͸��ε�\n" +
	"&2[&9ȸ��&2]&f /ȸ����� ��ġ�ݼ��� <ȸ���> <�ݾ�>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� ��ġ���߰� <ȸ���> <�ݾ�>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� ��ġ������ <ȸ���> <�ݾ�>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �̳����߰� <ȸ���> <�ݾ�>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �̳������� <ȸ���> <�ݾ�>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �̳��ݼ��� <ȸ���> <�ݾ�>\n" +
	
	"&2[&9ȸ��&2]&f /ȸ����� �̳�Ƚ���߰� <ȸ���> <Ƚ��>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �̳�Ƚ������ <ȸ���> <Ƚ��>\n" +
	"&2[&9ȸ��&2]&f /ȸ����� �̳�Ƚ������ <ȸ���> <Ƚ��>\n" +
	"&2[&9ȸ��&2]&f &2[&9ȸ��&2]&f /ȸ����� �������� &7: ȸ�� ��Ͽ� ������ ȸ�� ������ �����մϴ�.\n" +
	"&2[&9ȸ��&2]&f /ȸ����� ����������"),
	
	WARN_COLOR("&c"),
	SAFE_COLOR("&f"),
	
	DAYS("��"),
	HOURS("�ð�"),
	MINUTES("��"),
	SECONDS("��"),
	
	
	RELOADED_CONFIG("&2[&9ȸ��&2]&f ���� ���ε� �Ϸ�."),
	RELOADED_DATA("&2[&9ȸ��&2]&f ������ ���ε� �Ϸ�."),

	COMPANY_CREATE_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� â�� <ȸ���> <�г���>"),
	
	COMPANY_FORCE_ADD_MEMBER("&2[&9ȸ��&2]&f �����ڿ� ���� %target%���� %company% ȸ�� ������� �߰��ƽ��ϴ�."),
	COMPANY_FORCE_ADD_MEMBER_TO_TARGET("&2[&9ȸ��&2] &f�����ڿ� ���� %company% ȸ���� ����� �Ǿ����ϴ�."),

	COMPANY_ADMIN_ADD_MEMBER_HELP("&2[&9ȸ��&2]&f /ȸ����� �߰� <ȸ���̸�> <�г���>"),
	COMPANY_ADMIN_MANDATE_HELP("&2[&9ȸ��&2]&f /ȸ����� ȸ������ <ȸ���> <�г���>"),
	COMPANY_ADMIN_DELETE_COMPANY_HELP("&2[&9ȸ��&2]&f /ȸ����� ��ü <ȸ���>"),
	COMPANY_ADMIN_REMOVE_MEMBER_HELP("&2[&9ȸ��&2]&f /ȸ����� ���� <ȸ���̸�> <ȸ����̸�>"),
	COMPANY_FORCE_SET_MAXSIZE(" &2[&9ȸ��&2]&f ������(%player%)�Կ� ���� %company% ȸ���� �ִ��ο��� %size%������ ����."),
	COMPANY_SET_MAXSIZE("&2[&9ȸ��&2]&f %company% ȸ���� �ִ��ο��� %size%������ ����."),

	COMPANY_ADMIN_MAXSIZE_HELP("&2[&9ȸ��&2]&f /ȸ����� �ִ��ο� <ȸ���̸�> <���� ����>"),
	
	COMPANY_LIST_WITH_OWNER_HELP("&2[&9ȸ��&2]&f /ȸ����� ���-������ [������]"),
	COMPANY_LIST_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� ��� [������]"),
	

	
	COMPANY_SET_VALUE_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� ��ġ�ݼ��� <ȸ���> <�ݾ�>"),

	COMPANY_ADD_VALUE_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� ��ġ���߰� <ȸ���> <�ݾ�>"),

	COMPANY_SUBTRACT_VALUE_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� ��ġ������ <ȸ���> <�ݾ�>"),

	COMPANY_ADD_UNPAID_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� �̳����߰� <ȸ���> <�ݾ�>"),
	
	COMPANY_SUBTRACT_UNPAID_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� �̳������� <ȸ���> <�ݾ�>"),

	COMPANY_SET_UNPAID_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� �̳��ݼ��� <ȸ���> <�ݾ�>"),

	COMPANY_ADD_UNPAID_TIMES_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� �̳�Ƚ���߰� <ȸ���> <Ƚ��>"),
	COMPANY_SUBTRACT_UNPAID_TIMES_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� �̳�Ƚ������ <ȸ���> <Ƚ��>" ),

	COMPANY_SET_UNPAID_TIMES_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� �̳�Ƚ������ <ȸ���> <Ƚ��>"),
	
	
	CALC_RANK_DONE("&2[&9ȸ��&2]&f ȸ�� ���� ���� �Ϸ�, �ҿ�� �ð� %ms% ms"),

	COMPANY_SET_VALUE_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ��ġ�ݾ��� %value%������ �����Ǿ����ϴ�."),

	
	COMPANY_ADD_VALUE_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ��ġ�ݾ��� %amount%���� �߰��Ǿ����ϴ�."),
	COMPANY_ADD_VALUE("&2[&9ȸ��&2]&f %company% ȸ���� ��ġ�ݾ��� %amount%���� �߰��Ǿ� ���� ��ġ�ݾ��� %value%�� �Դϴ�."),
	COMPANY_ADD_VALUE_HELP("&2[&9ȸ��&2]&f /ȸ�� ��ġ�Ա� <�ݾ�> &7: ȸ�� ��ġ�� <�ݾ�>�� ��ŭ �Ա��մϴ�. ȸ�� ��ġ�� ������ ���� �� ���� ��꿡 ���̸� ����� �Ұ����մϴ�."),

	
	
	COMPANY_SUBTRACT_VALUE_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ��ġ�ݾ��� %amount%�� �����Ǿ����ϴ�."),
	COMPANY_SUBTRACT_VALUE("&2[&9ȸ��&2]&f %company% ȸ���� ��ġ�ݾ��� %amount%���� �����Ǿ� ���� ��ġ�ݾ��� %value%�� �Դϴ�."),

	
	COMPANY_SET_UNPAID_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ������ �̳����� %unPaid%������ �����Ǿ����ϴ�."),

	COMPANY_ADD_UNPAID_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ������ �̳����� %amount%�� �߰��Ǿ����ϴ�."),
	COMPANY_ADD_UNPAID("&2[&9ȸ��&2]&f %company% ȸ���� ������ �̳����� %amount%���� �߰��Ǿ� ���� �̳����� %unPaid%�� �Դϴ�."),
	COMPANY_SUBTRACT_UNPAID_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ������ �̳����� %amount%�� �����Ǿ����ϴ�."),
	COMPANY_SUBTRACT_UNPAID("&2[&9ȸ��&2]&f %company% ȸ���� ������ �̳����� %amount%���� �����Ǿ� ���� �̳����� %unPaid%�� �Դϴ�."),

	
	COMPANY_SET_UNPAID_TIMES_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ������ �̳�Ƚ���� %unPaid_times%ȸ�� �����Ǿ����ϴ�."),

	COMPANY_ADD_UNPAID_TIMES_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ������ �̳�Ƚ���� %amount%ȸ �߰��Ǿ����ϴ�."),
	COMPANY_ADD_UNPAID_TIMES("&2[&9ȸ��&2]&f %company% ȸ���� ������ �̳� Ƚ���� %amount%ȸ �߰��Ǿ� ���� �̳� Ƚ���� %unPaid_times%ȸ �Դϴ�."),

	COMPANY_SUBTRACT_UNPAID_TIMES_BY_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� %company%ȸ���� ������ �̳�Ƚ���� %amount%ȸ �����Ǿ����ϴ�."),
	COMPANY_SUBTRACT_UNPAID_TIMES("&2[&9ȸ��&2]&f %company% ȸ���� ������ �̳� Ƚ���� %amount%ȸ �����Ǿ� ���� �̳� Ƚ���� %unPaid_times%ȸ �Դϴ�."),

	
	
	COMPANY_DATA_SAVED("&2[&9ȸ��&2]&f ȸ�� �����Ͱ� �����"),
	
	COMPANY_LOOKUP_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� ��ȸ <�г���>"),

	COMPANY_FORCE_REMOVE_MEMBER("&2[&9ȸ��&2]&f �����ڿ� ���� %target%���� %company% ȸ�翡�� Ż��Ǿ����ϴ�."),
	COMPANY_FORCE_REMOVE_MEMBER_TARGET("&2[&9ȸ��&2]&f �����ڿ�  %company% ȸ�翡�� Ż��Ǿ����ϴ�."),

	COMPANY_FORCE_MANDATE_DONE("&2[&9ȸ��&2]&f �����ڿ� ����  %company% ȸ���� ȸ���� %from%�Կ��� %after%������ ����Ǿ����ϴ�."),

	LEAVE_COMPANY("&2[&9ȸ��&2]&f ����� %company% ȸ�縦 Ż���߽��ϴ�."),
	CANNOT_LEAVE_COMPANY_LEADER("&2[&9ȸ��&2]&f ȸ���� ȸ�翡�� Ż�� �� �� �����ϴ�. ��ü�� ���� �ٸ� \"/ȸ�� ��ü\"�� �̿��� �ּ���."),
	
		
	COMPANY_FORCEREMOVE_MEMBER_TO_TARGET("&2[&9ȸ��&2]&f �����ڿ� ���� %company% ȸ�翡�� �߹�Ǿ����ϴ�."),
	CANNOT_KICK_LEADER("&2[&9ȸ��&2]&f ȸ���� �߹��ϴ� ���� �Ұ����մϴ�."),
	NOT_MEMBER_TARGET("&2[&9ȸ��&2]&f %target%���� �� %company% ȸ���� �Ҽ��� �ƴմϴ�."),
	NOT_MEMBER_MYSELF("&2[&9ȸ��&2]&f ����� �� ȸ�翡 �ҼӵǾ� ���� �ʽ��ϴ�."),
			
	COMPANY_LIST_HELP("&2[&9ȸ��&2]&f /ȸ�� ��� [������]"),
	
	COMPANY_NOT_EXIST_THAT_NAME("&2[&9ȸ��&2]&f %company% ȸ��� �������� �ʽ��ϴ�."),
	
	NAME_WITH_OWNER("&b[%rank%] &fcompany% ȸ�� - ������:%leader% - ȸ�簡ġ��:%values%"),
	
	COMPANY_LIST("&2[&9ȸ��&2]&f ============== (���ı���: ��ġ�ݾ�, ���� �ð�: %time%)\n"+
	"%company_list%\n"+
			"&2[&9ȸ��&2]&f ����������: %page% / %maxPage%"
			),
	COMPANY_LIST_LINE("&b[%rank%] &f%company% ȸ��"),
	
	COMPANY_INFO("&2[&9ȸ��&2]&f ================= &b���� &f=================\n"
			+ "&2[&9ȸ��&2] &9ȸ���: &f%company%\n"
			+ "&2[&9ȸ��&2] &4ȸ��: &f%leader%\n"
			+ "&2[&9ȸ��&2] &c��ȸ��: &f%sub_leaders%\n"
			+ "&2[&9ȸ��&2] &e���: &f%members%\n"
			+ "&2[&9ȸ��&2] &a�ο�: &f%size%/%max_size%\n"
			+ "&2[&9ȸ��&2] &6ȸ�� ��ġ�ݾ� : &f%value%��\n"
			+ "&2[&9ȸ��&2] &aȸ�� ������ : &f%money_for_maintain%��/%maintain_time%\n"
			+ "&2[&9ȸ��&2] &cȸ�� ������ �̳��� : &f%unPaid_bal%&f��\n"
			+ "&2[&9ȸ��&2] &cȸ�� ������ �̳� Ƚ�� : &f%amount_unpaid%&fȸ &7(%max_unPaid%ȸ �ʰ��� ȸ�� ���� ��ü)\n"
			+ "&2[&9ȸ��&2] &a�ֱ� ������ ���� ��¥: &f%date_of_lastPaid%\n"
			+ "&2[&9ȸ��&2] &9ȸ��â������: &f%startDate%"
			),
	
	COMPANY_INFO_MINE("&2[&9ȸ��&2]&f ================= &b����� ���� ȸ�� ���� &f=================\n"
			+ "&2[&9ȸ��&2] &9ȸ���: &f%company%\n"
			+ "&2[&9ȸ��&2] &4ȸ��: &f%leader%\n"
			+ "&2[&9ȸ��&2] &c��ȸ��: &f%sub_leaders%\n"
			+ "&2[&9ȸ��&2] &e���: &f%members%\n"
			+ "&2[&9ȸ��&2] &a�ο�: &f%size%/%max_size%\n"
			+ "&2[&9ȸ��&2] &6ȸ�� ��ġ�ݾ� : &f%value%��\n"
			+ "&2[&9ȸ��&2] &aȸ�� ������ : &f%money_for_maintain%��/%maintain_time%\n"
			+ "&2[&9ȸ��&2] &cȸ�� ������ �̳��� : &f%unPaid_bal%&f��\n"
			+ "&2[&9ȸ��&2] &cȸ�� ������ �̳� Ƚ�� : &f%amount_unpaid%&fȸ &7(%max_unPaid%ȸ �ʰ��� ȸ�� ���� ��ü)\n"
			+ "&2[&9ȸ��&2] &a�ֱ� ������ ���� ��¥: &f%date_of_lastPaid%\n"
			+ "&2[&9ȸ��&2] &9ȸ��â������: &f%startDate%"
			),
	
	COMPANY_HELP("&2[&9ȸ��&2]&f /ȸ�� ���� <�̸�>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ���� <ȸ���̸�> <�г���>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ���Ӽ���/���� <ȸ���̸�> <�г���>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ����/���� <�̸�>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� �ʴ� <�г���>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� �߹� <�г���>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ��� <�г���>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ��ȸ�� <�г���>\n" +
			"&2[&9ȸ��&2]&f /ȸ�� �̸����� <���̸�> &7: 200������ �Һ��Ͽ� ȸ�� �̸��� �����մϴ�.\n" +
			"&2[&9ȸ��&2]&f /ȸ�� �������� <�ִ��ο�> &7: �ִ��ο��� <�ִ��ο�>�϶��� ȸ�� ��ġ�ݾ׿��� ���εǴ� ȸ�� ������ ����մϴ�.\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ��ġ�Ա� <�ݾ�> &7: ȸ�� ��ġ�� <�ݾ�>�� ��ŭ �Ա��մϴ�. ȸ�� ��ġ�� ������ ���� �� ���� ��꿡 ���̸� ����� �Ұ����մϴ�.\n" +
			"&2[&9ȸ��&2]&f /ȸ�� â�� <ȸ���> &7: (������� 700����, �ּ��ں��� 2000���� ���� â�� ����, ȸ���̸��� ���ڵ带 �־ ���� ������� �ʽ��ϴ�. ���� ȸ�� �̸��� �����ñ� �ٶ��ϴ�. �ڿ� ȸ����� ���̽ø� ȸ���̸�ȸ�� ȸ�� �̷������� ��ϵ˴ϴ�.�⺻ �ִ� �ο� 8��)\n" +
			"&2[&9ȸ��&2]&f /ȸ�� Ż��\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ��ü\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ��� [������]\n" +
			"&2[&9ȸ��&2]&f /ȸ�� ��ȸ <�г���> &7: (�г���)�� �Ҽ�ȸ�縦 Ȯ���մϴ�.\n" +
			"&2[&9ȸ��&2]&f /ȸ�� �߰� &7: (�ִ��ο��� 1�� ����, 20������� 1�� �߰��� 100���� , 20~30�� ������ 1�� �߰��� 250����, 30~50 �� ������ 1�� �߰��� 1500����, 50�� �̻��� 1�� �߰��� 1��� �ҿ�. ȸ�常 ����) (�ο��� ���� ȸ�� ������ ����)"
),
	
	CALC_MAINTAIN_FEE_HELP("&2[&9ȸ��&2]&f /ȸ�� �������� <�ִ��ο�> &7: �ִ��ο��� <�ִ��ο�>�϶��� ȸ�� ��ġ�ݾ׿��� ���εǴ� ȸ�� ������ ����մϴ�."),
	MAINTAIN_FEE_INFO("&2[&9ȸ��&2]&fȸ�� �ִ��ο����� %size%���� ��� %price%���� %interval%���� ���ε˴ϴ�. (�ִ� ��� �̳� Ƚ��: %max_unPaid%ȸ)"),
	
	COMPANY_DELETED_BY_UNPAID("&2[&9ȸ��&2]&f %company%ȸ�簡 ȸ�� ������ ���� Ƚ�� �̻� �̳����� ������ü �ƽ��ϴ�."),

	
	COMPANY_CHNAGE_NAME_HELP("&2[&9ȸ��&2]&f /ȸ�� �̸����� <���̸�> &7: 200������ �Һ��Ͽ� ȸ�� �̸��� �����մϴ�."),
	COMPANY_CHNAGE_NAME_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ����� �̸����� <����ȸ���̸�> <���̸�> &7: <����ȸ���̸�> ȸ���� ȸ�� �̸��� <���̸�>���� �����մϴ�."),
	

	COMPANY_CHNAGED_NAME_MEMBERS("&2[&9ȸ��&2]&f ����� �Ҽӵ� ȸ���� �̸��� %company%�� ����Ǿ����ϴ�."),
	
	COMPANY_CHNAGED_NAME_BROADCAST("&2[&9ȸ��&2]&f ȸ�� %player%�Կ� ���� ������ %oldCompany% ȸ���� �̸��� %company%�� ����Ǿ����ϴ�."),

	COMPANY_CHNAGED_NAME_BROADCAST_ADMIN("&2[&9ȸ��&2]&f ������ %player%�Կ� ���� ������ %oldCompany% ȸ���� �̸��� %company%�� ����Ǿ����ϴ�."),

	FAILED_COMPANY_CHNAGE_NAME("&2[&9ȸ��&2]&f ȸ�� �̸� ���濡 �����߽��ϴ�."),


	
	
	
	CANNOT_SET_LEADER_TO_MEMBER("&2[&9ȸ��&2]&f ȸ���� ������� ������ �� �����ϴ�."),
	COMPANY_SET_MEMBER_HELP("&2[&9ȸ��&2]&f /ȸ�� ��� <�г���> &7: <�г���>���� ������� �����մϴ�."),
	NOW_MEMBER_TARGET("&2[&9ȸ��&2]&f %target%���� ���� %company%ȸ���� ����Դϴ�."),
	NOW_MEMBER_ME("&2[&9ȸ��&2]&f ����� ���� %company%ȸ���� ����Դϴ�."),
	FAILED_SET_MEMBER("&2[&9ȸ��&2]&f %target%���� %company%ȸ���� ��ȸ������ �����ϴ� ���� �����߽��ϴ�."),

	
	COMPANY_SET_SUBLEADER_HELP("&2[&9ȸ��&2]&f /ȸ�� ��ȸ�� <�г���> &7: <�г���>���� ��ȸ������ �����մϴ�."),
	NOW_SUBLEADER_TARGET("&2[&9ȸ��&2]&f %target%���� ���� %company%ȸ���� ��ȸ�� �Դϴ�."),
	NOW_SUBLEADER_ME("&2[&9ȸ��&2]&f ����� ���� %company%ȸ���� ��ȸ�� �Դϴ�."),
	FAILED_SET_SUBLEADER("&2[&9ȸ��&2]&f %target%���� %company%ȸ���� ��ȸ������ �����ϴ� ���� �����߽��ϴ�."),

	
	
	JUST_PAID_MAINTAIN_FEE("&2[&9ȸ��&2]&f %company%ȸ�� ������� %price%���� ��ġ�ݾ׿��� �����Ͽ� ���� ��ġ�ݾ��� %value%�� �Դϴ�."),
	JUST_UNPAID_MAINTAIN_FEE("&2[&9ȸ��&2]&f %company%ȸ�� ������ ������ ȸ�� ��ġ�ݾ��� %need%�� �����Ͽ� �̳� Ƚ���� �߰��Ǿ����ϴ�. &f���� �̳���: %unPaid%, ���� �̳� Ƚ��: %unPaid_times%/%max_unPaid%ȸ"),

	
	
	
	CANNOT_CHANGE_RANK_MINE("&2[&9ȸ��&2]&f �ڱ� �ڽ��� ������ ���� �� �� �����ϴ�."),
	COMPANY_NOTEXIST("&2[&9ȸ��&2] &f%company% ȸ��� ���� ���� �ʽ��ϴ�."),
	COMPANY_ALREADY_EXIST("&2[&9ȸ��&2] &f%company% ȸ��� �̹� �����մϴ�."),

	
	
	COMPANY_VALUE_ADD_CMD_HELP("&2[&9ȸ��&2]&f /ȸ�� ��ġ�Ա� <�ݾ�> &7: ȸ�� ��ġ�� <�ݾ�>�� ��ŭ �Ա��մϴ�. ȸ�� ��ġ�� ������ ���� �� ���� ��꿡 ���̸� ����� �Ұ����մϴ�."),
	COMPANY_INFO_ADMIN_HELP("&2[&9ȸ��&2]&f /ȸ�� ���� <ȸ�� �̸�>"),
	
	EMPTY_LIST("����"),
	NO_PERM("&b&l[ &f&lMine SV &b&l] &c������ �����ϴ�."),

	
	MANDATE_HELP("&2[&9ȸ��&2]&f /ȸ�� ���� <ȸ���̸�> <�г���>"),
	MANDATE_DENY_HELP("&2[&9ȸ��&2]&f /ȸ�� ���Ӱ��� <ȸ���̸�> <�г���>"),
	MANDATE_ACCEPT_HELP("&2[&9ȸ��&2]&f /ȸ�� ���Ӽ��� <ȸ���̸�> <�г���>"),

	
	MANDATE_LEADER_DONE("&2[&9ȸ��&2]&f &a���� �Ϸ�."),
	MANDATE_LEADER_FAILED("&2[&9ȸ��&2]&c &a%company% ���� ó�� ����, ȸ���� �̹� ����Ǿ��ų�, ���� ����� ���̻� ���� ȸ����� �ƴմϴ�."),
	NOT_MATCH_THE_COMPANY_NAME("&2[&9ȸ��&2]&c ȸ�� �̸��� ��ġ���� �ʽ��ϴ�."),
	NOT_MATCH_THE_PLAYER_NAME("&2[&9ȸ��&2]&c �÷��̾� �̸��� ��ġ���� �ʽ��ϴ�."),


	MANDATE_LEADER_BROADCAST("&2[&9ȸ��&2]&f %company%ȸ�� ȸ�� %leader%���� %target%�Կ��� ȸ���� �����߽��ϴ�."),
	
	CANNOT_MANDATE_CHANGED_COMPANY("&2[&9ȸ��&2]&c ���̻� ���� ȸ�� �Ҽ��� �ƴϿ��� ���� ��û�� ��ҵƽ��ϴ�."),
	
	CANNOT_MANDATE_CHANGED_LEADER("&2[&9ȸ��&2]&c ���� �¶����� �����ڰ� �̹� ������ �Ǿ� �ش� ���� Ȯ���� ��ҵƽ��ϴ�."),

	CANNOT_MANDATE_ANOTHER_COMPANY("&2[&9ȸ��&2]&f %target%���� ���� ȸ�� �Ҽ��� �ƴմϴ�."),
	ADMIN_CANNOT_MANDATE_NOT_MEMEBER("&2[&9ȸ��&2]&f %target%���� %company% ȸ�� �Ҽ��� �ƴմϴ�."),

	CANNOT_MANDATE_TO_OWNER("&2[&9ȸ��&2]&f �ش� �÷��̾�� ȸ���Դϴ�."),

	MANDATE_LEADER_DENIED("&2[&9ȸ��&2]&f &a���� ��û�� �����߽��ϴ�."),
	MANDATE_LEADER_DENIED_TO_OWNER("&2[&9ȸ��&2]&f &a%target%�Կ� ���� ������ �����ƽ��ϴ�."),

	MANDATE_LEADER_NOT_REQUESTED("&2[&9ȸ��&2] &c���� ��û�� ���� ���� �����ϴ�."),
	MANDATE_LEADER_REQUESTED("&2[&9ȸ��&2]&f &a%company% ȸ���� ��� %target%&f�Կ��� ȸ�� ���� ��û�� �Ͽ����ϴ�. ������ ���� ��� %sec%�� ��û�� �ڵ����� ��ҵ˴ϴ�."),
	MANDATE_LEADER_REQUESTED_TARGET("&2[&9ȸ��&2]&f &aȸ���� ���� ��û�� ���Խ��ϴ�. %sec%���Ŀ� �ڵ����� �����˴ϴ�.\n" +
	
			"&2[&9ȸ��&2]&f ���� �ϽǷ���, &a/ȸ�� ���Ӽ��� %company% <�����̸�> &f��ɾ, �����ϽǷ��� &e/ȸ�� ���Ӱ��� %company% <�����̸�> &f��ɾ �Է����ּ���."),

	MANDATE_CONFIRM_CANCELED("&2[&9ȸ��&2]&f &cȸ�� ���ӽ�û�� ��ҵǾ����ϴ�."),
	MANDATE_DENIED_BY_EXPIRED("&2[&9ȸ��&2]&f �ð��ʰ��� &c����&f�Ǿ����ϴ�."),

	MANDATE_LEADER_ALREADY_REQUESTED("&2[&9ȸ��&2]&f &c�̹� �ش� �÷��̾�� ���� ��û�� ���� ���� �Դϴ�."),
	CANNOT_MANDATE_MYSELF("&2[&9ȸ��&2]&f &c�ڱ� �ڽſ��Դ� ������ �� �����ϴ�."),
	ONLY_LEADER("&2[&9ȸ��&2] &f�ش��۾��� ȸ�常 �����մϴ�."),
	
	ONLY_PLAYER("&2[&9ȸ��&2] &f�ش��۾��� �÷��̾ �����մϴ�."),

	
	LOOKUP_PLAYER_COMPANY_HELP("&2[&9ȸ��&2]&f /ȸ�� ��ȸ <�г���>"),
	
	NO_COMPANY("&2[&9ȸ��&2]&f &c����� �Ҽӵ� ȸ�簡 �����ϴ�."),
	PLAYER_HAS_NO_COMPANY("&2[&9ȸ��&2]&f %target%���� �Ҽӵ� ȸ�簡 �����ϴ�."),

	PLAYER_LOOKUP_COMPANY("&2[&9ȸ��&2]&f %target%���� %company% ȸ�翡 �ҼӵǾ� �ֽ��ϴ�."),
	PLAYER_NOT_ONLINE("&2[&9ȸ��&2]&f &c%target%���� �¶����� �ƴմϴ�."),
	
	INVITE_HELP("&2[&9ȸ��&2]&f /ȸ�� �ʴ� <�г���> &7: <�г���>���� ���� ȸ�翡 �ʴ��մϴ�."),
	ALREADY_INVITED("&2[&9ȸ��&2]&f &c�̹� �ʴ븦 �߽��ϴ�.."),

	NOT_INVITED("&2[&9ȸ��&2]&f &c��ſ��� �� �ʴ밡 �����ϴ�."),
	INVITED_PLAYER("&2[&9ȸ��&2]&f &a%target%&f�Կ��� ȸ�� �ʴ��û�� ���½��ϴ�.."),
	INVITED_INFO_TO_TARGET("&2[&9ȸ��&2]&f %company%ȸ������ ���� �ʴ밡 �Խ��ϴ�. %sec%���� �ڵ����� �����˴ϴ�.\n" + 
	"&2[&9ȸ��&2]&f &a/ȸ�� ����&f, &c/ȸ�� ����"
	),
	
	INVITE_DENIED_BY_EXPIRED_TARGET("&2[&9ȸ��&2] &f�ʴ� �������ΰ� �ð��ʰ��� &c�ڵ�����&f�Ǿ����ϴ�."),
	INVITE_DENIED_BY_EXPIRED("&2[&9ȸ��&2]&f %target%�Կ� ���� �ʴ밡 �ð��ʰ��� &c����&f�Ǿ����ϴ�."),

	INVITE_REQUESTED_BY_OTHER("&2[&9ȸ��&2]&f %target%�Կ� ���� �ʴ�� �̹� �ٸ� �÷��̾ �ʴ��� �����̸鼭 �ش� �ʴ밡 ����Ǿ� ���� �ʾ� �ʴ밡 ��ҵǾ����ϴ�. (���� �ش� �÷��̷��� �ʴ��� ���: %other%)"),

	ACCEPTED_INVITE_TARGET("&2[&9ȸ��&2]&f �ʴ븦 �¶��߽��ϴ�. ����� ���� �ش� ȸ���� ����Դϴ�."),
	ACCEPT_INVITE_INVITER("&2[&9ȸ��&2]&f %target%���� �ʴ븦 �¶��߽��ϴ�. ���� %target%���� ���� ȸ���� ����Դϴ�."),
	
	
	DENIED_INVITE_TARGET("&2[&9ȸ��&2]&f &f�ʴ븦 �����߽��ϴ�."),
	DENIED_INVITE_INVITER("&2[&9ȸ��&2]&f %target%���� �ʴ븦 �����߽��ϴ�."),
	

	
	
	CANNOT_INVITE_NO_PERM("&2[&9ȸ��&2]&f &cȸ�� �ʴ�� ȸ��/��ȸ�常 �����մϴ�."),
	CANNOT_KICK_NO_PERM("&2[&9ȸ��&2]&f &cȸ�� �߹��� ȸ��/��ȸ�常 �����մϴ�."),
	KICK_HELP("&2[&9ȸ��&2]&f /ȸ�� �߹� <�г���> &7: <�г���>���� ȸ�翡�� �߹��մϴ�."),
	CANNOT_KICK_ME_LEADER_SUBLEDAERS("&2[&9ȸ��&2]&f &c�ڽ� �Ǵ� ��ȸ��� ȸ���� �߹��� �� �� �����ϴ�.!"),
	NOT_SAME_COMPANY_MEMBER("&2[&9ȸ��&2]&f &c%target%���� ���� ȸ����� �ƴմϴ�!"),
	NO_COMPANY_NAME_SPACE("&2[&9ȸ��&2]&f &cȸ�� �̸��� ���� ���� �ؾ� �մϴ�."),
	NO_COMPANY_NAME_COMPANY("&2[&9ȸ��&2]&f &cȸ�� �̸��� ����� �մϴ�. �ڿ� ȸ��� �ڵ����� �ٽ��ϴ�."),
	CONTAINS_BANNED_CHAR("&2[&9ȸ��&2]&f &c������ ���� ���ڰ� ���ԵǾ� �ֽ��ϴ�. &e������ ���� ����: %banned_word%"),
	TOO_LONG_LONG("&2[&9ȸ��&2]&f &cȸ�� �̸��� �ʹ� ��ϴ�. ȸ���̸��� %max%�� ������ �����մϴ�. ���� �Է��Ͻ� �̸��� %chars%�� �Դϴ�."),

	NAME_CANNOT_HAVE_COLORCODE("&2[&9ȸ��&2]&f &cȸ�� �̸��� ���ڵ尡 ��� �� �� �����ϴ�."),

	CREATE_COMPANY_HELP("&2[&9ȸ��&2]&f /ȸ�� â�� <ȸ���> &7: (������� 700����, �ּ��ں��� 2000���� ���� â�� ����, ȸ���̸��� ���ڵ带 �־ ���� ������� �ʽ��ϴ�. ���� ȸ�� �̸��� �����ñ� �ٶ��ϴ�. �ڿ� ȸ����� ���̽ø� ȸ���̸�ȸ�� ȸ�� �̷������� ��ϵ˴ϴ�.�⺻ �ִ� �ο� 8��) (ȸ�� ������ �ֽ��ϴ�.)"),
	
	PAID_MONEY("&2[&9ȸ��&2]&f %price%���� �Ҹ��߽��ϴ�."),
	REFUND_MONEY("&2[&9ȸ��&2]&f %price%���� ȯ�� �ƽ��ϴ�."),
	FAILED_CREATE_COMPANY("&2[&9ȸ��&2]&f %company% ȸ�縦 â���� ���� �߽��ϴ�."),

	CREATED_COMPANY("&2[&9ȸ��&2]&f %player%���� %company% ȸ�縦 â���߽��ϴ�."),
	
	COMPANY_FULL("&2[&9ȸ��&2]&f &c�� ȸ���� �ο��� ���� á���ϴ�.."),
	KICKED_PLAYER("&2[&9ȸ��&2]&f &c%target%���� ȸ�翡�� �߹��Ͽ����ϴ�.."),
	KICKED_FROM_COMPANY("&2[&9ȸ��&2]&f &c����� %company% ȸ�翡�� �߹� ���Ͽ����ϴ�.."),
		
	
	DELETE_COMPANY_CONFIRM_NOTSET("&2[&9ȸ��&2]&f &cȸ�縦 ��ü �ϽǷ��� ���� \"&6/ȸ�� ��ü&c\" ��ɾ �Է��� �ּ���. &aȸ�� ��ü Ȯ�� �ð��� ����Ǿ��ų� \"/ȸ�� ��ü\" ��ɾ �Է��� ����� �����ϴ�."),
	DELETE_COMPANY_CONFIRM("&2[&9ȸ��&2]&f ȸ�縦 ��ü �ϽǷ��� %sec%�ʳ��� \"&a/ȸ�� ��ü Ȯ��&f\" ��ɾ ����ϼ���."),
	DELETE_COMPANY_CONFIRM_CANCEL("&2[&9ȸ��&2]&c ȸ�� ��ü Ȯ���� ��ҵǾ����ϴ�."),
	DELETE_COMPANY_CMD_HELP("&2[&9ȸ��&2]&f /ȸ�� ��ü &7: ������ ȸ�縦 ��ü�մϴ�."),


	DELETE_COMPANY_CONFIRM_CANCEL_BY_CHANGE("&2[&9ȸ��&2]&c �Ҽӵ� ȸ�簡 ����Ǿ��־� ȸ�� ��ü Ȯ���� ��ҵǾ����ϴ�."),
	DELETED_COMPANY("&2[&9ȸ��&2]&f %player%&f���� %company% ȸ�縦 ��ü�߽��ϴ�."),
	DELETED_COMPANY_ADMIN("&2[&9ȸ��&2]&f ������ %player%&f���� %company% ȸ�縦 ��ü�߽��ϴ�."),
	CREATED_COMPANY_ADMIN("&2[&9ȸ��&2]&f ������ %player%&f���� %company% ȸ�縦 %target% ���Ƿ� â���߽��ϴ�."),

	NO_ENOUGH_MONEY("&2[&9ȸ��&2] &c���� �����մϴ�. &7�߰��� �ʿ��� �ݾ�: %money_need%"),
	
	EMPTY_MEMBER("����"),
	
	DATE_FORMAT("yyyy�� MM�� dd��"),
	TIME_FORMAT_FOR_LIST("yyyy.MM.dd a hh:mm"),
	TIME_FORMAT_FOR_LASTPAID("yyyy.MM.dd a hh:mm"),

	NOT_INTEGER("&2[&9ȸ��&2] &c%value%(��)�� ������ �ƴմϴ�."),
	NOT_NUMBER("&2[&9ȸ��&2] &c%value%(��)�� ���ڰ� �ƴմϴ�."),
	NOT_POSITIVE_NUMBER("&2[&9ȸ��&2] &c�Է� �Ͻ� ���ڴ� ����� �ƴմϴ�."),

	INVAILED_PAGE("&2[&9ȸ��&2] &c�������� 0���� ũ�� %max%������ �������� �մϴ�."),
	INVAILED_MAXSIZE("&2[&9ȸ��&2] &c��ȿ���� �ʴ� ũ���Դϴ�. �ִ��ο��� �ּ� %min%��, �ִ� %max%�� ������ �����մϴ�."),
	INVAILED_MAXSIZE_NOT_ENOUGH("&2[&9ȸ��&2] &c���� ȸ���� �ο��� �����ҷ��� �ִ��ο� ���� Ů�ϴ�. ���� �ο�: %size%��"),
	NOT_POSITIVE_INTEGER("&2[&9ȸ��&2] &c�Է��Ͻ� ���ڴ� ���� ������ �ƴմϴ�."),
	
	ERROR_CALC_PRICE("&2[&9ȸ��&2] &c�ݾ� ����߿� ������ �߻��߽��ϴ�. �����ڿ��� �������ּ���."),
	CANNOT_ADD_MAXSIZE_LIMIT("&2[&9ȸ��&2] &c�߰� ������ �ִ� �ο��� �Ѱ�ġ�� �����߽��ϴ�. �ִ��ο��� %max%������� �����մϴ�."),
	CANNOT_INVITE_MAXSIZE_LIMIT("&2[&9ȸ��&2] &c�߰� ������ �ִ� �ο��� �Ѱ�ġ�� �����Ͽ� �ʴ밡 �Ұ����մϴ�. �ִ��ο��� %max%������� �����մϴ�."),
	CANNOT_ACCEPT_MAXSIZE_LIMIT("&2[&9ȸ��&2] &c�ش� ȸ��� �̹� ������ ����á���ϴ�. �ִ��ο��� %max%������� �����մϴ�."),

	;

	private final String def;

	Lang(String def) {
		this.def = def;
	}

	public static void init(ConfigurationSection section) {
		for (Lang lang : Lang.values()) {

			String key = lang.key();
			if (!section.contains(key)) {
				section.set(key, lang.def);
			}
		}
	}
	
	
	public static String withPlaceHolder(Lang lang, String[] placeholders, Object... values) {

		StringBuffer msg = new StringBuffer(lang.toString());
		for (int i = 0; i < placeholders.length; i++) {
			MCUtils.replaceAll(msg, placeholders[i], String.valueOf(values[i]));
		}

		return msg.toString();

	}
	
	public static String withPlaceHolder(Lang lang, String placeholder, Object value) {

		StringBuffer msg = new StringBuffer(lang.toString());


		MCUtils.replaceAll(msg, placeholder, String.valueOf(value));

		return msg.toString();

	}
	

	public String toString(ConfigurationSection lang) {
		return ChatColor.translateAlternateColorCodes('&', lang.getString(key(), def));
	}

	@Override
	public String toString() {
		return toString(MineCompanyPlugin.plugin.getLangConfig());
	}

	private String key() {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
	}

}
