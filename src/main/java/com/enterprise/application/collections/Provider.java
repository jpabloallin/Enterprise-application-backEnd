package com.enterprise.application.collections;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "provider")
public class Provider {
    @Id
    private String id;
    private String name;
    private String passport;
    private String email;
}
