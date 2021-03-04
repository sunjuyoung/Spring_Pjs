package com.test.studycafe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "persistent_logins")
@Getter
@Setter
public class PersistentLogins {

    @Id
    @Column(length = 64)
    private String series;

    @Column(length = 64 ,nullable = false)
    private String username;

    @Column(length = 64, nullable = false)
    private String token;

    @Column(length = 64,nullable = false,name = "last_used")
    private LocalDateTime lastUsed;
}
