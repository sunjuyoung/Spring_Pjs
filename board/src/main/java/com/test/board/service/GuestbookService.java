package com.test.board.service;

import com.test.board.dto.GuestbookDTO;
import com.test.board.dto.PageRequestDTO;
import com.test.board.dto.PageResultDTO;
import com.test.board.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO,Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long gno);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .content(entity.getContent())
                .title(entity.getTitle())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }


}
