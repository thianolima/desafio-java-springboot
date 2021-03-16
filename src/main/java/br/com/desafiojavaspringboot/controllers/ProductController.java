package br.com.desafiojavaspringboot.controllers;

import br.com.desafiojavaspringboot.dtos.ProductDTO;
import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.services.ProductService;
import br.com.desafiojavaspringboot.vos.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Api(tags = "Produtos", description = "Gerencia os produtos do seu catalogo.")
public class ProductController {

    @Autowired
    private ModelMapper modelMapper;

    private final ProductService service;

    @PostMapping
    @ApiOperation("Inserir")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado."),
    })
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductVO vo, UriComponentsBuilder uriBuilder){
        Product product = modelMapper.map(vo, Product.class);
        product = service.save(product);

        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
