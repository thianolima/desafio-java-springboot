package br.com.desafiojavaspringboot.repositories;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.templates.ProductTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProductRepository repository;

    @Test
    @DisplayName("Deve salvar um livro.")
    public void saveTest() {
        Product product = ProductTemplate.getInstance().getObjectValid();
        Product productSave = repository.save(product);
        assertThat(productSave.getId()).isNotNull();
    }
}
