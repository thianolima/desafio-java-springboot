package br.com.desafiojavaspringboot.controllers;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.services.ProductService;
import br.com.desafiojavaspringboot.templates.ProductTemplate;
import br.com.desafiojavaspringboot.templates.ProductVOTemplate;
import br.com.desafiojavaspringboot.vos.ProductVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService service;

    @Test
    @DisplayName("POST/products - Deve salvar um novo produto.")
    public void insertTest() throws Exception {
        ProductVO vo = ProductVOTemplate.getInstance().getObjectValid();
        Product product = ProductTemplate.getInstance().getObjectValid();

        BDDMockito.given(service.save(Mockito.any(Product.class))).willReturn(product);

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("name").isNotEmpty())
                .andExpect(jsonPath("description").isNotEmpty())
                .andExpect(jsonPath("price").isNotEmpty());
    }

    @Test
    @DisplayName("GET/products - Deve retornar todos os produtos cadastrados.")
    public void findByIdTest() throws Exception {
        List<Product> products = ProductTemplate.getInstance().getListValid();

        BDDMockito.given(service.findAll()).willReturn(products);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(100)))
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].description").isNotEmpty())
                .andExpect(jsonPath("$[0].price").isNotEmpty());
    }

    @Test
    @DisplayName("GET/products/{id} - Deve retornar produto pelo id.")
    public void findAll() throws Exception {
        Product product = ProductTemplate.getInstance().getObjectValid();

        BDDMockito.given(service.findById(Mockito.anyLong())).willReturn(product);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(product.getId()))
                .andExpect(jsonPath("name").value(product.getName()))
                .andExpect(jsonPath("description").value(product.getDescription()))
                .andExpect(jsonPath("price").value(product.getPrice()));
    }

    @Test
    @DisplayName("DELETE/products/{id} - Deve excluir um produto por id.")
    public void deleteTest() throws Exception {
        Product product = ProductTemplate.getInstance().getObjectValid();

        BDDMockito.given(service.delete(Mockito.anyLong())).willReturn(true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/products/" + product.getId())
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("PUT/products/{id} - Deve atualizar um livro.")
    public void updateTest() throws Exception {
        ProductVO vo = ProductVOTemplate.getInstance().getObjectValid();
        Product product = ProductTemplate.getInstance().getObjectValid();

        Product productUpdate = ProductTemplate.getInstance().getObjectValid();
        productUpdate.setId(product.getId());
        productUpdate.setName(vo.getName());
        productUpdate.setPrice(vo.getPrice());
        productUpdate.setDescription(vo.getDescription());

        BDDMockito.given(service.findById(Mockito.anyLong())).willReturn(product);
        BDDMockito.given(service.update(Mockito.any(Product.class), Mockito.anyLong())).willReturn(productUpdate);

        String json = new ObjectMapper().writeValueAsString(vo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value(productUpdate.getName()))
            .andExpect(jsonPath("description").value(productUpdate.getDescription()))
            .andExpect(jsonPath("price").value(productUpdate.getPrice()));
    }

}
