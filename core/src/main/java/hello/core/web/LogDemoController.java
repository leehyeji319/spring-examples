package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

	private final LogDemoService logDemoService;
	private final MyLogger myLogger; //왜 오류? http reqeust가 들어와서 나갈때까지 쓸수잇는데 http request요청이 없는거야

	@RequestMapping("log-demo")
	@ResponseBody //이거 하면 문자를 그대로 내보낼수잇음
	public String logDemo(HttpServletRequest request) throws InterruptedException { //요청이 들어와서 호출하는 상태임
		//MyLogger myLogger = myLoggerProvider.getObject(); //getObject 하는 시점에 만들어짐
		String requestURL = request.getRequestURL().toString();

		System.out.println("myLogger = " + myLogger.getClass()); //myLogger 껍데기만 ㄹㅇ 넣어놓는거야 마치 Provider처럼 동작하는것
		myLogger.setRequestURL(requestURL); //이때 진짜를 찾아서

		myLogger.log("controller test");
		Thread.sleep(1000);
		logDemoService.logic("testID");
		return "OK";
	}
}
