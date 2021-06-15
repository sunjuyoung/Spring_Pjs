package com.test.book.springboot.domain.posts;


import com.test.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity //테이블과 링크될 클래스
public class Posts extends BaseTimeEntity {

    @Id //PK,
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK, auto_increment
    private Long id;

    @Column(columnDefinition = "TEXT" ,length = 50, nullable = false)
    private String content;


    private String author;

    @Column(length = 500,nullable = false)
    private String title;


    @Builder
    public Posts(String title, String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
