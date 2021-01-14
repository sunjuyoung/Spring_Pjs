package com.test.board.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@MappedSuperclass //테이블로 생성되지 않는다.
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate",updatable = false)//해당 엔티티 객체를 데이터베이스에서 반영할때 변경되지 않는다
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
