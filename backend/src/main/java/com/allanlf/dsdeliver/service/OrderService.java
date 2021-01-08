package com.allanlf.dsdeliver.service;

import com.allanlf.dsdeliver.dto.OrderDTO;
import com.allanlf.dsdeliver.dto.ProductDTO;
import com.allanlf.dsdeliver.entities.Order;
import com.allanlf.dsdeliver.entities.OrderStatus;
import com.allanlf.dsdeliver.entities.Product;
import com.allanlf.dsdeliver.repositories.OrderRepository;
import com.allanlf.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository repository;
    // Para não fazer o lock de escrita no banco, pois é apenas consulta
    @Transactional(readOnly = true)
    public List<OrderDTO> findOrdersWithProducts() {
        List<Order> list = repository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order(
                null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
                Instant.now(), OrderStatus.PENDING);
        for(ProductDTO p : dto.getProducts()) {
            Product product =  productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = repository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id) {
        Order order = repository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = repository.save(order);
        return new OrderDTO(order);
    }


}
