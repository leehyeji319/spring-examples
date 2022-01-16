package hello.core.order;

public interface OrderService { //최종 오더 결과를 반환하는 인터페이스(역할)
	Order createOrder(Long memberId, String itemName, int itemPrice);
}
