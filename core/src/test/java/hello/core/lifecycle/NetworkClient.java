package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

	private String url;

	public NetworkClient() {
		System.out.println("생성자 호출, url = " +url);

	}

	public void setUrl(String url) { //url 외부에서 넣어줄 수 잇음
		this.url = url;
	}

	//서비스 시작시 호출
	public void connect() {
		System.out.println("connect: " + url);
	}

	public void call(String message) {
		System.out.println("call: " + url + " message = " + message);
	}

	//서비스 종료시 호출
	public void disconnect() {
		System.out.println("close: " + url);
	}

	@PostConstruct
	public void init() {
		//여기에 해주면 스프링이 의존관계 끝나고 그때 호출해줌
		System.out.println("NetworkClient.init");
		connect();
		call("초기화 연결 메시지");
	}

	@PreDestroy
	public void close() {
		//얘가 들어오면 디스커넥트 호출해줌
		System.out.println("NetworkClient.close");
		disconnect();
	}
}
