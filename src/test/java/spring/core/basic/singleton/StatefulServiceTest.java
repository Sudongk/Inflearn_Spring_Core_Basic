package spring.core.basic.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        //given
        // @Bean 으로 등록된 빈들은 싱글톤으로 만들어져서 같은 인스턴스를 공유한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        // when
        statefulService1.order("userA", 10000);

        // 동일한 인스턴스를 참조하기 때문에 statefulService2.order("userB", 20000)에 의해 변경
        statefulService2.order("userB", 20000);

        int price = statefulService1.getPrice();

        // then
        assertThat(price).isEqualTo(20000);
    }

    @Test
    void notStatefulService() {
        // given
        TestConfig2 testConfig2 = new TestConfig2();

        StatefulService statefulService1 = testConfig2.statefulService();
        StatefulService statefulService2 = testConfig2.statefulService();

        // when
        statefulService1.order("UserA", 10000);
        statefulService2.order("UserB", 20000);

        int price = statefulService1.getPrice();

        // then
        assertThat(price).isNotEqualTo(20000);
    }

    static class TestConfig {
        // 싱글톤
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

    static class TestConfig2 {
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}