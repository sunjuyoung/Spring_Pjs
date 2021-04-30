package com.test.springboot02.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String text;

    private String replyer;
}
