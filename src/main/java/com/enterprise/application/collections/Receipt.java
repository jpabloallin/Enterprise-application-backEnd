package com.enterprise.application.collections;

import com.enterprise.application.model.ProviderDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "receipt")
public class Receipt {
    @Id
    private String id;
    private LocalDate date;
    private ProviderDTO provider;
    private String productId;
    private Integer units;
}
