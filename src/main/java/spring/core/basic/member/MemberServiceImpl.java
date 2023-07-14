package spring.core.basic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // MemberServiceImpl은 MemberRepository 타입의 구현체가 주입되어야 한다.
    // @Autowired 어노테이션으로 인해 스프링 빈에서 MemberRepository 타입의 빈을 가져와서 자동(Auto)으로 주입한다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤 확인 테스트를 위한 getter 메소드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
