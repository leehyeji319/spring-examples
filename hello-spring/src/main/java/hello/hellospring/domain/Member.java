package hello.hellospring.domain;

public class Member {

    private Long id;
    private String name;

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
