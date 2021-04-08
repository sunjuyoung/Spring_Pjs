package com.test.studycafe.domain;

import com.test.studycafe.security.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(name = "Event.withEnrollments",
attributeNodes = @NamedAttributeNode("enrollments"))

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account createBy;

    @Column(nullable = false)
    private String title;

    @Lob
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime endEnrollDateTime;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    private Integer limitOfEnrollments;

    @OneToMany(mappedBy = "event")
    private List<Enrollment> enrollments;

    @Enumerated(EnumType.STRING)
    private EventType eventType;


    public boolean isEnrollableFor(UserAccount userAccount){
        return this.endEnrollDateTime.isAfter(LocalDateTime.now()) && !isAlreadyEnroll(userAccount);
    }

    public boolean isDisenrollableFor(UserAccount userAccount){
        return isAlreadyEnroll(userAccount) && this.endEnrollDateTime.isAfter(LocalDateTime.now());
    }

    public boolean isAttended(UserAccount userAccount){
        Account account = userAccount.getAccount();
        for(Enrollment e: this.enrollments){
            if(e.getAccount().equals(account)){
                if(e.isAttended()){
                    return true;
                }
        }
    }
        return false;
    }

    public boolean isAlreadyEnroll(UserAccount userAccount){
        Account account = userAccount.getAccount();
        for(Enrollment e: this.enrollments){
            if(e.getAccount().equals(account)){
                return true;
            }
        }
        return false;
    }

    public boolean canAccept(Enrollment enrollment){
        return enrollment.isAttended();
    }
    public boolean canReject(Enrollment enrollment){
        return !enrollment.isAttended();
    }

    public int numberOfRemainSpots(){
        if(this.enrollments.size()>this.limitOfEnrollments){
            return 0;
        }else{
            return this.limitOfEnrollments - this.enrollments.size();
        }
    }
}
