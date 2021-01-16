package com.test.board.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//다양한 곳에서 사용할수있는 페이지 결과 처리 DTO
@Data
public class PageResultDTO<DTO,EN> {

    private List<DTO> dtoList;

    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){

        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
