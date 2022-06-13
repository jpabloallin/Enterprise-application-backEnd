package com.enterprise.application.model;

import com.enterprise.application.collections.SoldProduct;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillDTO {
    private String id;
    private String date;
    @NotEmpty
    @Size(min = 2, message = "Client name should have at least 2 characters")
    private String client;
    @NotEmpty
    @Size(min = 2, message = "Seller name should have at least 2 characters")
    private String seller;
    private List<SoldProduct> productsSold;
    private Double total;
}
