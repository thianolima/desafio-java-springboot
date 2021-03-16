package br.com.desafiojavaspringboot.templates;

import br.com.desafiojavaspringboot.dtos.ProductDTO;
import br.com.desafiojavaspringboot.vos.ProductVO;
import lombok.Getter;

public class ProductVOTemplate extends BaseTemplate{

    @Getter
    private static final ProductVOTemplate instance = new ProductVOTemplate();

    public ProductVO getObjectValid(){
        return ProductVO.builder()
                .name(faker.beer().name())
                .description(faker.beer().style())
                .price(faker.number().randomNumber())
                .build();
    }
}
