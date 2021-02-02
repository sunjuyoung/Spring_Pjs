package com.test.board1.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.test.board1.entity.Board;
import com.test.board1.entity.QBoard;
import com.test.board1.entity.QMember;
import com.test.board1.entity.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class); //도메인클래스 지정, null값을 넣을수 없다
    }



    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board,member,reply.count());

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        builder.and(expression);

        if(type != null){
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();
            //검색 조건 처리
            for(String t:typeArr){
                switch (t){
                    case "t": conditionBuilder.or(board.title.contains(keyword));
                    case "c": conditionBuilder.or(board.content.contains(keyword));
                    case "w": conditionBuilder.or(member.email.contains(keyword));
                        break;
                }

            }
            builder.and(conditionBuilder);

        }

        tuple.where(builder);
        //order by
        // Sort sort = pageable.getSort();
        tuple.orderBy(board.bno.desc());

        tuple.groupBy(board);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();
        log.info("COUNT: " +count);
        log.info(result);

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }

  /*  @Override
    public Board search1() {
        log.info("search1------------------------");

        QBoard board = QBoard.board;

        JPQLQuery<Board> jpqlQuery = from(board);

        jpqlQuery.select(board).where(board.bno.eq(202L));

        List<Board> result = jpqlQuery.fetch();

        return null;
    }

    //join
    @Override
    public Board search2() {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(member.eq(board.writer));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        jpqlQuery.select(board,member.email,reply.count()).groupBy(board);

        List<Board> result = jpqlQuery.fetch();
        return null;
    }

    //각각의 데이터를 추출하는경우 Tuple이라는 객체를 이용
    @Override
    public Board search3() {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(member.eq(board.writer));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuples = jpqlQuery.select(board,member.email,reply.count());

        tuples.groupBy(board);
        log.info("===================================");
        log.info(tuples);
        log.info("===================================");
        List<Tuple> result = tuples.fetch();

        log.info(result);

        return null;
    }
*/


}

