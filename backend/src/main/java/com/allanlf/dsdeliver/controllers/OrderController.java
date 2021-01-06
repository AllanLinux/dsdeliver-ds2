package com.allanlf.dsdeliver.controllers;

import com.allanlf.dsdeliver.dto.OrderDTO;
import com.allanlf.dsdeliver.dto.ProductDTO;
import com.allanlf.dsdeliver.entities.Order;
import com.allanlf.dsdeliver.service.OrderService;
import com.allanlf.dsdeliver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> list = service.findOrdersWithProducts();
        return ResponseEntity.ok().body(list);
    }
}
