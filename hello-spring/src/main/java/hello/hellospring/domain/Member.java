package hello.hellospring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 알아서 생성해준다.
    private Long id; //시스템에서 지정

    //@Column(name = "username") 컬럼이름을 써놓으면 거기로 됨
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
