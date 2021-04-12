package com.test.studycafe.domain;

import com.test.studycafe.security.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return !enrollment.isAttended()
                && this.eventType == EventType.CONFIRM
                && !enrollment.isAccepted();
    }
    public boolean canReject(Enrollment enrollment){
        return enrollment.isAttended()
                && enrollment.isAccepted()
                && this.eventType == EventType.CONFIRM;
    }

    public int numberOfRemainSpots(){
        if(this.enrollments.size()>(int)this.limitOfEnrollments){
            return 0;
        }else{
            return (int)(this.limitOfEnrollments - this.enrollments.size());
        }
    }

    //선착순일 때 모집인원과 신청인원 비교하여 가입가능여부 확인
    public boolean isAbleToAcceptEnrollment() {
        return this.eventType == EventType.FCFS &&
                this.limitOfEnrollments > this.enrollments.stream().filter(Enrollment::isAccepted).count();
    }

    //관리자 확인 모임일 때 신청인원 비교하여 가입가능여부 확인
    public boolean isAcceptConfirmEnrollment(){
        return this.limitOfEnrollments > this.enrollments.stream().filter(Enrollment::isAccepted).count() &&
                this.eventType == EventType.CONFIRM;
    }

    //모입 인원 중 가입취소자 발생 시 첫 번째 대기중인(존재 시) 인원 가입신청
    public void acceptFirstWaitingEnroll(){
        if(this.isAbleToAcceptEnrollment()){
            Enrollment enrollment = gietFirstWaitingEnroll();
            if(enrollment != null){
                enrollment.setAccepted(true);
            }

        }
    }

    //모입 인원 중 가입취소자 발생 시 첫 번째 대기중인(존재시) 인원 반환
    public Enrollment gietFirstWaitingEnroll(){
        for(Enrollment e: this.enrollments){
            if(!e.isAccepted()){
                return e;
            }
        }
        return null;
    }

    //모집인원 변경시 늘어난 인원수만큼 가입신청
    public void acceptWaitingList() {
        if(isAbleToAcceptEnrollment()){
            int num = (int)this.limitOfEnrollments - (int)this.enrollments.stream().filter(Enrollment::isAccepted).count();
            List<Enrollment> enrollments = this.enrollments.stream().filter(i->i.isAccepted()==false).collect(Collectors.toList());
            System.out.println("==============="+num);
            /*            for(int i=0; i<num; i++){
                enrollments.get(i).setAccepted(true);
            }*/
            if(enrollments.size()>0)enrollments.subList(0,num).forEach(i->i.setAccepted(true));
        }
    }


}
