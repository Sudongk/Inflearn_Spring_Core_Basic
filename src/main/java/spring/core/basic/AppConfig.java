package spring.core.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.basic.discount.DiscountPolicy;
import spring.core.basic.discount.FixDiscountPolicy;
import spring.core.basic.discount.RateDiscountPolicy;
import spring.core.basic.member.MemberRepository;
import spring.core.basic.member.MemberService;
import spring.core.basic.member.MemberServiceImpl;
import spring.core.basic.member.MemoryMemberRepository;
import spring.core.basic.order.OrderService;
import spring.core.basic.order.OrderServiceImpl;
import spring.core.basic.singleton.StatefulService;

@Configuration
public class AppConfig {

    /**
     * DI 컨테이너로 전환
     */

//    private MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

    // @Bean(name = "getMemberRepository") 이름 지정이 가능
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

//    private DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
