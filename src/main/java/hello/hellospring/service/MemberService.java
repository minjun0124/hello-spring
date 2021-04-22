package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/*
@Service 없으면 Spring에서 이 객체를 알 수 있는 방법이 없다.
SpringConfig.java 에서 @Bean 으로 등록하는 방식을 차용하기 위해 주석
*/
//@Service
@Transactional  // JPA를 사용하려면 service에 transactional이 걸려있어야한다.
public class MemberService {

    private final MemberRepository memberRepository;

    // DI : Dependency Injection
    // ref : MemberServiceTest - beforeEach
    // 항상 같은 memberRepository를 사용하기 위함
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     * */
    public Long join(Member member) {
        /*
        // 같은 이름이 있는 중복 회원X
        Optional<Member> result = memberRepository.findByName(member.getName());
        // Optional로 한번 감싸면 if null 등으로 확인할 수 있는 method가 제공된다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */
        // 반환 객체가 otional이니까 아래와 같이 줄일 수 있다.

        long start = System.currentTimeMillis();

        try {
            validateDuplicatedMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
