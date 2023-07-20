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

        assertThat(PrototypeBean1).isNotSameAs(PrototypeBean2);

        applicationContext.close();
    }

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
