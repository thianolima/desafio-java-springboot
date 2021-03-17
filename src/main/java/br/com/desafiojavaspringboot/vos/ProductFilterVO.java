package br.com.desafiojavaspringboot.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterVO {

    @ApiModelProperty(example = "10,30", position = 0)
    BigDecimal minPrice;

    @ApiModelProperty(example = "50", position = 1)
    BigDecimal maxPrice;

    @ApiModelProperty(example = "PILHA", position = 2)
    String q;
}
