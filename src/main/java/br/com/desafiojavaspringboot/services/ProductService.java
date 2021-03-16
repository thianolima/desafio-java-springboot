package br.com.desafiojavaspringboot.services;

import br.com.desafiojavaspringboot.dtos.ProductDTO;
import br.com.desafiojavaspringboot.entities.Product;

public interface ProductService {
    Product save(Product product);
}
