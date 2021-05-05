package com.test.springboot02.dto;

import lombok.*;


import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long bno;

    @NotBlank
    private String title;

    @Lob @NotBlank
    private String content;

    @NotBlank
    private String writer;

    private int replyCount;


    private LocalDateTime regDate,modDate;
}
