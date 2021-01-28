package com.test.board1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne (fetch = FetchType.LAZY)
    private Member writer;


    public void modifycontentTitle(String title,String content){
        this.title = title;
        this.content = content;
    }

}
