package br.com.desafiojavaspringboot.repositories;

import br.com.desafiojavaspringboot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
