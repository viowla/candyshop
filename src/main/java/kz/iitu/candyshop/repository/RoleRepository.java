package kz.iitu.candyshop.repository;

import kz.iitu.candyshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
