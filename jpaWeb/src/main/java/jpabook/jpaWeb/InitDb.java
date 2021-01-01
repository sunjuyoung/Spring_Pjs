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

   @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1(){
            Member member = createMember("userB","부산","44","32");
            em.persist(member);

            Book book = createBook("JPA",21000);
            em.persist(book);

            Book book2 = createBook("JPA2",31000);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book,21000,1);
            OrderItem orderItem2 =OrderItem.createOrderItem(book2,31000,2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member,delivery,orderItem,orderItem2);
            em.persist(order);
        }

        public void dbInit2(){
            Member member = createMember("userB","서울","강남","123");
            em.persist(member);

            Book book = createBook("SPRING1",20000);
            em.persist(book);

            Book book2 = createBook("SPRING2",30000);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book,20000,1);
            OrderItem orderItem2 =OrderItem.createOrderItem(book2,30000,2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member,delivery,orderItem,orderItem2);
            em.persist(order);

        }

        private Book createBook(String name,int i) {
            Book book = new Book();
            book.setPrice(i);
            book.setName(name);
            book.setStockQuantity(100);
            return book;
        }

        private Member createMember(String userA,String city,String street,String zipcode) {
            Member member = new Member();
            member.setName(userA);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

    }
}
