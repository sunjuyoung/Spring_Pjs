package com.test.springboot02.service;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.dto.PageRequestDTO;
import com.test.springboot02.dto.PageResultDTO;
import com.test.springboot02.entity.Board;
import com.test.springboot02.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;


    @Override
    public Long register(BoardDTO dto) {

        Board board = modelMapper.map(dto,Board.class);
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO,Board> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        Page<Board> result= boardRepository.findAll(pageable);

        Function<Board,BoardDTO> fn = (en->entityToDTO(en));

       return new PageResultDTO<>(result,fn);

    }

    @Override
    public BoardDTO getBoardByBno(Long bno) {
        Board board = boardRepository.findByBno(bno);
        BoardDTO dto = entityToDTO(board);
        return dto;
    }
}
