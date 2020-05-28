package kz.iitu.candyshop.repository;

import kz.iitu.candyshop.entity.Order;
import kz.iitu.candyshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUsers_Username(String username);
    List<Order> findAllByUsers(User user);
}
