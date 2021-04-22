package br.com.desafiojavaspringboot.controllers;

import br.com.desafiojavaspringboot.api.ProductAPI;
import br.com.desafiojavaspringboot.dtos.ProductDTO;
import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.services.ProductService;
import br.com.desafiojavaspringboot.vos.ProductFilterVO;
import br.com.desafiojavaspringboot.vos.ProductVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductAPI {

    @Autowired
    private ModelMapper modelMapper;

    private final ProductService service;

    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductVO vo, UriComponentsBuilder uriBuilder){
        Product product = modelMapper.map(vo, Product.class);
        product = service.save(product);

        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(modelMapper.map(service.findById(id), ProductDTO.class));
    }

    public ResponseEntity<List<ProductDTO>> findById(){
        List<Product> result = service.findAll();

        if (result.size() > 0 || result == null){
            ResponseEntity.notFound();
        }

        List<ProductDTO> resultDto = result.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultDto);
    }

    public ResponseEntity<List<ProductDTO>> search(ProductFilterVO filter){
        List<Product> result = service.search(filter);

        if (result.size() > 0 || result == null){
            ResponseEntity.notFound();
        }

        List<ProductDTO> resultDto = result.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultDto);
    }

    public ResponseEntity<HttpStatus> delete(@PathVariable  Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductVO vo, @PathVariable Long id){
        Product product = service.findById(id);

        product.setDescription(vo.getDescription());
        product.setName(vo.getName());
        product.setPrice(vo.getPrice());

        service.update(product, id);
        return ResponseEntity.ok(modelMapper.map(product, ProductDTO.class));
    }
}
