package com.test.studycafe.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    private LocalDateTime  confirmEmailAt; //인증 메일 재전송 방지

    private String bio;

    private String url;

    private String occupation;

    private String location;

    @ManyToMany
    private Set<Tag> tags;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    /* 알림 */
    private boolean studyCreatedByEmail; //스터디 개설
    private boolean studyCreatedByWeb = true;

    private boolean studyEnrollmentResultByEmail; //스터디 가입신청결과
    private boolean studyEnrollmentResultByWeb  = true;

    private boolean studyUpdatedByWeb  = true;
    private boolean studyUpdatedByEmail;


    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.confirmEmailAt = LocalDateTime.now();
    }

    public void completeSignUp() {
        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }

    public boolean canSendConfirmEmail(){
        return this.confirmEmailAt.isBefore(LocalDateTime.now().minusMinutes(2));
    }

}

