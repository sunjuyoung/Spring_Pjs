package com.test.springboot02.dto;

import com.test.springboot02.entity.Board;
import com.test.springboot02.service.BoardService;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDTO<DTO,EN> {

    //repository 결과 Page<Entity> 객체 타입을 DTO객체로 변환,화면에 필요한 페이지 정보 구성

    private List<DTO> dtoList;

    private int totalPage;

    private int page;

    private int size;

    private int start,end;

    private boolean prev,next;

    private List<Integer> pageList;


   public PageResultDTO(Page<EN> result , Function<EN,DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        this.totalPage = result.getTotalPages();
        this.page = result.getPageable().getPageNumber() +1;
        this.size = result.getPageable().getPageSize();

        int tempEnd = (int)(Math.ceil(page/10.0)) *10;

        this.start = tempEnd -9;

        this.end = tempEnd > totalPage ? totalPage:tempEnd;

        this.next = tempEnd<totalPage;
        this.prev =  start>1;

        this.pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

    }




}
