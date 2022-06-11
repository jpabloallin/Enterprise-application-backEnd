package com.enterprise.application.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiptDTO {
    private String id;
    private LocalDate date;
    private ProviderDTO provider;
    private String productId;
    private Integer units;

}
