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
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;
    private String passport;
    @Email
    private String email;
}
