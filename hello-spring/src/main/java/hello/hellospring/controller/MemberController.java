package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController { //membercontroller 객체를 생성해서 넣어다.

    //new 해서 하면 다른 컨트롤러들이 멤버 서비스를 가져다 쓸수 잇겟지. 그냥 하나 생성해서 공용으로 쓰면 되거든...
    // 그러니까 그냥 spring container에 등록하고 사용하면돼
    private final MemberService memberService;

    @Autowired //연결 시켜줘야지? 연결 시켜줄때 autowired 쓰면 된다. //이게 컴포턴트 스캔 방식임
    //스프링 빈에 등록되어있는 멤버 서비스를 갖다가 넣어줌. 이게 바로 d.i 임
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
