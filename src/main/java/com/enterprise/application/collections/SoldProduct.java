package com.enterprise.application.collections;

import lombok.Data;

@Data
public class SoldProduct {
    private String id;
    private String name;
    private Double price;
    private Integer units;
}
