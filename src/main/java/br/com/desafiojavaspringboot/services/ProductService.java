package br.com.desafiojavaspringboot.services;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.vos.ProductVO;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    Boolean delete(Long id);

    Product update(Product product, Long id);
}
