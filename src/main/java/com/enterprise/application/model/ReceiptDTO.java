package com.enterprise.application.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceiptDTO {
    private String id;
    private String date;
    @NotEmpty
    private String providerId;
    @NotEmpty
    private String productId;
    @NotEmpty
    private Integer units;

}
