package spring.core.basic.discount;

import org.springframework.stereotype.Component;
import spring.core.basic.member.Grade;
import spring.core.basic.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }

        return 0;
    }
}
