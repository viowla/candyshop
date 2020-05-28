package kz.iitu.candyshop.controller;

import io.swagger.annotations.Api;
import kz.iitu.candyshop.entity.Dessert;
import kz.iitu.candyshop.entity.Order;
import kz.iitu.candyshop.entity.User;
import kz.iitu.candyshop.repository.DessertRepository;
import kz.iitu.candyshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
@Api(value = "Order Controller class")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DessertRepository dessertRepository;

    @GetMapping("")
    public String getAllOrders(Map<String, Object> model, @AuthenticationPrincipal User user){
        List<Order> orders = orderRepository.findAllByUsers(user);
        model.put("orders", orders);
        return "order";
    }

    @PostMapping("/create")
    public String addOrder(Order order, @AuthenticationPrincipal User user, String[] desserts){
        String all="";
        all+=desserts.toString();
        System.out.println(all);
        order.setTime(new Date());
        order.setUsers(user);
        ArrayList<Dessert> dessertsOrder = new ArrayList<>();

        for (String dessert : desserts) {
            Dessert p = dessertRepository.findById(Long.parseLong(dessert)).get();
            dessertsOrder.add(p);
        }
        order.setDesserts(dessertsOrder);
        int allPrice = 0;

        for(Dessert price:dessertsOrder){
            allPrice+=price.getPrice();
        }
        order.setOrderPrice(allPrice);

        orderRepository.saveAndFlush(order);
        return "redirect:/";

    }
}
