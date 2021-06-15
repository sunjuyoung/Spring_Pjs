package com.test.book.springboot.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_test(){


        String name = "test";
        int amount = 10000;


        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //검증 메서드
        //assertThat에있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);




    }
}
