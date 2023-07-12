package spring.core.basic;

import spring.core.basic.member.Grade;
import spring.core.basic.member.Member;
import spring.core.basic.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println(findMember);
    }
}
