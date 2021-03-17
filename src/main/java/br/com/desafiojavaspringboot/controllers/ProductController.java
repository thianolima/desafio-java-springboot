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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            @ApiResponse(code = 400, message = "Produto não cadastrado.")
    })
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductVO vo, UriComponentsBuilder uriBuilder){
        Product product = modelMapper.map(vo, Product.class);
        product = service.save(product);

        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("{id}")
    @ApiOperation("Pesquisar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto encontrado."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(modelMapper.map(service.findById(id), ProductDTO.class));
    }

    @DeleteMapping("{id}")
    @ApiOperation("Excluir")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto excluído."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable  Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto alterado."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductVO vo, @PathVariable Long id){
        Product product = service.findById(id);

        product.setDescription(vo.getDescription());
        product.setName(vo.getName());
        product.setPrice(vo.getPrice());

        service.update(product, id);
        return ResponseEntity.ok(modelMapper.map(product, ProductDTO.class));
    }
}
