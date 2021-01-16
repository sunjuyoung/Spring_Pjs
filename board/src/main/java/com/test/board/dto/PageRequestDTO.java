package com.test.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDTO {

    private int page;
    private int size;

    public PageRequestDTO(){
        this.page =1;
        this.size = 10;
    }

    //Pageable 객체 생성
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1,size,sort);
    }
}
