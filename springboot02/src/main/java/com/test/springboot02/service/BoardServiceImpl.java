package com.test.springboot02.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.dto.PageRequestDTO;
import com.test.springboot02.dto.PageResultDTO;
import com.test.springboot02.entity.Board;

import com.test.springboot02.entity.Member;
import com.test.springboot02.entity.QBoard;
import com.test.springboot02.repository.BoardRepository;
import com.test.springboot02.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

import static org.springframework.data.jpa.repository.support.QueryHints.from;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;



    @Override
    public Long register(BoardDTO dto,Member member) {
        Board board = modelMapper.map(dto,Board.class);
        board.setWriter(member);
        boardRepository.save(board);
        return board.getBno();
    }

    @Transactional
    @Override
    public Long modify(BoardDTO dto) {
        Board result = boardRepository.findByBno(dto.getBno());
        result.modifyBoard(dto);
        return result.getBno();
    }


    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResultDTO<BoardDTO,Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

       // Page<Board> result= boardRepository.findAll(pageable);
       // Page<Board> result = boardRepository.findAll(booleanBuilder,pageable);
        Page<Object[]> result1 = boardRepository.boardListWithSearchPage(requestDTO.getType(),requestDTO.getKeyword(),pageable);

        Function<Object[],BoardDTO> fn = (en->entityToDTO((Board) en[0],(Member)en[1],(Long)en[2]));

       return new PageResultDTO<>(result1,fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO){
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QBoard qBoard = QBoard.board;
        BooleanExpression expression = qBoard.bno.gt(0);

        booleanBuilder.and(expression);

        //검색 조건이 없는 경우
        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qBoard.title.containsIgnoreCase(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qBoard.content.containsIgnoreCase(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qBoard.writer.nickname.containsIgnoreCase(keyword));
        }
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }


    @Override
    public BoardDTO getBoardByBno(Long bno,Member member) {
        Board board = boardRepository.findByBno(bno);
        BoardDTO dto = entityToDTO(board,member);
        return dto;
    }
}
