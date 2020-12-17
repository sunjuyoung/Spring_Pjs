package jpabook.jpaWeb.domain;

import jpabook.jpaWeb.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter

public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //xToOne 기본 fetch =EAGER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int count; //주문 수량

    //@NoArgsConstructor(access = AccessLevel.PROTECTED) 롬복으로 사용가능
    protected OrderItem(){

    }

    //생성메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();//할인 등 다양한 경우대비 객체생성후
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(orderPrice);

        item.minusStock(count);
        return orderItem;
    }


    //비즈니스 로직
    public void cancel(){
        getItem().addStock(count);
    }

    //총가격 조회
    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }
}
