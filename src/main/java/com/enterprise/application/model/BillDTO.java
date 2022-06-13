package com.enterprise.application.model;

import com.enterprise.application.collections.SoldProduct;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillDTO {
    private String id;
    private String date;
    private String client;
    private String seller;
    private List<SoldProduct> productsSold;
    private Double total;
}
