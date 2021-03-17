package br.com.desafiojavaspringboot.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

    @ApiModelProperty(example = "PILHA DURACEL AA", required = true)
    @NotBlank
    String name;

    @ApiModelProperty(example = "PILHA ALCALINA DE LONGA DURAÇÃO", required = true)
    @NotBlank
    String description;

    @ApiModelProperty(example = "4.85", required = true)
    @PositiveOrZero
    BigDecimal price;
}
