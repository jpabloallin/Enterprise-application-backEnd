package com.enterprise.application.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private Integer currentUnits;
    private Integer minimumUnits;
    private Integer maximumUnits;
    private Double price;
    private ProviderDTO provider;
}
