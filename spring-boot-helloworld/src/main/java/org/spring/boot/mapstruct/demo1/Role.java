package org.spring.boot.mapstruct.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
    private Long id;
    private String roleName;
    private String description;
}

