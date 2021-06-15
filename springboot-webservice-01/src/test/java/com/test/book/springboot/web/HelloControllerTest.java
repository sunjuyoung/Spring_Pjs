package com.test.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    //웹 API테스트할 떄 사용
    //HTTP get,post등 테스트 가능
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello() throws Exception{
        String hello = "hello";


        mvc.perform(get("/hello"))
                .andExpect(status().isOk())  //HTTP header status 검증 200,404,500등. OK = 200인지 검증한다
                .andExpect(content().string(hello));

    }



    @Test
    public void lombok() throws Exception{
        String name="wef";
        int amount = 23;


        mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))  //json응답값을 필드별로 검증,$를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount").value(amount));

    }
}
