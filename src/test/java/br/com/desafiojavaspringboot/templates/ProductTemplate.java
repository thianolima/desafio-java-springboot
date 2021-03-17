package br.com.desafiojavaspringboot.templates;

import br.com.desafiojavaspringboot.entities.Product;
import br.com.desafiojavaspringboot.vos.ProductVO;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductTemplate extends BaseTemplate{

    @Getter
    private static final ProductTemplate instance = new ProductTemplate();

    public Product getObjectValid(){
        return Product.builder()
                .id(faker.random().nextLong())
                .name(faker.beer().name())
                .description(faker.beer().style())
                .price(new BigDecimal(Math.random()))
                .build();
    }

    public List<Product> getListValid(){
        List<Product> products = new ArrayList<>();
        for(int i=0; i<100; i++){
            products.add(ProductTemplate.getInstance().getObjectValid());
        }
        return products;
    }
}
