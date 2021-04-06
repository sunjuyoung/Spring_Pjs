package com.test.springboot02.dto;

import com.test.springboot02.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO,EN> {

    //repository 결과 Page<Entity> 객체 타입을 DTO객체로 변환,화면에 필요한 페이지 정보 구성

    private List<DTO> dtoList;


    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }




}
