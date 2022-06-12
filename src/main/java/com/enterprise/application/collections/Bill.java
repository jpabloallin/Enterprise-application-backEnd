package com.enterprise.application.collections;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "bill")
public class Bill {
    private String id;
    private String date;
    private String client;
    private String seller;
    private List<SoldProduct> productsSold;
    private Double total;
}
