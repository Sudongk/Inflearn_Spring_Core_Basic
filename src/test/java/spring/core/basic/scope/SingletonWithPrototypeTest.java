package spring.core.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototypeWithJsrProvider() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanWithJsrProvider.class, PrototypeBean.class);

        // given
        ClientBeanWithJsrProvider clientBeanWithJsrProvider1 = ac.getBean(ClientBeanWithJsrProvider.class);

        // when
        int count1 = clientBeanWithJsrProvider1.logic();

        // then
        assertThat(count1).isEqualTo(1);

        // given
        ClientBeanWithJsrProvider clientBeanWithJsrProvider2 = ac.getBean(ClientBeanWithJsrProvider.class);

        // when
        int count2 = clientBeanWithJsrProvider2.logic();

        // then
        assertThat(count2).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototypeWithObjectProvider() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanWithObjectProvider.class, PrototypeBean.class);

        // given
        ClientBeanWithObjectProvider clientBeanWithObjectProvider1 = ac.getBean(ClientBeanWithObjectProvider.class);

        // when
        int count1 = clientBeanWithObjectProvider1.logic();

        // then
        assertThat(count1).isEqualTo(1);

        // given
        ClientBeanWithObjectProvider clientBeanWithObjectProvider2 = ac.getBean(ClientBeanWithObjectProvider.class);

        // when
        int count2 = clientBeanWithObjectProvider2.logic();

        // then
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBeanWithJsrProvider {

        // 자바 표준(JSR-330)에 따른 의존성 주입을 지원하는 인터페이스
        private final Provider<PrototypeBean> prototypeBeanProvider;

        // javax.inject.Inject를 사용하여 의존성 주입
        @Inject
        public ClientBeanWithJsrProvider(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBeanWithObjectProvider {

        // 스프링 프레임워크의 특정 기능으로 지연된 의존성 주입을 지원하는 객체로, 실제 빈 인스턴스를 요청 시점에 가져온다.
        // 따라서 ObjectProvider를 사용하면 프로토타입 빈을 매번 새로운 인스턴스로 가져올 수 있다.
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public ClientBeanWithObjectProvider(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
