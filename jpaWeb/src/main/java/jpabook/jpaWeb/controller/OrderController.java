package jpabook.jpaWeb.controller;

import jpabook.jpaWeb.domain.Member;
import jpabook.jpaWeb.domain.Order;
import jpabook.jpaWeb.domain.item.Item;
import jpabook.jpaWeb.repository.OrderSearch;
import jpabook.jpaWeb.service.ItemService;
import jpabook.jpaWeb.service.MemberService;
import jpabook.jpaWeb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private  final OrderService orderService;
    private final MemberService memberService;
    private  final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items  = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/oderForm";
    }

    /**
     * 주문
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,@RequestParam("itemId")Long itemId
                        ,@RequestParam("count")int count){
        Long orderId = orderService.order(memberId,itemId,count);
        return "redirect:/orders";
    }

    /**
     * 주문 내역
     * @param orderSearch
     * @param model
     * @return
     */
    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch,Model model){
        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders",orders);

        return "order/orderList";
    }

    /**
     * 주문 취소
     * @param orderId
     * @return
     */
    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId")Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }


}
