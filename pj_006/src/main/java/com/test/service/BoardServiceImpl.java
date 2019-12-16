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
		
		return mapper.boardList();
	}
	
	
	//페이징리스트
	@Override
	public List<BoardVO> pagingList(Criteria cri) {
		
		return mapper.pagingList(cri);
	}
	
	

	//삭제
	@Override
	public int remove(Long bno) {
		
		return mapper.delete(bno);
	}

	//조회
	@Override
	public BoardVO read(Long bno) {
		
		return mapper.read(bno);
	}

	//추가
	@Override
	public int insert(BoardVO vo) {
		
			
		return mapper.insertSelectKey(vo);
	}

	
	//수정
	@Override
	public int update(BoardVO vo) {
		return mapper.update(vo);
	}


	//total
	@Override
	public int total() {
		// TODO Auto-generated method stub
		return mapper.total();
	}

	
	
	



}
