package spring.core.basic.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    // @MainDiscountPolicy -> 의존성 주입 시 어떤 빈을 주입 시킬지 명시적으로 나타냄
    // @MainDiscountPolicy 가 적용된 대상은 -> @Qualifier("mainDiscountPolicy")
    // 코드 가독성, 오타나 잘못된 문자열의 사용을 방지, 유지보수성 증가
}
