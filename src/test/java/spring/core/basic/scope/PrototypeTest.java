package spring.core.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("getBean of PrototypeBean1");
        PrototypeBean PrototypeBean1 = applicationContext.getBean(PrototypeBean.class);

        System.out.println("getBean of PrototypeBean2");
        PrototypeBean PrototypeBean2 = applicationContext.getBean(PrototypeBean.class);


        System.out.println("PrototypeBean1 = " + PrototypeBean1);
        System.out.println("PrototypeBean2 = " + PrototypeBean2);

        // 서로 다른 객체 검증 ( 서로 다른 메모리 주소를 가짐, 프로토타입 스코프 빈이 매번 새로운 인스턴스로 생성되기 때문)
        assertThat(PrototypeBean1).isNotSameAs(PrototypeBean2);

        applicationContext.close();
    }

    // 스코프는 스프링 빈이 매번 새로운 인스턴스로 생성되는 스코프로, 매번 다른 객체를 생성해주는 것을 의미
    // @Scope("prototype") 이를 통해 스프링에게 해당 빈을 프로토타입 스코프로 생성하도록 지시
    // 스코프 빈은 싱글톤과 달리 컨테이너에 의해 소멸되지 않는다. 그래서 프로토타입 빈이 종료될 때 수행할 작업을 직접 처리
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
