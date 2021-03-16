package br.com.desafiojavaspringboot.vos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

    @ApiModelProperty(example = "PILHA DURACEL AA", required = true)
    @NotNull
    String name;

    @ApiModelProperty(example = "PILHA ALCALINA DE LONGA DURAÇÃO", required = true)
    @NotNull
    String description;

    @ApiModelProperty(example = "4.85", required = true)
    @PositiveOrZero
    Number price;
}
