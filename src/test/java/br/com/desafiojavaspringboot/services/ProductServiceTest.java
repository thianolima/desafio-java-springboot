package br.com.desafiojavaspringboot.services;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.repositories.ProductRepository;
import br.com.desafiojavaspringboot.services.impl.ProductServiceImpl;
import br.com.desafiojavaspringboot.specifications.ProductSpecification;
import br.com.desafiojavaspringboot.templates.ProductTemplate;
import br.com.desafiojavaspringboot.vos.ProductFilterVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("Deve econtrar um produto por id")
    public void findByIdTest() {
        Product product = ProductTemplate.getInstance().getObjectValid();

        Mockito.when(repository.findById(product.getId()))
                .thenReturn(Optional.of(product));

        org.junit.jupiter.api.Assertions
                .assertDoesNotThrow(() -> service.findById(product.getId()));

        Mockito.verify(repository, Mockito.times(1))
                .findById(Mockito.anyLong());
    }

    @Test
    @DisplayName("Deve retornar todos os produtos cadastrados.")
    public void findAllTest() {
        List<Product> products = ProductTemplate.getInstance().getListValid();

        Mockito.when(repository.findAll()).thenReturn(products);

        org.junit.jupiter.api.Assertions
                .assertDoesNotThrow(() -> service.findAll());

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar todos os produtos cadastrados.")
    public void searchTest() {
        List<Product> products = ProductTemplate.getInstance().getListValid();

        Mockito.when(repository.findAll(Mockito.any(ProductSpecification.class))).thenReturn(products);

        org.junit.jupiter.api.Assertions
                .assertDoesNotThrow(() -> service.search(Mockito.any(ProductFilterVO.class)));

        Mockito.verify(repository, Mockito.times(1)).findAll(Mockito.any(ProductSpecification.class));
    }

    @Test
    @DisplayName("Deve excluir um produto.")
    public void deleteTest() {
        Product product = ProductTemplate.getInstance().getObjectValid();

        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(product));

        org.junit.jupiter.api.Assertions
                .assertDoesNotThrow(() -> service.delete(product.getId()));

        Mockito.verify(repository, Mockito.times(1))
                .delete(Mockito.any(Product.class));
    }

    @Test
    @DisplayName("Deve atualizar um produto.")
    public void updateTest() {
        Product product = ProductTemplate.getInstance().getObjectValid();

        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(product));

        Mockito.when(repository.save(Mockito.any(Product.class)))
                .thenReturn(product);

        org.junit.jupiter.api.Assertions
                .assertDoesNotThrow(() -> service.update(product, product.getId()));

        Mockito.verify(repository, Mockito.times(1))
                .save(Mockito.any(Product.class));
    }
}
