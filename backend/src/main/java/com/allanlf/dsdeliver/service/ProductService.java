package com.allanlf.dsdeliver.service;

import com.allanlf.dsdeliver.dto.ProductDTO;
import com.allanlf.dsdeliver.entities.Product;
import com.allanlf.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    // Para não fazer o lock de escrita no banco, pois é apenas consulta
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAllByOrderByNameAsc();
        return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }



}
