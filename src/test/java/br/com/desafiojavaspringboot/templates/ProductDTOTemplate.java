package br.com.desafiojavaspringboot.templates;

import br.com.desafiojavaspringboot.dtos.ProductDTO;
import lombok.Getter;

public class ProductDTOTemplate extends BaseTemplate{

    @Getter
    private static final ProductDTOTemplate instance = new ProductDTOTemplate();

    public ProductDTO getObjectValid(){
        return ProductDTO.builder()
                .id(faker.random().nextLong())
                .name(faker.beer().name())
                .description(faker.beer().style())
                .price(faker.number().randomNumber())
                .build();
    }
}
