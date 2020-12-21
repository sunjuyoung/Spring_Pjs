package jpabook.jpaWeb.service;

import jpabook.jpaWeb.domain.Address;
import jpabook.jpaWeb.domain.Member;
import jpabook.jpaWeb.domain.Order;
import jpabook.jpaWeb.domain.OrderStatus;
import jpabook.jpaWeb.domain.item.Book;
import jpabook.jpaWeb.domain.item.Item;
import jpabook.jpaWeb.exception.NotEnoughStockException;
import jpabook.jpaWeb.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문()throws Exception{
        //given
        Member member = createMemter();     //ctrl + alt + M

        Item book = createBook();
        int orderCcunt = 2;
        //when
        Long orderId = orderService.order(member.getId(),book.getId(),orderCcunt);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,getOrder.getStaus());
        assertEquals("주문한 상품 종류 수가 정확해야한다",1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격* 수량",2000,getOrder.getTotalPrice());
        assertEquals("주문 숫량 만큼 재고가 줄어야한다",8,book.getStockQuantity());

    }


    @Test
    public void 주문취소()throws Exception{
        //given
        Member member = createMemter();     //ctrl + alt + M

        Item book = createBook();
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(),book.getId(),orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문취소시 주문상태",OrderStatus.CANCEL,getOrder.getStaus());
        assertEquals("재고확인",10,book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMemter();     //ctrl + alt + M

        Item book = createBook();
        int orderCount = 11;

        //when
        orderService.order(member.getId(),book.getId(),orderCount);

        //then
        fail("예외발생해야한다.");

    }




    private Item createBook() {
        Item book = new Book();
        book.setName("JPA");
        book.setPrice(1000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member createMemter() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","논현","123"));
        em.persist(member);
        return member;
    }
}