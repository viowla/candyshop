package kz.iitu.candyshop.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Dessert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int price;
    private String ingridients;
}
