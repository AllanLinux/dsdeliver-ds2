package com.allanlf.dsdeliver.repositories;

import com.allanlf.dsdeliver.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
