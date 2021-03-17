package br.com.desafiojavaspringboot.repositories;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.specifications.ProductSpecification;
import br.com.desafiojavaspringboot.templates.ProductTemplate;
import br.com.desafiojavaspringboot.vos.ProductFilterVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    @DisplayName("Deve salvar um livro.")
    public void saveTest() {
        Product product = ProductTemplate.getInstance().getObjectValid();
        Product productSave = repository.save(product);
        assertThat(productSave.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve realizar consulta avancada")
    public void findAllWithSpecificationTest() {
        List<Product> products = new ArrayList<>();
        for(int i= 0; i<=10; i++) {
            Product product = ProductTemplate.getInstance().getObjectValid();
            repository.save(product);
            products.add(product);
        }

        Product productMin = products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElseThrow(NoSuchElementException::new);

        Product productMax = products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElseThrow(NoSuchElementException::new);

        ProductFilterVO filter = ProductFilterVO.builder()
                .minPrice(productMin.getPrice())
                .maxPrice(productMax.getPrice())
                .q(productMax.getDescription())
                .build();

        List<Product> result = new ArrayList<>();
        result = repository.findAll(new ProductSpecification(filter));

        assertThat(result.size()).isGreaterThan(0);
    }
}
