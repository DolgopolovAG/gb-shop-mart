package ru.gb.gbshopmart.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbshopmart.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
}
