package br.com.desafiojavaspringboot.templates;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.vos.ProductVO;
import lombok.Getter;

public class ProductTemplate extends BaseTemplate{

    @Getter
    private static final ProductTemplate instance = new ProductTemplate();

    public Product getObjectValid(){
        return Product.builder()
                .id(faker.random().nextLong())
                .name(faker.beer().name())
                .description(faker.beer().style())
                .price(faker.number().randomNumber())
                .build();
    }
}
