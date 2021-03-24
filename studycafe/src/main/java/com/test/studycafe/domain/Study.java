package com.test.studycafe.domain;

import com.test.studycafe.security.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@NamedEntityGraph(name = "Study.withAll", attributeNodes = {
        @NamedAttributeNode("tags"),
        @NamedAttributeNode("zones"),
        @NamedAttributeNode("managers"),
        @NamedAttributeNode("members")})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Study {

    @Id @GeneratedValue
    private Long id;

    @Builder.Default
    @ManyToMany
    private Set<Account> managers = new HashSet<>(); //권한 이임 및

    @Builder.Default
    @ManyToMany
    private Set<Account> members = new HashSet<>();

    @Column(unique = true)
    private String path;

    private String title;

    private String shortDescription;

    @Lob @Basic //default EAGER
    private String fullDescription;


    @Lob @Basic
    private String image;

    @Builder.Default
    @ManyToMany
    private Set<Tag>tags = new HashSet<>();

    @Builder.Default
    @ManyToMany
    private Set<Zone> zones = new HashSet<>();

    private LocalDateTime publishedDateTime;

    private LocalDateTime closedDateTime;

    private LocalDateTime recruitUpdateDateTime;

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    private boolean useBanner;


    public void addManager(Account account) {
        this.managers.add(account);
    }

    public boolean isJoinable(UserAccount userAccount){
        Account account = userAccount.getAccount();
        return this.isPublished() && this.isRecruiting()
                && !this.members.contains(account) && !this.managers.contains(account);

    }
    public boolean isMember(UserAccount userAccount){
        return this.members.contains(userAccount.getAccount());

    }

    public boolean isManager(UserAccount userAccount){
        return this.managers.contains(userAccount.getAccount());
    }

    public void banner(){
        this.useBanner = !this.useBanner;
    }

    public String getImage(){
        return this.image != null ? this.image:"/images/default-banner.jpg";
    }
}
