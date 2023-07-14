package spring.core.basic.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.basic.AutoAppConfig;
import spring.core.basic.member.MemberService;
import spring.core.basic.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = applicationContext.getBean(MemberService.class);

        // 빈 이름은 memberServiceImpl이 된다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
