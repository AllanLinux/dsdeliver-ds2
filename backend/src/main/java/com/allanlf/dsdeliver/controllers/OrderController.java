package com.allanlf.dsdeliver.controllers;

import com.allanlf.dsdeliver.dto.OrderDTO;
import com.allanlf.dsdeliver.dto.ProductDTO;
import com.allanlf.dsdeliver.entities.Order;
import com.allanlf.dsdeliver.service.OrderService;
import com.allanlf.dsdeliver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    // RequestBody para sinalizar que o conteudo virá no corpo da requisição
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto) {
        dto = service.insert(dto);
        // URI para retornar o id do objeto criado e retornar code 201
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}