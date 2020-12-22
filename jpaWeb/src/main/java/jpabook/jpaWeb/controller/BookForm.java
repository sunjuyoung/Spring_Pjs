package jpabook.jpaWeb.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    private Long id;

    @NotEmpty(message = "제목을 입력해 주세요")
    private String name;

    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
