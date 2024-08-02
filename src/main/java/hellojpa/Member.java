package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id; // jpa 관련 어노테이션은 대부분 javax.persistence 에서 import


// jpa가 처음 로딩될때 @Entity를 붙인 애를 jpa가 사용하는 애구나 하구 인식
@Entity
public class Member {

    @Id
    private Long id;
    private String name;

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
