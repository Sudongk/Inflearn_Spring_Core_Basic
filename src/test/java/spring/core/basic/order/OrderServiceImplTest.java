package spring.core.basic.order;

import org.junit.jupiter.api.Test;
import spring.core.basic.discount.FixDiscountPolicy;
import spring.core.basic.discount.RateDiscountPolicy;
import spring.core.basic.member.Grade;
import spring.core.basic.member.Member;
import spring.core.basic.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}