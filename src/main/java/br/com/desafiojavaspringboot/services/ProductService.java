package br.com.desafiojavaspringboot.services;

import br.com.desafiojavaspringboot.entities.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    Boolean delete(Long id);

    Product update(Product product, Long id);

    List<Product> findAll();
}
