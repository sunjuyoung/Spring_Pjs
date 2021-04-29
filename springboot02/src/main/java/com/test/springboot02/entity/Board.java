package com.test.springboot02.entity;

import com.test.springboot02.dto.BoardDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length = 50,nullable = false)
    private String writer;

    public void modifyBoard(BoardDTO dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
