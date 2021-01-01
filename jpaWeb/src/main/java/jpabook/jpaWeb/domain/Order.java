package jpabook.jpaWeb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // new ByteBuddyInterceptor(Proxy)

    //CascadeType.ALL persist 전파 저장 ,삭제   //order를 persist하면 같이
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //CascadeType.ALL  order를 persist하면 같이
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "deliver_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //order_date

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태

    //양방향일때 연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrdersItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //@NoArgsConstructor(access = AccessLevel.PROTECTED) 롬복으로 사용가능
    //직접생성 방지
    protected Order(){

    }

    //생성 메서드
    public static Order createOrder(Member member,Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrdersItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //비즈니스 로직

    /**
     * 주문취소
     */
    public void cancel(){
        if(delivery.getStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송중입니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
        //데이터만 바꾸면 update쿼리가 생성
    }

    /**
     * 전체 주문 가격 조회
     * @return
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice  += orderItem.getTotalPrice();
        }
        //int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();

        return totalPrice;

    }

}
