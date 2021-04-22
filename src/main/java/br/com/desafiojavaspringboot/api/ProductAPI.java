package br.com.desafiojavaspringboot.api;

import br.com.desafiojavaspringboot.dtos.ProductDTO;
import br.com.desafiojavaspringboot.vos.ProductFilterVO;
import br.com.desafiojavaspringboot.vos.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/products")
@Api(tags = "Produtos", description = "Gerencia os produtos do seu catalogo.")
public interface ProductAPI {

    @PostMapping
    @ApiOperation("Inserir")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado."),
            @ApiResponse(code = 400, message = "Produto não cadastrado.")
    })
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductVO vo, UriComponentsBuilder uriBuilder);

    @GetMapping("{id}")
    @ApiOperation("Pesquisar pelo id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto encontrado."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id);

    @GetMapping
    @ApiOperation("Listar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto encontrado."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<List<ProductDTO>> findById();

    @GetMapping("/search")
    @ApiOperation("Consultar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto encontrado."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<List<ProductDTO>> search(ProductFilterVO filter);

    @DeleteMapping("{id}")
    @ApiOperation("Excluir")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto excluído."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable  Long id);

    @PutMapping("{id}")
    @ApiOperation("Alterar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto alterado."),
            @ApiResponse(code = 404, message = "Produto não encontrado.")
    })
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductVO vo, @PathVariable Long id);
}
