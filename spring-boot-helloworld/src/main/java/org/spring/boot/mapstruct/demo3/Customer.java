package org.spring.boot.mapstruct.demo3;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangzhigang
 * @date 2022-01-29 09:23
 */
@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
}
