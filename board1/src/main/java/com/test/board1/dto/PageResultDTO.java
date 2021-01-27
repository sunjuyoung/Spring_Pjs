package com.test.board1.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<EN,DTO> {


    private List<DTO> dtoList;

    private int page;
    private int size;

    private int totalPage;

    private int start,end;


    private boolean prev,next;

    public PageResultDTO(Page<EN> result, Function<EN,DTO>fn){

        this.dtoList = result.stream().map(fn).collect(Collectors.toList());

        this.totalPage = result.getTotalPages();

        this.page = result.getPageable().getPageNumber()+1;
        this.size = result.getPageable().getPageSize();

        int tempEnd = (int)(Math.ceil(page/10.0))*10;

        this.end = totalPage > tempEnd ? tempEnd:totalPage;

        this.start = tempEnd - 9;

        this.prev = start >1;

        this.next =  totalPage>tempEnd;

    }





}
