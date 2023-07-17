package spring.core.basic.autowired;

import jakarta.annotation.Nullable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.basic.member.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);
        TestBean testBean = applicationContext.getBean(TestBean.class);

        assertThat(testBean.getNoBean1()).isNull();
        assertThat(testBean.getNoBean2()).isNull();
        assertThat(testBean.getNoBean3()).isEmpty();
    }

    static class TestBean {
        private Member noBean1;
        private Member noBean2;
        private Optional<Member> noBean3;
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
            this.noBean1 = noBean1;
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
            this.noBean2 = noBean2;
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
            this.noBean3 = noBean3;
        }

        public Member getNoBean1() {
            return this.noBean1;
        }

        public Member getNoBean2() {
            return this.noBean2;
        }

        public Optional<Member> getNoBean3() {
            return this.noBean3;
        }
    }
}
