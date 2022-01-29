package org.spring.boot.mapstruct.demo3;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhangzhigang
 * @date 2022-01-29 09:24
 */
@Data
@ToString
public class CustomerDTO {
    private Long id;
    private String customerName;
}
