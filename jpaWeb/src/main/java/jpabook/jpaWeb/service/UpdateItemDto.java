package jpabook.jpaWeb.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDto {

        private Long id;


        private String name;

        private int price;
        private int stockQuantity;

        private String author;
        private String isbn;
}
