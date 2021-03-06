package jpabook.jpaWeb.service;

import jpabook.jpaWeb.domain.Delivery;
import jpabook.jpaWeb.domain.Member;
import jpabook.jpaWeb.domain.Order;
import jpabook.jpaWeb.domain.OrderItem;
import jpabook.jpaWeb.domain.item.Item;
import jpabook.jpaWeb.repository.ItemRepository;
import jpabook.jpaWeb.repository.MemberRepository;
import jpabook.jpaWeb.repository.OrderRepository;
import jpabook.jpaWeb.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        //주문 생성
        Order order = Order.createOrder(member,delivery,orderItem);

        //주문 저장
        orderRepository.save(order);  ////CascadeType.ALL  delivery,orderItem persist하면 같이

        return order.getId();
    }


    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        //엔티티조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();

    }


   //검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
}
