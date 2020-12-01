package hellojpa.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;

/**
 *
 */
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String name;
    private int age;

   // @Column(name = "TEAM_ID")
   // private Long teamId;

    //연관관계 주인
    @ManyToOne //(fetch = FetchType.LAZY) Member조회할때는 Member만 안하면 join으로 Team 도 조회
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING) /*STRING 권장*/
    private MemberType memberType;


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                ", memberType=" + memberType +
                '}';
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }



    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
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
