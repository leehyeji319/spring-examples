package hello.hellospring.domain;

public class Member {

    private Long id; //시스템에서 지정
    private String name; // 회원이 지정하는 이름

    //단순하게 그냥 만들게
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
