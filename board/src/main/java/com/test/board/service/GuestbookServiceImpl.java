package com.test.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.test.board.dto.GuestbookDTO;
import com.test.board.dto.PageRequestDTO;
import com.test.board.dto.PageResultDTO;
import com.test.board.entity.Guestbook;
import com.test.board.entity.QGuestbook;
import com.test.board.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

        BooleanBuilder booleanBuilder = getSearch(requestDTO);//검색 조건

        Page<Guestbook> result = guestbookRepository.findAll(booleanBuilder,pageable);


        Function<Guestbook,GuestbookDTO> fn = (entity-> entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = guestbookRepository.findById(gno);
        //간접적으로 null 다루기
        return result.isPresent()? entityToDto(result.get()):null;
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = guestbookRepository.findById(dto.getGno());
        if(result.isPresent()){
            Guestbook entity = result.get();
            entity.chagneContent(dto.getContent());
            entity.changeTitle(dto.getTitle());

            guestbookRepository.save(entity);

        }
    }

    @Override
    public void remove(Long gno) {
        guestbookRepository.deleteById(gno);
    }


    //검색 조건 querdsl 처리
    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();

        BooleanBuilder builder = new BooleanBuilder();

        QGuestbook qGuestbook = QGuestbook.guestbook;

        BooleanExpression expression = qGuestbook.gno.gt(0L); // gno > 0

        builder.and(expression);

        if(type==null || type.trim().length() == 0){
            return builder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        builder.and(conditionBuilder);

        return builder;



    }

}
