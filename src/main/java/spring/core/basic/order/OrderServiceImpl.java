package spring.core.basic.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.core.basic.annotation.MainDiscountPolicy;
import spring.core.basic.discount.DiscountPolicy;
import spring.core.basic.discount.FixDiscountPolicy;
import spring.core.basic.discount.RateDiscountPolicy;
import spring.core.basic.member.Member;
import spring.core.basic.member.MemberRepository;
import spring.core.basic.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 확인 테스트를 위한 getter
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    // 싱글톤 확인 테스트를 위한 getter
    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}
