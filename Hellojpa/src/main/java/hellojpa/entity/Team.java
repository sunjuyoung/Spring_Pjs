package hellojpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    private Long id;
    private String name;

    //연관관계 주인이 아니므로 mappedBy , 조회만 가능
    // 외래키가있는 곳을 주인으로 정해라 ,
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();









    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

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
