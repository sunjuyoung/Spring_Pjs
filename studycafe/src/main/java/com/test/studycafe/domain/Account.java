package com.test.studycafe.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String bio;

    private String url;

    private String occupation;

    private String location;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    /* 알림 */
    private boolean studyCreatedByEmail; //스터디 개설
    private boolean studyCreatedByWeb;

    private boolean studyEnrollmentResultByEmail; //스터디 가입신청결과
    private boolean studyEnrollmentResultByWeb;

    private boolean studyUpdatedByWeb;
    private boolean studyUpdatedByEmail;


    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }
}

