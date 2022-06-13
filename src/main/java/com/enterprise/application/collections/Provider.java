package com.enterprise.application.collections;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "provider")
public class Provider {
    @Id
    private String id;
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;
    private String passport;
    @Email
    private String email;

}
