package br.com.desafiojavaspringboot.services;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.vos.ProductFilterVO;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    Boolean delete(Long id);

    Product update(Product product, Long id);

    List<Product> findAll();

    List<Product> search(ProductFilterVO filter);
}
