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
    @NotEmpty
    @Size(min = 2, message = "product name should have at least 2 characters")
    private String name;
    private String description;
    private Integer currentUnits;
    @Size(min = 1, message = "higher than 1")
    private Integer minimumUnits;
    private Integer maximumUnits;
    private Double price;
    private ProviderDTO provider;
}
