package spring.core.basic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.basic.AppConfig;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
//    private MemberService memberService = new MemberServiceImpl();
    private MemberService memberService;

//    @BeforeEach
//    void setUp() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
//    }

    @BeforeEach
    void setUpByDiContainer() {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
            memberService = applicationContext.getBean("memberService", MemberService.class);
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        assertThat(member).isEqualTo(findMember);
    }
}