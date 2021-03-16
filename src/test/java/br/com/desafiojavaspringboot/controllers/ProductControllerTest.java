package br.com.desafiojavaspringboot.controllers;

import br.com.desafiojavaspringboot.dtos.ProductDTO;
import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.services.ProductService;
import br.com.desafiojavaspringboot.templates.ProductDTOTemplate;
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
    @DisplayName("POST/products - Deve salvar um novo produto")
    public void createProductTest() throws Exception {
        ProductVO vo = ProductVOTemplate.getInstance().getObjectValid();
        ProductDTO dto = ProductDTOTemplate.getInstance().getObjectValid();
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

}
