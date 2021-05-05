package com.test.springboot02.dto;

import com.test.springboot02.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long id;

    private String text;

    private String replyer;

    private Long bno;

    private LocalDateTime regDate,modDate;

}
