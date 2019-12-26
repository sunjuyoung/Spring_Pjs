package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.domain.BoardVO;
import com.test.domain.PageDTO;
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


	//검색
	@Override
	public BoardVO get(int bno) {
		return mapper.get(bno);
	}
	
	
	//입력
	@Transactional
	@Override
	public void insert(BoardVO board) {
		log.info("register service");
		mapper.insert(board);
		mapper.fileInsert(board);
		
	}
	
	
	//삭제
	@Override
	public boolean remove(int bno) {
		
		boolean result = false;
		if(mapper.delete(bno) >0) {
			result =true;
		}
		
		return result;
	}

	//수정
	@Override
	public int modify(BoardVO board) {
		return mapper.modify(board);
	}

	//페이징 리스트
	@Override
	public List<BoardVO> getListWithPaging(PageDTO dto) {
		
		
		return mapper.getListWithPaging(dto);
	}
	
	
	
	public int total() {
		
		return mapper.total();
	}


	@Override
	public void fileInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void fileDelete(String uuid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<BoardVO> findByBno(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	




	


	
	*/

}
