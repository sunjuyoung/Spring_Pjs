package com.test.board.service;

import com.test.board.dto.GuestbookDTO;
import com.test.board.dto.PageRequestDTO;
import com.test.board.dto.PageResultDTO;
import com.test.board.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookServiceTest {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sample title...")
                .content("sample content")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();


        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        for(GuestbookDTO dto : resultDTO.getDtoList()){
            System.out.println(dto);
        }

        System.out.println("prev : "+ resultDTO.isPrev());
        System.out.println("next : "+ resultDTO.isNext());
        System.out.println("total : "+ resultDTO.getTotalPage());

        System.out.println("========================");
        resultDTO.getPageList().forEach(i-> System.out.println(i));
    }

}