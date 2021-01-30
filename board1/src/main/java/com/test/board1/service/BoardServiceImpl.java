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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {

        Board board = dtoToEntity(dto);
        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        Function<Object[],BoardDTO> fn = (entity-> entityToDTO((Board) entity[0],(Member)entity[1],(Long)entity[2]));

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public BoardDTO read(Long bno) {

        Object arr =boardRepository.getBoardByBno(bno);

        Object[]result= (Object[])arr;

        return entityToDTO((Board)result[0],(Member) result[1],(Long)result[2]);
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
