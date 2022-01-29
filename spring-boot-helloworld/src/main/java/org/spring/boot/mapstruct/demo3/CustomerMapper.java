package org.spring.boot.mapstruct.demo3;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 注入spring容器中
 *
 * @author zhangzhigang
 * @date 2022-01-29 09:25
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "name", target = "customerName")
    CustomerDTO toCustomerDTO(Customer customer);
}
