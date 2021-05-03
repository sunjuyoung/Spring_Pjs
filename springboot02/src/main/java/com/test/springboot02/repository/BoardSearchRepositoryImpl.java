package com.test.springboot02.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.QBoard;
import com.test.springboot02.entity.QMember;
import com.test.springboot02.entity.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchRepositoryImpl extends QuerydslRepositorySupport implements BoardSearchRepository {

    public BoardSearchRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;
        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(member.nickname.eq(board.writer.nickname));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board,member.nickname,reply.count());
        tuple.groupBy(board,member);

        log.info(tuple);
        log.info("===========================");
       // List<Board> result = jpqlQuery.fetch();
        List<Tuple> result = tuple.fetch();
        log.info(result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(member.nickname.eq(board.writer.nickname));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board,member.nickname,reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);
        if(type !=null){
            String[] typearr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String t : typearr){
                switch (t){
                    case "t":
                        conditionBuilder.or(board.title.containsIgnoreCase(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.containsIgnoreCase(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(board.writer.nickname.containsIgnoreCase(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);
        tuple.groupBy(board,member);
        List<Tuple> result = tuple.fetch();
        log.info(result);

        return null;
    }
}
