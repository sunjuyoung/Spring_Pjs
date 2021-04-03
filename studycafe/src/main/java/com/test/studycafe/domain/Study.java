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

@NamedEntityGraph(name="Study.withTagsAndManagers",attributeNodes = {
        @NamedAttributeNode("tags"),
        @NamedAttributeNode("managers"),})

@NamedEntityGraph(name="Study.withZonesAndManagers",attributeNodes = {
        @NamedAttributeNode("zones"),
        @NamedAttributeNode("managers"),})

@NamedEntityGraph(name="Study.withManagers",attributeNodes = {
        @NamedAttributeNode("managers")})

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

    private int memberCount;


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

    public String defaultImage(){
        return this.image != null ? this.image:"/images/default-banner.jpg";
    }

    public void publish(){
        if(!this.closed && !this.published){
            this.published = true;
            this.publishedDateTime = LocalDateTime.now();
        }else{
            throw new RuntimeException("스터디를 공개할 수 없습니다. 이미 공개했거나 종료했습니다.");
        }
    }


    public void close(){
        if(!this.closed && this.published){
            this.closed = true;
            this.publishedDateTime = LocalDateTime.now();
        }else{
            throw new RuntimeException("스터디를 종료할 수 없습니다. 이미  종료했습니다.");
        }
    }

    public boolean canRecruitStart(){
        return  this.published && this.recruitUpdateDateTime == null || this.recruitUpdateDateTime.isBefore(LocalDateTime.now().minusMinutes(10));
    }

    public void startRecruit(){
        if(!this.closed && this.published){
            this.recruiting = true;
            this.recruitUpdateDateTime = LocalDateTime.now();
        }
    }

    public void stopRecruit(){
        if(!this.closed && this.published){
            this.recruiting = false;
            this.recruitUpdateDateTime = LocalDateTime.now();
        }
    }

    public void closeRecruit(){
        this.recruiting = false;
    }

    public boolean isRemovable() {
        return !this.published;
    }

    public boolean isManagerBy(Account account){
        return this.getManagers().contains(account);
    }

    public void addMember() {
        this.memberCount++;
    }

    public void removeMember(){
        this.memberCount--;
    }
}
