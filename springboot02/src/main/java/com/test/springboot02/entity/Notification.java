package com.test.springboot02.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    private boolean checked;

    private LocalDateTime createDateTime;

    @ManyToOne
    private Member member;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
