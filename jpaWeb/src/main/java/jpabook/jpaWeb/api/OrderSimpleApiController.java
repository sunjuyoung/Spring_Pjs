package jpabook.jpaWeb.api;

import jpabook.jpaWeb.domain.Order;
import jpabook.jpaWeb.repository.OrderRepository;
import jpabook.jpaWeb.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * XtoOne(ManyToOne,OneToOne)
 * order
 * order -> member
 * order -> delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private  final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all =orderRepository.findAllByString(new OrderSearch());
        for(Order order:all){
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        }
        return all;
    }



}
