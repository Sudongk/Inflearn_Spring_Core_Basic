package spring.core.basic.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.core.basic.AppConfig;

public class BeanDefinitionTest {
    // Factory 메소드를 통해 등록하는 방식, 로그를 찍어보면 이 방법은 bean: class[null]이 나온다.
//     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // xml로 설정하면 bean: class[클래스 패키지 명과 이름]이 명확히 드러난다.
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinition = " + beanDefinition +
                        " beanDefinition = " + beanDefinition);
            }
        }
    }
}
