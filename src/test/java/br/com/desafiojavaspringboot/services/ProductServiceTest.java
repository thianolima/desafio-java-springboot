package br.com.desafiojavaspringboot.services;

import br.com.desafiojavaspringboot.dtos.ProductDTO;
import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.repositories.ProductRepository;
import br.com.desafiojavaspringboot.services.impl.ProductServiceImpl;
import br.com.desafiojavaspringboot.templates.ProductTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    ProductService service;

    @MockBean
    ProductRepository repository;

    @BeforeEach
    public void setup() {
        this.service = new ProductServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um novo produto")
    public void saveTest() {
        Product product = ProductTemplate.getInstance().getObjectValid();

        when(repository.save(Mockito.any(Product.class))).thenReturn(product);

        Product productSave = service.save(product);

        assertThat(productSave.getId()).isNotNull();
        assertThat(productSave.getName()).isEqualTo(product.getName());
        assertThat(productSave.getDescription()).isEqualTo(product.getDescription());
        assertThat(productSave.getPrice()).isEqualTo(product.getPrice());
    }
}
