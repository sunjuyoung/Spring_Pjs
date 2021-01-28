package com.test.board1.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO,EN> {


    private List<DTO> dtoList;

    private int page;
    private int size;

    private int totalPage;

    private int start,end;

    private boolean prev,next;

    //페이지 번호 목록
    private List<Integer> pageList;

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


        //boxed() 메서드는 int, long, double 요소를 Integer, Long, Double 요소로 박싱해서 Stream을 생성한다
        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }





}
