package kz.iitu.candyshop.repository;

import kz.iitu.candyshop.entity.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertRepository extends JpaRepository<Dessert, Long> {
}
