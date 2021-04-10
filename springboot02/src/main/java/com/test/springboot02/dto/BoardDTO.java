package com.test.springboot02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
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


    private LocalDateTime regDate,modDate;
}
