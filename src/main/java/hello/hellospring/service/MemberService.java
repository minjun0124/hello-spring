package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
        // 반환 객체가 optional이니까 아래와 같이 줄일 수 있다.
        validateDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
