package com.test.springboot02.event;

import com.test.springboot02.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Async
@Component
@Transactional(readOnly = true)
public class BoardEventListener {

    @EventListener
    public void handleBoardCreateEvent(BoardCreateEvent boardCreateEvent){
        Board board = boardCreateEvent.getBoard();
        log.info("is created");
        log.info("is created");
        log.info("is created "+board.getBno());
    }
}
