package spring.core.basic.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = applicationContext.getBean(NetworkClient.class);
        // 컨테이너 종료시 빈 삭제
        applicationContext.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            // 생성자 호출
//            NetworkClient networkClient = new NetworkClient("http://hello-spring.dev");
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");

            return networkClient;
        }
    }
}
