package com.test.springboot02.entity;

import com.test.springboot02.dto.BoardDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer")
public class Board extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nickname")
    private Member writer;


    public void modifyBoard(BoardDTO dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }




}
