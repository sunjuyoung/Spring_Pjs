package com.test.board.service;

import com.test.board.dto.GuestbookDTO;
import com.test.board.dto.PageRequestDTO;
import com.test.board.dto.PageResultDTO;
import com.test.board.entity.Guestbook;
import com.test.board.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);
        guestbookRepository.save(entity);
        log.info(entity);

        return entity.getGno();
    }


    //Page<Entity>의 엔티티 객체들를 DTO 객체로 변환
    public PageResultDTO<GuestbookDTO,Guestbook> getList(PageRequestDTO requestDTO){

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = guestbookRepository.findAll(pageable);


        Function<Guestbook,GuestbookDTO> fn = (entity-> entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }


}
