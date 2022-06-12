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
    private String date;
    private String providerId;
    private String productId;
    private Integer units;

}
