package net.daniel.MineCompany;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import com.google.common.base.CaseFormat;

import net.daniel.MineCompany.MCUtils.MCUtils;


public enum Lang {
	// Class 명이 파일명과 같을 필요는 없음
	// 이름이 파일에 있는 메세지와 같을것, 띄어쓰기는 _ 로 대체 (메세지 X)

	ALREADY_HAS_COMPANY("&2[&9회사&2]&f %target% 님은 이미 직장이 있습니다."),
	ALREADY_LEADER("&2[&9회사&2]&f %target% 님은 이미 %company% 회사의 회장입니다."),

	ALREADY_HAS_COMPANY_ME("&2[&9회사&2]&f 당신은 이미 직장이 있습니다."),
	MIGRATED_PLAYER_LEADER("&2[&9회사&2]&f 계정변경 명령어로, %before%님이 회장인 %company%회사의 회장이 %after%로 변경되었습니다." ),	
	MIGRATED_PLAYER_SUBLEADER("&2[&9회사&2]&f 계정변경 명령어로, %before%님이 부회장인 %company%회사의 부회장이 %before% 대신 %after%로 변경되었습니다."),
	MIGRATED_PLAYER_MEMBER("&2[&9회사&2]&f 계정변경 명령어로, %before%님이 사원인 %company%회사의 부회장이 %before% 대신 %after%로 변경되었습니다."),
	MIGRATE_PLAYER_HELP("&2[&9회사&2]&f /회사계정이전 <기존닉네임> <새닉네임>"),
	
	COMPANY_MANAGE_HELP("&2[&9회사&2]&f /회사관리 창설 <회사명> <닉네임>\n" +
	
 	"&2[&9회사&2]&f /회사관리 회장이전 <회사명> <닉네임>\n"+

	"&2[&9회사&2]&f /회사관리 해체 <회사명>\n"+
	"&2[&9회사&2]&f /회사관리 정보 <회사명>\n"+

	"&2[&9회사&2]&f /회사관리 목록 [페이지]\n"+
	"&2[&9회사&2]&f /회사관리 목록-소유자\n"+	
	"&2[&9회사&2]&f /회사관리 추가/삭제 <회사명> <닉네임>\n"+
	"&2[&9회사&2]&f /회사관리 최대인원 <회사명> <최대인원>\n" +
	"&2[&9회사&2]&f /회사관리 이름변경 <기존회사이름> <새이름> &7: <기존회사이름> 회사의 회사 이름을 <새이름>으로 변경합니다.\n" +
	"&2[&9회사&2]&f /회사관리 조회 <닉네임>\n" +
	"&2[&9회사&2]&f /회사관리 설정리로드\n" +
	"&2[&9회사&2]&f /회사관리 데이터리로드\n" +
	"&2[&9회사&2]&f /회사관리 가치금설정 <회사명> <금액>\n" +
	"&2[&9회사&2]&f /회사관리 가치금추가 <회사명> <금액>\n" +
	"&2[&9회사&2]&f /회사관리 가치금차감 <회사명> <금액>\n" +
	"&2[&9회사&2]&f /회사관리 미납금추가 <회사명> <금액>\n" +
	"&2[&9회사&2]&f /회사관리 미납금차감 <회사명> <금액>\n" +
	"&2[&9회사&2]&f /회사관리 미납금설정 <회사명> <금액>\n" +
	
	"&2[&9회사&2]&f /회사관리 미납횟수추가 <회사명> <횟수>\n" +
	"&2[&9회사&2]&f /회사관리 미납횟수차감 <회사명> <횟수>\n" +
	"&2[&9회사&2]&f /회사관리 미납횟수설정 <회사명> <횟수>\n" +
	"&2[&9회사&2]&f &2[&9회사&2]&f /회사관리 순위정렬 &7: 회사 목록에 나오는 회사 순위를 갱신합니다.\n" +
	"&2[&9회사&2]&f /회사관리 데이터저장"),
	
	WARN_COLOR("&c"),
	SAFE_COLOR("&f"),
	
	DAYS("일"),
	HOURS("시간"),
	MINUTES("분"),
	SECONDS("초"),
	
	
	RELOADED_CONFIG("&2[&9회사&2]&f 설정 리로드 완료."),
	RELOADED_DATA("&2[&9회사&2]&f 데이터 리로드 완료."),

	COMPANY_CREATE_ADMIN_HELP("&2[&9회사&2]&f /회사관리 창설 <회사명> <닉네임>"),
	
	COMPANY_FORCE_ADD_MEMBER("&2[&9회사&2]&f 관리자에 의해 %target%님이 %company% 회사 사원으로 추가됐습니다."),
	COMPANY_FORCE_ADD_MEMBER_TO_TARGET("&2[&9회사&2] &f관리자에 의해 %company% 회사의 사원이 되었습니다."),

	COMPANY_ADMIN_ADD_MEMBER_HELP("&2[&9회사&2]&f /회사관리 추가 <회사이름> <닉네임>"),
	COMPANY_ADMIN_MANDATE_HELP("&2[&9회사&2]&f /회사관리 회장이전 <회사명> <닉네임>"),
	COMPANY_ADMIN_DELETE_COMPANY_HELP("&2[&9회사&2]&f /회사관리 해체 <회사명>"),
	COMPANY_ADMIN_REMOVE_MEMBER_HELP("&2[&9회사&2]&f /회사관리 삭제 <회사이름> <회사원이름>"),
	COMPANY_FORCE_SET_MAXSIZE(" &2[&9회사&2]&f 관리자(%player%)님에 의해 %company% 회사의 최대인원을 %size%명으로 설정."),
	COMPANY_SET_MAXSIZE("&2[&9회사&2]&f %company% 회사의 최대인원을 %size%명으로 설정."),

	COMPANY_ADMIN_MAXSIZE_HELP("&2[&9회사&2]&f /회사관리 최대인원 <회사이름> <양의 정수>"),
	
	COMPANY_LIST_WITH_OWNER_HELP("&2[&9회사&2]&f /회사관리 목록-소유자 [페이지]"),
	COMPANY_LIST_ADMIN_HELP("&2[&9회사&2]&f /회사관리 목록 [페이지]"),
	

	
	COMPANY_SET_VALUE_ADMIN_HELP("&2[&9회사&2]&f /회사관리 가치금설정 <회사명> <금액>"),

	COMPANY_ADD_VALUE_ADMIN_HELP("&2[&9회사&2]&f /회사관리 가치금추가 <회사명> <금액>"),

	COMPANY_SUBTRACT_VALUE_ADMIN_HELP("&2[&9회사&2]&f /회사관리 가치금차감 <회사명> <금액>"),

	COMPANY_ADD_UNPAID_ADMIN_HELP("&2[&9회사&2]&f /회사관리 미납금추가 <회사명> <금액>"),
	
	COMPANY_SUBTRACT_UNPAID_ADMIN_HELP("&2[&9회사&2]&f /회사관리 미납금차감 <회사명> <금액>"),

	COMPANY_SET_UNPAID_ADMIN_HELP("&2[&9회사&2]&f /회사관리 미납금설정 <회사명> <금액>"),

	COMPANY_ADD_UNPAID_TIMES_ADMIN_HELP("&2[&9회사&2]&f /회사관리 미납횟수추가 <회사명> <횟수>"),
	COMPANY_SUBTRACT_UNPAID_TIMES_ADMIN_HELP("&2[&9회사&2]&f /회사관리 미납횟수차감 <회사명> <횟수>" ),

	COMPANY_SET_UNPAID_TIMES_ADMIN_HELP("&2[&9회사&2]&f /회사관리 미납횟수설정 <회사명> <횟수>"),
	
	
	CALC_RANK_DONE("&2[&9회사&2]&f 회사 순위 정렬 완료, 소요된 시간 %ms% ms"),

	COMPANY_SET_VALUE_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 가치금액이 %value%원으로 설정되었습니다."),

	
	COMPANY_ADD_VALUE_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 가치금액이 %amount%원이 추가되었습니다."),
	COMPANY_ADD_VALUE("&2[&9회사&2]&f %company% 회사의 가치금액이 %amount%원이 추가되어 현재 가치금액이 %value%원 입니다."),
	COMPANY_ADD_VALUE_HELP("&2[&9회사&2]&f /회사 가치입금 <금액> &7: 회사 가치에 <금액>원 만큼 입금합니다. 회사 가치는 유지비 납부 및 순위 계산에 쓰이며 출금은 불가능합니다."),

	
	
	COMPANY_SUBTRACT_VALUE_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 가치금액이 %amount%원 차감되었습니다."),
	COMPANY_SUBTRACT_VALUE("&2[&9회사&2]&f %company% 회사의 가치금액이 %amount%원이 차감되어 현재 가치금액이 %value%원 입니다."),

	
	COMPANY_SET_UNPAID_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 유지비 미납금이 %unPaid%원으로 설정되었습니다."),

	COMPANY_ADD_UNPAID_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 유지비 미납금이 %amount%원 추가되었습니다."),
	COMPANY_ADD_UNPAID("&2[&9회사&2]&f %company% 회사의 유지비 미납금이 %amount%원이 추가되어 현재 미납금이 %unPaid%원 입니다."),
	COMPANY_SUBTRACT_UNPAID_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 유지비 미납금이 %amount%원 차감되었습니다."),
	COMPANY_SUBTRACT_UNPAID("&2[&9회사&2]&f %company% 회사의 유지비 미납금이 %amount%원이 차감되어 현재 미납금이 %unPaid%원 입니다."),

	
	COMPANY_SET_UNPAID_TIMES_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 유지비 미납횟수가 %unPaid_times%회로 설정되었습니다."),

	COMPANY_ADD_UNPAID_TIMES_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 유지비 미납횟수가 %amount%회 추가되었습니다."),
	COMPANY_ADD_UNPAID_TIMES("&2[&9회사&2]&f %company% 회사의 유지비 미납 횟수가 %amount%회 추가되어 현재 미납 횟수가 %unPaid_times%회 입니다."),

	COMPANY_SUBTRACT_UNPAID_TIMES_BY_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 %company%회사의 유지비 미납횟수가 %amount%회 차감되었습니다."),
	COMPANY_SUBTRACT_UNPAID_TIMES("&2[&9회사&2]&f %company% 회사의 유지비 미납 횟수가 %amount%회 차감되어 현재 미납 횟수가 %unPaid_times%회 입니다."),

	
	
	COMPANY_DATA_SAVED("&2[&9회사&2]&f 회사 데이터가 저장됨"),
	
	COMPANY_LOOKUP_ADMIN_HELP("&2[&9회사&2]&f /회사관리 조회 <닉네임>"),

	COMPANY_FORCE_REMOVE_MEMBER("&2[&9회사&2]&f 관리자에 의해 %target%님이 %company% 회사에서 탈퇴되었습니다."),
	COMPANY_FORCE_REMOVE_MEMBER_TARGET("&2[&9회사&2]&f 관리자에  %company% 회사에서 탈퇴되었습니다."),

	COMPANY_FORCE_MANDATE_DONE("&2[&9회사&2]&f 관리자에 의해  %company% 회사의 회장이 %from%님에서 %after%님으로 변경되었습니다."),

	LEAVE_COMPANY("&2[&9회사&2]&f 당신은 %company% 회사를 탈퇴했습니다."),
	CANNOT_LEAVE_COMPANY_LEADER("&2[&9회사&2]&f 회장은 회사에서 탈퇴 할 수 없습니다. 해체를 원한 다면 \"/회사 해체\"를 이용해 주세요."),
	
		
	COMPANY_FORCEREMOVE_MEMBER_TO_TARGET("&2[&9회사&2]&f 관리자에 의해 %company% 회사에서 추방되었습니다."),
	CANNOT_KICK_LEADER("&2[&9회사&2]&f 회장을 추방하는 것은 불가능합니다."),
	NOT_MEMBER_TARGET("&2[&9회사&2]&f %target%님은 그 %company% 회사의 소속이 아닙니다."),
	NOT_MEMBER_MYSELF("&2[&9회사&2]&f 당신은 그 회사에 소속되어 있지 않습니다."),
			
	COMPANY_LIST_HELP("&2[&9회사&2]&f /회사 목록 [페이지]"),
	
	COMPANY_NOT_EXIST_THAT_NAME("&2[&9회사&2]&f %company% 회사는 존재하지 않습니다."),
	
	NAME_WITH_OWNER("&b[%rank%] &fcompany% 회사 - 소유자:%leader% - 회사가치금:%values%"),
	
	COMPANY_LIST("&2[&9회사&2]&f ============== (정렬기준: 가치금액, 갱신 시각: %time%)\n"+
	"%company_list%\n"+
			"&2[&9회사&2]&f 현재페이지: %page% / %maxPage%"
			),
	COMPANY_LIST_LINE("&b[%rank%] &f%company% 회사"),
	
	COMPANY_INFO("&2[&9회사&2]&f ================= &b정보 &f=================\n"
			+ "&2[&9회사&2] &9회사명: &f%company%\n"
			+ "&2[&9회사&2] &4회장: &f%leader%\n"
			+ "&2[&9회사&2] &c부회장: &f%sub_leaders%\n"
			+ "&2[&9회사&2] &e사원: &f%members%\n"
			+ "&2[&9회사&2] &a인원: &f%size%/%max_size%\n"
			+ "&2[&9회사&2] &6회사 가치금액 : &f%value%원\n"
			+ "&2[&9회사&2] &a회사 유지비 : &f%money_for_maintain%원/%maintain_time%\n"
			+ "&2[&9회사&2] &c회사 유지비 미납금 : &f%unPaid_bal%&f원\n"
			+ "&2[&9회사&2] &c회사 유지비 미납 횟수 : &f%amount_unpaid%&f회 &7(%max_unPaid%회 초과시 회사 강제 해체)\n"
			+ "&2[&9회사&2] &a최근 유지비 납부 날짜: &f%date_of_lastPaid%\n"
			+ "&2[&9회사&2] &9회사창설일자: &f%startDate%"
			),
	
	COMPANY_INFO_MINE("&2[&9회사&2]&f ================= &b당신이 속한 회사 정보 &f=================\n"
			+ "&2[&9회사&2] &9회사명: &f%company%\n"
			+ "&2[&9회사&2] &4회장: &f%leader%\n"
			+ "&2[&9회사&2] &c부회장: &f%sub_leaders%\n"
			+ "&2[&9회사&2] &e사원: &f%members%\n"
			+ "&2[&9회사&2] &a인원: &f%size%/%max_size%\n"
			+ "&2[&9회사&2] &6회사 가치금액 : &f%value%원\n"
			+ "&2[&9회사&2] &a회사 유지비 : &f%money_for_maintain%원/%maintain_time%\n"
			+ "&2[&9회사&2] &c회사 유지비 미납금 : &f%unPaid_bal%&f원\n"
			+ "&2[&9회사&2] &c회사 유지비 미납 횟수 : &f%amount_unpaid%&f회 &7(%max_unPaid%회 초과시 회사 강제 해체)\n"
			+ "&2[&9회사&2] &a최근 유지비 납부 날짜: &f%date_of_lastPaid%\n"
			+ "&2[&9회사&2] &9회사창설일자: &f%startDate%"
			),
	
	COMPANY_HELP("&2[&9회사&2]&f /회사 정보 <이름>\n" +
			"&2[&9회사&2]&f /회사 위임 <회사이름> <닉네임>\n" +
			"&2[&9회사&2]&f /회사 위임수락/거절 <회사이름> <닉네임>\n" +
			"&2[&9회사&2]&f /회사 수락/거절 <이름>\n" +
			"&2[&9회사&2]&f /회사 초대 <닉네임>\n" +
			"&2[&9회사&2]&f /회사 추방 <닉네임>\n" +
			"&2[&9회사&2]&f /회사 사원 <닉네임>\n" +
			"&2[&9회사&2]&f /회사 부회장 <닉네임>\n" +
			"&2[&9회사&2]&f /회사 이름변경 <새이름> &7: 200만원을 소비하여 회사 이름을 변경합니다.\n" +
			"&2[&9회사&2]&f /회사 유지비계산 <최대인원> &7: 최대인원이 <최대인원>일때의 회사 가치금액에서 납부되는 회사 유지비를 계산합니다.\n" +
			"&2[&9회사&2]&f /회사 가치입금 <금액> &7: 회사 가치에 <금액>원 만큼 입금합니다. 회사 가치는 유지비 납부 및 순위 계산에 쓰이며 출금은 불가능합니다.\n" +
			"&2[&9회사&2]&f /회사 창업 <회사명> &7: (증설비용 700만원, 최소자본금 2000만원 부터 창설 가능, 회사이름에 색코드를 넣어도 색이 적용되지 않습니다. 또한 회사 이름만 적으시길 바랍니다. 뒤에 회사까지 붙이시면 회사이름회사 회사 이런식으로 등록됩니다.기본 최대 인원 8명)\n" +
			"&2[&9회사&2]&f /회사 탈퇴\n" +
			"&2[&9회사&2]&f /회사 해체\n" +
			"&2[&9회사&2]&f /회사 목록 [페이지]\n" +
			"&2[&9회사&2]&f /회사 조회 <닉네임> &7: (닉네임)의 소속회사를 확인합니다.\n" +
			"&2[&9회사&2]&f /회사 추가 &7: (최대인원을 1명 증가, 20명까지는 1명 추가시 100만원 , 20~30명 까지는 1명 추가시 250만원, 30~50 명 까지는 1명 추가시 1500만원, 50명 이상은 1명 추가시 1억원 소요. 회장만 가능) (인원에 따라서 회사 유지비 증가)"
),
	
	CALC_MAINTAIN_FEE_HELP("&2[&9회사&2]&f /회사 유지비계산 <최대인원> &7: 최대인원이 <최대인원>일때의 회사 가치금액에서 납부되는 회사 유지비를 계산합니다."),
	MAINTAIN_FEE_INFO("&2[&9회사&2]&f회사 최대인원인이 %size%명인 경우 %price%원이 %interval%마다 납부됩니다. (최대 허용 미납 횟수: %max_unPaid%회)"),
	
	COMPANY_DELETED_BY_UNPAID("&2[&9회사&2]&f %company%회사가 회사 유지비를 일정 횟수 이상 미납으로 강제해체 됐습니다."),

	
	COMPANY_CHNAGE_NAME_HELP("&2[&9회사&2]&f /회사 이름변경 <새이름> &7: 200만원을 소비하여 회사 이름을 변경합니다."),
	COMPANY_CHNAGE_NAME_ADMIN_HELP("&2[&9회사&2]&f /회사관리 이름변경 <기존회사이름> <새이름> &7: <기존회사이름> 회사의 회사 이름을 <새이름>으로 변경합니다."),
	

	COMPANY_CHNAGED_NAME_MEMBERS("&2[&9회사&2]&f 당신이 소속된 회사의 이름이 %company%로 변경되었습니다."),
	
	COMPANY_CHNAGED_NAME_BROADCAST("&2[&9회사&2]&f 회장 %player%님에 의해 기존의 %oldCompany% 회사의 이름이 %company%로 변경되었습니다."),

	COMPANY_CHNAGED_NAME_BROADCAST_ADMIN("&2[&9회사&2]&f 관리자 %player%님에 의해 기존의 %oldCompany% 회사의 이름이 %company%로 변경되었습니다."),

	FAILED_COMPANY_CHNAGE_NAME("&2[&9회사&2]&f 회사 이름 변경에 실패했습니다."),


	
	
	
	CANNOT_SET_LEADER_TO_MEMBER("&2[&9회사&2]&f 회장을 사원으로 변경할 수 없습니다."),
	COMPANY_SET_MEMBER_HELP("&2[&9회사&2]&f /회사 사원 <닉네임> &7: <닉네임>님을 사원으로 설정합니다."),
	NOW_MEMBER_TARGET("&2[&9회사&2]&f %target%님은 이제 %company%회사의 사원입니다."),
	NOW_MEMBER_ME("&2[&9회사&2]&f 당신은 이제 %company%회사의 사원입니다."),
	FAILED_SET_MEMBER("&2[&9회사&2]&f %target%님을 %company%회사의 부회장으로 설정하는 것을 실패했습니다."),

	
	COMPANY_SET_SUBLEADER_HELP("&2[&9회사&2]&f /회사 부회장 <닉네임> &7: <닉네임>님을 부회장으로 설정합니다."),
	NOW_SUBLEADER_TARGET("&2[&9회사&2]&f %target%님은 이제 %company%회사의 부회장 입니다."),
	NOW_SUBLEADER_ME("&2[&9회사&2]&f 당신은 이제 %company%회사의 부회장 입니다."),
	FAILED_SET_SUBLEADER("&2[&9회사&2]&f %target%님을 %company%회사의 부회장으로 설정하는 것을 실패했습니다."),

	
	
	JUST_PAID_MAINTAIN_FEE("&2[&9회사&2]&f %company%회사 유지비로 %price%원을 가치금액에서 납부하여 현재 가치금액은 %value%원 입니다."),
	JUST_UNPAID_MAINTAIN_FEE("&2[&9회사&2]&f %company%회사 유지비를 납부할 회사 가치금액이 %need%원 부족하여 미납 횟수가 추가되었습니다. &f현재 미납금: %unPaid%, 현재 미납 횟수: %unPaid_times%/%max_unPaid%회"),

	
	
	
	CANNOT_CHANGE_RANK_MINE("&2[&9회사&2]&f 자기 자신의 직급은 변경 할 수 없습니다."),
	COMPANY_NOTEXIST("&2[&9회사&2] &f%company% 회사는 존재 하지 않습니다."),
	COMPANY_ALREADY_EXIST("&2[&9회사&2] &f%company% 회사는 이미 존재합니다."),

	
	
	COMPANY_VALUE_ADD_CMD_HELP("&2[&9회사&2]&f /회사 가치입금 <금액> &7: 회사 가치에 <금액>원 만큼 입금합니다. 회사 가치는 유지비 납부 및 순위 계산에 쓰이며 출금은 불가능합니다."),
	COMPANY_INFO_ADMIN_HELP("&2[&9회사&2]&f /회사 정보 <회사 이름>"),
	
	EMPTY_LIST("없음"),
	NO_PERM("&b&l[ &f&lMine SV &b&l] &c권한이 없습니다."),

	
	MANDATE_HELP("&2[&9회사&2]&f /회사 위임 <회사이름> <닉네임>"),
	MANDATE_DENY_HELP("&2[&9회사&2]&f /회사 위임거절 <회사이름> <닉네임>"),
	MANDATE_ACCEPT_HELP("&2[&9회사&2]&f /회사 위임수락 <회사이름> <닉네임>"),

	
	MANDATE_LEADER_DONE("&2[&9회사&2]&f &a위임 완료."),
	MANDATE_LEADER_FAILED("&2[&9회사&2]&c &a%company% 위임 처리 실패, 회장이 이미 변경되었거나, 위임 대상이 더이상 같은 회사원이 아닙니다."),
	NOT_MATCH_THE_COMPANY_NAME("&2[&9회사&2]&c 회사 이름이 일치하지 않습니다."),
	NOT_MATCH_THE_PLAYER_NAME("&2[&9회사&2]&c 플레이어 이름이 일치하지 않습니다."),


	MANDATE_LEADER_BROADCAST("&2[&9회사&2]&f %company%회사 회장 %leader%님이 %target%님에게 회장을 위임했습니다."),
	
	CANNOT_MANDATE_CHANGED_COMPANY("&2[&9회사&2]&c 더이상 같은 회사 소속이 아니여서 위임 신청이 취소됐습니다."),
	
	CANNOT_MANDATE_CHANGED_LEADER("&2[&9회사&2]&c 위임 승락전에 지도자가 이미 변경이 되어 해당 위임 확인이 취소됐습니다."),

	CANNOT_MANDATE_ANOTHER_COMPANY("&2[&9회사&2]&f %target%님은 같은 회사 소속이 아닙니다."),
	ADMIN_CANNOT_MANDATE_NOT_MEMEBER("&2[&9회사&2]&f %target%님은 %company% 회사 소속이 아닙니다."),

	CANNOT_MANDATE_TO_OWNER("&2[&9회사&2]&f 해당 플레이어는 회장입니다."),

	MANDATE_LEADER_DENIED("&2[&9회사&2]&f &a위임 신청을 거절했습니다."),
	MANDATE_LEADER_DENIED_TO_OWNER("&2[&9회사&2]&f &a%target%님에 대한 위임이 거절됐습니다."),

	MANDATE_LEADER_NOT_REQUESTED("&2[&9회사&2] &c위임 신청을 받은 적이 없습니다."),
	MANDATE_LEADER_REQUESTED("&2[&9회사&2]&f &a%company% 회사의 사원 %target%&f님에게 회장 위임 신청을 하였습니다. 응답이 없을 경우 %sec%후 신청이 자동으로 취소됩니다."),
	MANDATE_LEADER_REQUESTED_TARGET("&2[&9회사&2]&f &a회사장 위임 신청이 들어왔습니다. %sec%초후에 자동으로 거절됩니다.\n" +
	
			"&2[&9회사&2]&f 수락 하실려면, &a/회사 위임수락 %company% <본인이름> &f명령어를, 거절하실려면 &e/회사 위임거절 %company% <본인이름> &f명령어를 입력해주세요."),

	MANDATE_CONFIRM_CANCELED("&2[&9회사&2]&f &c회사 위임신청이 취소되었습니다."),
	MANDATE_DENIED_BY_EXPIRED("&2[&9회사&2]&f 시간초과로 &c거절&f되었습니다."),

	MANDATE_LEADER_ALREADY_REQUESTED("&2[&9회사&2]&f &c이미 해당 플레이어에게 위임 신청을 보낸 상태 입니다."),
	CANNOT_MANDATE_MYSELF("&2[&9회사&2]&f &c자기 자신에게는 위임할 수 없습니다."),
	ONLY_LEADER("&2[&9회사&2] &f해당작업은 회장만 가능합니다."),
	
	ONLY_PLAYER("&2[&9회사&2] &f해당작업은 플레이어만 가능합니다."),

	
	LOOKUP_PLAYER_COMPANY_HELP("&2[&9회사&2]&f /회사 조회 <닉네임>"),
	
	NO_COMPANY("&2[&9회사&2]&f &c당신은 소속된 회사가 없습니다."),
	PLAYER_HAS_NO_COMPANY("&2[&9회사&2]&f %target%님은 소속된 회사가 없습니다."),

	PLAYER_LOOKUP_COMPANY("&2[&9회사&2]&f %target%님은 %company% 회사에 소속되어 있습니다."),
	PLAYER_NOT_ONLINE("&2[&9회사&2]&f &c%target%님은 온라인이 아닙니다."),
	
	INVITE_HELP("&2[&9회사&2]&f /회사 초대 <닉네임> &7: <닉네임>님을 본인 회사에 초대합니다."),
	ALREADY_INVITED("&2[&9회사&2]&f &c이미 초대를 했습니다.."),

	NOT_INVITED("&2[&9회사&2]&f &c당신에게 온 초대가 없습니다."),
	INVITED_PLAYER("&2[&9회사&2]&f &a%target%&f님에게 회사 초대신청을 보냈습니다.."),
	INVITED_INFO_TO_TARGET("&2[&9회사&2]&f %company%회사으로 부터 초대가 왔습니다. %sec%초후 자동으로 거절됩니다.\n" + 
	"&2[&9회사&2]&f &a/회사 수락&f, &c/회사 거절"
	),
	
	INVITE_DENIED_BY_EXPIRED_TARGET("&2[&9회사&2] &f초대 수락여부가 시간초과로 &c자동거절&f되었습니다."),
	INVITE_DENIED_BY_EXPIRED("&2[&9회사&2]&f %target%님에 대한 초대가 시간초과로 &c거절&f되었습니다."),

	INVITE_REQUESTED_BY_OTHER("&2[&9회사&2]&f %target%님에 대한 초대는 이미 다른 플레이어가 초대한 상태이면서 해당 초대가 만료되어 있지 않아 초대가 취소되었습니다. (현재 해당 플레이러를 초대한 사람: %other%)"),

	ACCEPTED_INVITE_TARGET("&2[&9회사&2]&f 초대를 승락했습니다. 당신은 이제 해당 회사의 멤버입니다."),
	ACCEPT_INVITE_INVITER("&2[&9회사&2]&f %target%님이 초대를 승락했습니다. 이제 %target%님은 같은 회사의 멤버입니다."),
	
	
	DENIED_INVITE_TARGET("&2[&9회사&2]&f &f초대를 거절했습니다."),
	DENIED_INVITE_INVITER("&2[&9회사&2]&f %target%님이 초대를 거절했습니다."),
	

	
	
	CANNOT_INVITE_NO_PERM("&2[&9회사&2]&f &c회사 초대는 회장/부회장만 가능합니다."),
	CANNOT_KICK_NO_PERM("&2[&9회사&2]&f &c회사 추방은 회장/부회장만 가능합니다."),
	KICK_HELP("&2[&9회사&2]&f /회사 추방 <닉네임> &7: <닉네임>님을 회사에서 추방합니다."),
	CANNOT_KICK_ME_LEADER_SUBLEDAERS("&2[&9회사&2]&f &c자신 또는 부회장과 회장은 추방을 할 수 없습니다.!"),
	NOT_SAME_COMPANY_MEMBER("&2[&9회사&2]&f &c%target%님은 같은 회사원이 아닙니다!"),
	NO_COMPANY_NAME_SPACE("&2[&9회사&2]&f &c회사 이름은 띄어쓰기 없이 해야 합니다."),
	NO_COMPANY_NAME_COMPANY("&2[&9회사&2]&f &c회사 이름만 적어야 합니다. 뒤에 회사는 자동으로 붙습니다."),
	CONTAINS_BANNED_CHAR("&2[&9회사&2]&f &c허용되지 않은 문자가 포함되어 있습니다. &e허용되지 않은 문자: %banned_word%"),
	TOO_LONG_LONG("&2[&9회사&2]&f &c회사 이름이 너무 깁니다. 회사이름은 %max%자 까지만 가능합니다. 현재 입력하신 이름은 %chars%자 입니다."),

	NAME_CANNOT_HAVE_COLORCODE("&2[&9회사&2]&f &c회사 이름에 색코드가 들어 갈 수 없습니다."),

	CREATE_COMPANY_HELP("&2[&9회사&2]&f /회사 창업 <회사명> &7: (증설비용 700만원, 최소자본금 2000만원 부터 창설 가능, 회사이름에 색코드를 넣어도 색이 적용되지 않습니다. 또한 회사 이름만 적으시길 바랍니다. 뒤에 회사까지 붙이시면 회사이름회사 회사 이런식으로 등록됩니다.기본 최대 인원 8명) (회사 유지비가 있습니다.)"),
	
	PAID_MONEY("&2[&9회사&2]&f %price%원을 소모했습니다."),
	REFUND_MONEY("&2[&9회사&2]&f %price%원이 환불 됐습니다."),
	FAILED_CREATE_COMPANY("&2[&9회사&2]&f %company% 회사를 창업에 실패 했습니다."),

	CREATED_COMPANY("&2[&9회사&2]&f %player%님이 %company% 회사를 창업했습니다."),
	
	COMPANY_FULL("&2[&9회사&2]&f &c그 회사의 인원은 가득 찼습니다.."),
	KICKED_PLAYER("&2[&9회사&2]&f &c%target%님을 회사에서 추방하였습니다.."),
	KICKED_FROM_COMPANY("&2[&9회사&2]&f &c당신은 %company% 회사에서 추방 당하였습니다.."),
		
	
	DELETE_COMPANY_CONFIRM_NOTSET("&2[&9회사&2]&f &c회사를 해체 하실려면 먼저 \"&6/회사 해체&c\" 명령어를 입력해 주세요. &a회사 해체 확인 시간이 만료되었거나 \"/회사 해체\" 명령어를 입력한 기록이 없습니다."),
	DELETE_COMPANY_CONFIRM("&2[&9회사&2]&f 회사를 해체 하실려면 %sec%초내에 \"&a/회사 해체 확인&f\" 명령어를 사용하세요."),
	DELETE_COMPANY_CONFIRM_CANCEL("&2[&9회사&2]&c 회사 해체 확인이 취소되었습니다."),
	DELETE_COMPANY_CMD_HELP("&2[&9회사&2]&f /회사 해체 &7: 본인의 회사를 해체합니다."),


	DELETE_COMPANY_CONFIRM_CANCEL_BY_CHANGE("&2[&9회사&2]&c 소속된 회사가 변경되어있어 회사 해체 확인이 취소되었습니다."),
	DELETED_COMPANY("&2[&9회사&2]&f %player%&f님이 %company% 회사를 해체했습니다."),
	DELETED_COMPANY_ADMIN("&2[&9회사&2]&f 관리자 %player%&f님이 %company% 회사를 해체했습니다."),
	CREATED_COMPANY_ADMIN("&2[&9회사&2]&f 관리자 %player%&f님이 %company% 회사를 %target% 명의로 창설했습니다."),

	NO_ENOUGH_MONEY("&2[&9회사&2] &c돈이 부족합니다. &7추가로 필요한 금액: %money_need%"),
	
	EMPTY_MEMBER("없음"),
	
	DATE_FORMAT("yyyy년 MM월 dd일"),
	TIME_FORMAT_FOR_LIST("yyyy.MM.dd a hh:mm"),
	TIME_FORMAT_FOR_LASTPAID("yyyy.MM.dd a hh:mm"),

	NOT_INTEGER("&2[&9회사&2] &c%value%(은)는 정수가 아닙니다."),
	NOT_NUMBER("&2[&9회사&2] &c%value%(은)는 숫자가 아닙니다."),
	NOT_POSITIVE_NUMBER("&2[&9회사&2] &c입력 하신 숫자는 양수가 아닙니다."),

	INVAILED_PAGE("&2[&9회사&2] &c페이지는 0보다 크고 %max%이하의 정수여야 합니다."),
	INVAILED_MAXSIZE("&2[&9회사&2] &c유효하지 않는 크기입니다. 최대인원은 최소 %min%명, 최대 %max%명 까지만 가능합니다."),
	INVAILED_MAXSIZE_NOT_ENOUGH("&2[&9회사&2] &c현재 회사의 인원이 설정할려는 최대인원 보다 큽니다. 현재 인원: %size%명"),
	NOT_POSITIVE_INTEGER("&2[&9회사&2] &c입력하신 숫자는 양의 정수가 아닙니다."),
	
	ERROR_CALC_PRICE("&2[&9회사&2] &c금액 계산중에 오류가 발생했습니다. 관리자에게 문의해주세요."),
	CANNOT_ADD_MAXSIZE_LIMIT("&2[&9회사&2] &c추가 가능한 최대 인원의 한계치에 도달했습니다. 최대인원은 %max%명까지만 가능합니다."),
	CANNOT_INVITE_MAXSIZE_LIMIT("&2[&9회사&2] &c추가 가능한 최대 인원의 한계치에 도달하여 초대가 불가능합니다. 최대인원은 %max%명까지만 가능합니다."),
	CANNOT_ACCEPT_MAXSIZE_LIMIT("&2[&9회사&2] &c해당 회사는 이미 정원이 가득찼습니다. 최대인원은 %max%명까지만 가능합니다."),

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
