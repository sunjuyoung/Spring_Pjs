package com.test.board.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//다양한 곳에서 사용할수있는 페이지 결과 처리 DTO
@Data
public class PageResultDTO<DTO,EN> {

    //DTO 리스트
    private List<DTO> dtoList;

    private int totalPage;

    //현재 페이지
    private int page; 

    private int size;

    //시작 페이지 번호, 끝 페이지 번호
    private int start,end;

    private boolean prev,next;

    //페이지 번호 목록
    private List<Integer> pageList;


    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){

        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());

    }

    private void makePageList(Pageable pageable){
        
        this.page = pageable.getPageNumber() +1; // 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();
        
        int tempEnd = (int)(Math.ceil(page/10.0))*10;
        
        start  = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd : totalPage;

        next = totalPage > tempEnd;

        //boxed() 메서드는 int, long, double 요소를 Integer, Long, Double 요소로 박싱해서 Stream을 생성한다
        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());


    }
}
