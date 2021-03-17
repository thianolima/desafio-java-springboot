package br.com.desafiojavaspringboot.services.impl;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.repositories.ProductRepository;
import br.com.desafiojavaspringboot.services.ProductService;
import br.com.desafiojavaspringboot.vos.ProductVO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private String MSG_NOT_FOUND = "Produto não encontrado com id %d";

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(String.format(MSG_NOT_FOUND, id), 0));
    }

    @Override
    public Boolean delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(String.format(MSG_NOT_FOUND, id), 0));

        repository.delete(product);

        return true;
    }

    @Override
    public Product update(Product product, Long id) {
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
}
