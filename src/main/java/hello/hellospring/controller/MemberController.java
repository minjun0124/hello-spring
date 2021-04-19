package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Spring Load 되면서 객체 생성되고 Bean에 등록되어 spring에서 관리
public class MemberController {

    //    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    /**
     * Autowired : Service bean과 controller bean을 연결시켜준다.
     * DI : MemberService를 가져다가 주입해준다
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
