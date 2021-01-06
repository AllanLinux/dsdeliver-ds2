package com.allanlf.dsdeliver.service;

import com.allanlf.dsdeliver.dto.OrderDTO;
import com.allanlf.dsdeliver.dto.ProductDTO;
import com.allanlf.dsdeliver.entities.Order;
import com.allanlf.dsdeliver.entities.Product;
import com.allanlf.dsdeliver.repositories.OrderRepository;
import com.allanlf.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    // Para não fazer o lock de escrita no banco, pois é apenas consulta
    @Transactional(readOnly = true)
    public List<OrderDTO> findOrdersWithProducts() {
        List<Order> list = repository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }



}
