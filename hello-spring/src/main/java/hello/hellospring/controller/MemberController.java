package hello.hellospring.controller;

import java.util.List;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //이 어노테이션을 붙이면 memberController 객체를 생성해서 넣어둔다( == spring bean)
public class MemberController { //membercontroller 객체를 생성해서 넣었다.

    //new 해서 하면 다른 컨트롤러들이 멤버 서비스를 가져다 쓸수 잇겟지. 그냥 하나 생성해서 공용으로 쓰면 되거든...
    // 그러니까 그냥 spring container에 등록하고 사용하면돼
    //private final MemberService memberService = new MemberService() 이렇게 new 로 생성을 하면 문제가 있어 ..
    //여러군데에서 memberService가 쓰일텐데(ex, 주문 등등) 객체를 new하면 .. memberService에는 어차피 별기능이 없으니까 생성할 필요가없어
    //한개를 공용으로 쓰면 되니까 spring bean에 등록하고 쓰면 된다.
    private final MemberService memberService; //필드 주입 방법은 별로 쓰이지 않는다


    //생성자에 @Autowired가 붙으면 멤버서비스를 스프링이 스프링 컨테이너에 있는 멤버서비스를 가져다가 연결시켜준다.
    //@Autowired //연결 시켜줘야지? 연결 시켜줄때 autowired 쓰면 된다. //이게 컴포턴트 스캔 방식임
    //스프링 빈에 등록되어있는 멤버 서비스를 갖다가 넣어줌. 이게 바로 d.i 임
    public MemberController(MemberService memberService) { //이 방법을 제일 많이쓴다(생성자 주입)
        this.memberService = memberService;
    }// 생성자를 통해서 멤버 서비스가 멤버 컨트롤러에 주입이 되죠 ? 이게 생성자 통해서 들어오는 방법

    @GetMapping("/members/new") //주로 조회할때 get
    public String createForm() {
        return "members/createMemberForm"; //createMemberForm이란데로 리턴 그럼 template에서 createMemberForm을 찾음
    }

    @PostMapping("/members/new") //데이터를 전달할때 주로 post //url은 똑같지만 post냐 get이냐에 따라 넘어옴
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //홈화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //멤버 리스트 자체를 모델에 담아서 화면에 넘길거임
        return "members/memberList";
    }
}
