package spring.core.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.basic.AppConfig;
import spring.core.basic.member.MemberRepository;
import spring.core.basic.member.MemberServiceImpl;
import spring.core.basic.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationTest {

    @Test
    void configurationTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 테스팅을 위한 getter 메소드를 쓰기 위해 구현 클래스로 검색. 원래 지양하는 방식이다.
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 각 memberRepository의 주소값 확인을 위한 출력
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        // Annotation 기반으로 등록된 빈들은 싱글톤으로 생성되는 것을 확인
        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeeply() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // applicationContext가 등록될 때 @Configuration안에 있는 빈들 생성
        // call AppConfig.memberService
        // call AppConfig.memberRepository
        // call AppConfig.orderService
        // call AppConfig.discountPolicy
        // Bean으로 등록된 메소드는 최초 실행 시 DI 컨테이너에 등록되며, 이후 호출되면 DI 컨테이너에서 리턴한다.
    }
}
