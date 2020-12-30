package jpabook.jpaWeb;

import jpabook.jpaWeb.domain.*;
import jpabook.jpaWeb.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 주문 2개 예시
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private  final  InitService initService;

/*    @PostConstruct
    public void init(){
        initService.dbInit1();

    }*/

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1(){
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("서울","1","111"));
            em.persist(member);

            Book book = new Book();
            book.setPrice(1000);
            book.setName("JPA");
            book.setStockQuantity(100);
            em.persist(book);

            Book book2 = new Book();
            book2.setPrice(1000);
            book2.setName("JPA2");
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book,1000,1);
            OrderItem orderItem2 =OrderItem.createOrderItem(book2,1000,2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member,delivery,orderItem,orderItem2);
            em.persist(order);



        }

    }
}
