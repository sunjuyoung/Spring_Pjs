package jpabook.jpatest.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")//order 테이블에있는 member 필드
    private List<Order> orders = new ArrayList<>();
    //jpa hibernate에서 컬렉션은 필드에서 바로 초기화 하는 것이 (null문제)안전하다.




}
