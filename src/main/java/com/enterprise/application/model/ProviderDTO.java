package com.enterprise.application.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
