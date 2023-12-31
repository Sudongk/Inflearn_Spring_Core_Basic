package spring.core.basic.discount;

import org.springframework.stereotype.Component;
import spring.core.basic.annotation.MainDiscountPolicy;
import spring.core.basic.member.Grade;
import spring.core.basic.member.Member;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }

        return 0;
    }
}
