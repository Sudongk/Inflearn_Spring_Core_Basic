package spring.core.basic.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.core.basic.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    @DisplayName("MemberService 타입의 이름이 memberService인 빈 찾기")
    void xmlAppContextFindBeanByName() {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("모든 빈 찾기")
    void xmlAppContextFindAllBeans() {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
