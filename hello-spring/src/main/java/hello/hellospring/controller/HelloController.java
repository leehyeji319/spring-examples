package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //annotation 적어줘야함
public class HelloController {

    @GetMapping("hello") // /hello라고 들어오면 이 메서드를 매핑해줌
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        return "hello";   //hello.html 을 찾아서 매핑시켜라
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name); //get방식으로 파라미터가 넘어간다.
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //Body부에 이 데이터를 내가 직접 넣어주겟다 라는 뜻.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring" view와의 차이점 이 문자가 그냥 그대로 내려간다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;
        //java bean 표준 규약, property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
