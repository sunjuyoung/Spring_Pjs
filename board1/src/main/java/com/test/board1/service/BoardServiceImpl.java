package com.test.board1.service;

import com.test.board1.dto.BoardDTO;
import com.test.board1.dto.PageRequestDTO;
import com.test.board1.dto.PageResultDTO;
import com.test.board1.entity.Board;
import com.test.board1.entity.Member;
import com.test.board1.repository.BoardRepository;
import com.test.board1.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.function.Function;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService{

    @Autowired
    private  BoardRepository boardRepository;
    @Autowired
    private  ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {

        Board board = dtoToEntity(dto);
        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());

        //Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        Function<Object[],BoardDTO> fn = (entity-> entityToDTO((Board)entity[0],(Member)entity[1],(long)entity[2]));

        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending()));

        result.get().forEach(row->{
            Object[] arr = (Object[])row;
            System.out.println(Arrays.toString(arr));
        });



        return new PageResultDTO<>(result, fn);
    }



    @Override
    public BoardDTO read(Long bno) {

        Object arr =boardRepository.getBoardByBno(bno);

        Object[]result= (Object[])arr;

        return entityToDTO((Board)result[0],(Member) result[1],(long)result[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO dto) {

        Board board = boardRepository.getOne(dto.getBno());//findById 대신 필요한 순간까지 지연로딩방식인 getOne()사용

        board.modifycontentTitle(dto.getTitle(),dto.getContent());

        boardRepository.save(board);


    }


}
