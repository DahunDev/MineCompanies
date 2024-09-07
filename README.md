# MineCompanies
MineCompanies is a plugin for Minecraft servers, designed to manage company functionalities within the game. 
This plugin allows users to create and manage companies, handle employee invitations and removals, and manage company finances using in-game money. It offers customizable maintenance fees based on company size, with configurations tailored for Korean users.

# Features
**Company Creation and Management:** Create and manage companies with various permissions and settings. <br>
**Company Ownership:** Transfer ownership and manage company ranks.<br>
**Company Economy:** Set company values, manage maintenance fees, and handle unpaid fees.<br>
**Administration Commands:** Special commands for server administrators and moderators.<br>
## Commands

### Company Management Commands (For Server Administrator/Moderator)

- `/회사계정이전 <기존닉네임> <새닉네임>` - Migrate player data from `<기존닉네임>` to `<새닉네임>`. This will update the ownership of companies and reset the old player’s information.
- `/회사관리 창설 <회사명> <닉네임>` - Create a company named `<회사명>` with ownership assigned to `<닉네임>`.
- `/회사관리 회장이전 <회사명> <닉네임>` - Transfer ownership of the company named `<회사명>` to `<닉네임>`.
- `/회사관리 해체 <회사명>` - Dissolve the company named `<회사명>`.
- `/회사관리 정보 <회사명>` - Display information about the company named `<회사명>`.
- `/회사관리 목록 [페이지]` - Show a list of companies by page number.
- `/회사관리 목록-소유자` - List companies with their owner information.
- `/회사관리 추가/삭제 <회사명> <닉네임>` - Add or remove a member from the company named `<회사명>`.
- `/회사관리 최대인원 <회사명> <최대인원>` - Set the maximum number of members for the company named `<회사명>`.
- `/회사관리 이름변경 <기존회사이름> <새이름>` - Change the name of the company from `<기존회사이름>` to `<새이름>`.
- `/회사관리 조회 <닉네임>` - Lookup the company associated with the player `<닉네임>`.
- `/회사관리 설정리로드` - Reload the plugin settings.
- `/회사관리 데이터리로드` - Reload the plugin data.
- `/회사관리 가치금설정 <회사명> <금액>` - Set the value of the company named `<회사명>` to `<금액>`.
- `/회사관리 가치금추가 <회사명> <금액>` - Add `<금액>` to the value of the company named `<회사명>`.
- `/회사관리 가치금차감 <회사명> <금액>` - Decrease the value of the company named `<회사명>` by `<금액>`.
- `/회사관리 미납금추가 <회사명> <금액>` - Add `<금액>` to the unpaid maintenance fees of the company named `<회사명>`.
- `/회사관리 미납금차감 <회사명> <금액>` - Deduct `<금액>` from the unpaid maintenance fees of the company named `<회사명>`.
- `/회사관리 미납금설정 <회사명> <금액>` - Set the unpaid maintenance fee amount for the company named `<회사명>` to `<금액>`.
- `/회사관리 미납횟수추가 <회사명> <횟수>` - Add `<횟수>` to the unpaid maintenance fee count for the company named `<회사명>`.
- `/회사관리 미납횟수차감 <회사명> <횟수>` - Deduct `<횟수>` from the unpaid maintenance fee count for the company named `<회사명>`.
- `/회사관리 미납횟수설정 <회사명> <횟수>` - Set the unpaid maintenance fee count for the company named `<회사명>` to `<횟수>`.
- `/회사관리 순위정렬` - Sort company rankings.
- `/회사관리 데이터저장` - Save the company data.

### General Company Commands

- `/회사 목록 [페이지]` - List companies by page number.
- `/회사 정보 <이름>` - Show information for the company named `<이름>`.
- `/회사 위임 <회사이름> <닉네임>` - Delegate ownership of the company named `<회사이름>` to `<닉네임>`.
- `/회사 위임수락/거절 <회사이름> <닉네임>` - Accept or reject a company ownership request.
- `/회사 수락/거절 <이름>` - Accept or reject an invitation to join a company.
- `/회사 초대 <닉네임>` - Invite a player to join the company.
- `/회사 추방 <닉네임>` - Expel a player from the company.
- `/회사 사원 <닉네임>` - Set a player as a member of the company (not the president or vice-president).
- `/회사 부회장 <닉네임>` - Set a player as the vice-president.
- `/회사 이름변경 <새이름>` - Change the company name to `<새이름>`.
- `/회사 유지비계산 <최대인원>` - Calculate the maintenance fee based on company size.
- `/회사 가치입금 <금액>` - Deposit `<금액>` into the company’s value.
- `/회사 창업 <회사명>` - Create a company with the name `<회사명>`.
- `/회사 탈퇴` - Leave the company.
- `/회사 해체` - Dissolve the company.
- `/회사 목록 [페이지]` - List companies by page number.
- `/회사 조회 <닉네임>` - Lookup the company associated with the player `<닉네임>`.
- `/회사 추가` - Increase the maximum headcount size of the company.
  
# Configuration
The plugin comes with a default configuration file located at plugins/MineCompanies/config.yml. This file allows you to adjust settings related to company creation costs, maintenance fees, and other configurations to suit your server’s needs.
