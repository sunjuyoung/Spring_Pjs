package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

//BoardService 인터페이스의 구현체
@Service //계층 구조상 비즈니스 영역을  담당하는 객체임을 표시
@Log4j
public class BoardServiceImpl implements BoardService {

	
	@Autowired //4.3이후로는 생략해도 자동처리 된다  @setter(onMethod_ = @Autowired)
	private BoardMapper mapper;//4.3이후로는 단일 파라미터를 받는 생성자의 경우에는 필요한 파라미터를 자동으로 주입
	
	
	//리스트
	@Override
	public List<BoardVO> getList() {
		
		return mapper.getList();
	}

	//페이징 리스트
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		
		
		return mapper.getListWithPaging(cri);
	}
	
	//입력
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
		
	}

	//검색
	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	//삭제
	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) ==1;
	}

	//수정
	@Override
	public int modify(BoardVO board) {
		return mapper.modify(board);
	}


	
	public int total(Criteria cri) {
		
		return mapper.total(cri);
	}

}
