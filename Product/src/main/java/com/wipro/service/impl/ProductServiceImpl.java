package com.wipro.service.impl;

import com.wipro.entity.Product;
import com.wipro.repository.ProductRepo;
import com.wipro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo repo;

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Product findById(int id) {
        return repo.findById(id).orElse(new Product());
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = repo.findById(product.getId());
        if (existingProduct.isPresent()) {
            existingProduct.get().setDescription(product.getDescription());
            existingProduct.get().setCategory(product.getCategory());
            existingProduct.get().setPrice(product.getPrice());
            repo.save(existingProduct.get());
            return existingProduct.get();
        } else {
            throw new RuntimeException("order not found for the given id : " + product.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
