package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) { //model 이란? 얘에 어떤 데이터를 실어서 뷰에 넘길수잇음
		model.addAttribute("data", "hello!!!");
		return "hello"; //화면 이름
	}
}
