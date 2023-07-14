package spring.core.basic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // @Configuration 어노테이션으로 등록된 클래스도 빈으로 자동이 등록되고 설정 정보도 자동 등록된다.
        // 보통은 Configuration을 스캔 대상에서 제외하지는 않는다.
        // 본 실습에서는 이 컴포넌트 스캔 객체가 잘 작동하는지 시험하기 위해 AppConfig.java 는 스캔 대상에서 제외한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes  = Configuration.class),
        basePackages = "spring.core.basic"
)
public class AutoAppConfig {

}
