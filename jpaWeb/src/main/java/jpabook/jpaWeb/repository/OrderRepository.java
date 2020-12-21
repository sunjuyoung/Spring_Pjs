package jpabook.jpaWeb.repository;


import jpabook.jpaWeb.domain.Member;
import jpabook.jpaWeb.domain.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.common.reflection.XMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;


    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
      return  em.createQuery("select o from Order o join o.member m " +
                " where o.staus = :status" +
                " and m.name like :name",Order.class)
                .setParameter("status",orderSearch.getOrderStatus())
                .setParameter("name",orderSearch.getMemberName())
                .getResultList();
    }




}
