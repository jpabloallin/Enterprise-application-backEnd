package com.enterprise.application.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProviderDTO {

    private String id;
    private String name;
    private String passport;
    private String email;
}
