package com.test.springboot02.event;

import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.Member;
import com.test.springboot02.entity.Notification;
import com.test.springboot02.entity.NotificationType;
import com.test.springboot02.repository.BoardRepository;
import com.test.springboot02.repository.MemberRepository;
import com.test.springboot02.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Async
@Component
@Transactional
@RequiredArgsConstructor
public class BoardEventListener {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final NotificationRepository notificationRepository;


    @EventListener
    public void handleBoardCreateEvent(BoardCreateEvent boardCreateEvent){
        Board board = boardRepository.findBoardWithWriterByBno(boardCreateEvent.getBoard().getBno());
        Member member = memberRepository.findByNickname(board.getWriter().getNickname());

        Notification notification = Notification.builder()
                .checked(false)
                .createDateTime(LocalDateTime.now())
                .member(member)
                .notificationType(NotificationType.BOARD_REPLY)
                .number(board.getBno())
                .build();
        notificationRepository.save(notification);

        log.info("is created");
        log.info("is created");
        log.info("is created "+board.getBno());
    }
}
