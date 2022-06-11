package com.enterprise.application.collections;

import com.enterprise.application.model.ProviderDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer currentUnits;
    private Integer minimumUnits;
    private Integer maximumUnits;
    private Double price;
    private ProviderDTO provider;
}
