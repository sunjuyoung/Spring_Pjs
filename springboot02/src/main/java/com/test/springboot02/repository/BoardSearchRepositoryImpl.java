package com.test.springboot02.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.QBoard;
import com.test.springboot02.entity.QMember;
import com.test.springboot02.entity.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

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


        QReply reply = QReply.reply;
        QMember member = QMember.member;
        QBoard board = QBoard.board;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.nickname, reply.count().as("hi"));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
/*        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);*/

        if(type != null){
            String[] typeArr = type.split("");
            //검색 조건을 작성하기
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t:typeArr) {
                switch (t){
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.nickname.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        //tuple.orderBy(board.bno.desc());

        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC: Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));

        });
        tuple.groupBy(board , member).fetchCount();

        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());


        List<Tuple> result = tuple.fetch();


        log.info("==================");
        log.info(result);
        log.info(result.size());
        Long count = tuple.fetchCount();




        return null;//new PageImpl<Object[]>(result.stream().map(t->t.toArray()).collect(Collectors.toList()),pageable,result.size());
    }

    @Override
    public Page<Object[]> searchPage1(String type, String keyword, Pageable pageable) {


        QReply reply = QReply.reply;
        QMember member = QMember.member;
        QBoard board = QBoard.board;

        JPQLQuery<Board> jpqlQuery = from(board)
        .leftJoin(member).on(board.writer.eq(member))
        .leftJoin(reply).on(reply.board.eq(board)).fetchJoin()
                .groupBy(board).groupBy(member);
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board,reply.count());
        List<Tuple> result = tuple.fetch();
        log.info(result);
        JPQLQuery<Board> jpqlQuery1 = from(board);
        log.info(jpqlQuery1.fetchCount());


       // JPQLQuery<Board> pageableQuery =  getQuerydsl().applyPagination(pageable,jpqlQuery);
       // QueryResults<Board> fetchResult =  pageableQuery.fetchResults();
       // log.info(fetchResult.getTotal());

/*

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.nickname, reply.count().as("hi"));

        tuple.orderBy(board.bno.desc());


        tuple.groupBy(board , member).fetchCount();

        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());
*/





        return null;//new PageImpl<Object[]>(result.stream().map(t->t.toArray()).collect(Collectors.toList()),pageable,result.size());
    }

}
