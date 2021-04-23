package com.test.springboot02.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

}
