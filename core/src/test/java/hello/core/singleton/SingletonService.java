package hello.core.singleton;

public class SingletonService {

	//자기 자신을 내부에 private으로 하나 가지고있는데 static으로 가지고있어.
	//이러면 클래스레벨에 올라가기때문에 인스턴스 하나만 존재하게된다.
	private static final SingletonService instance = new SingletonService();

	public static SingletonService getInstance() {
		return instance;
	}

	//private 생성자를 씀 그렇게 해서 생성자를 못만들게
	private SingletonService() {
	}

	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
}
