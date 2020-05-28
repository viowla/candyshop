package kz.iitu.candyshop.entity;



import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private Date time;
    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderPizza",
            joinColumns = {@JoinColumn(name="order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="pizza_id", referencedColumnName = "id")})
    private List<Dessert> pizzas;


}
