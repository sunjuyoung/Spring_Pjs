package com.test.springboot02.event;

import com.test.springboot02.entity.Board;
import lombok.Getter;

@Getter
public class BoardCreateEvent {

    private Board board;

     public BoardCreateEvent(Board board){
        this.board= board;
    }

}
