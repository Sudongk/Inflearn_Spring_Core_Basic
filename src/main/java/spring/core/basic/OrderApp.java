package spring.core.basic;

import spring.core.basic.member.Grade;
import spring.core.basic.member.Member;
import spring.core.basic.member.MemberService;
import spring.core.basic.order.Order;
import spring.core.basic.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "itemA", 10000);

        System.out.println(order.getDiscountPrice());
    }
}
