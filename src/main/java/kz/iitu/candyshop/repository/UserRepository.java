package kz.iitu.candyshop.repository;

import kz.iitu.candyshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findAllByUserRoleContains(String role);
}
