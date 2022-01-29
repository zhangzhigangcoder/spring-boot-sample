package org.spring.boot.mapstruct.demo2;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zhangzhigang
 * @date 2022-01-29 09:25
 */
@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    /**
     *  source和target对象中的成员变量可以不定义get和set方法，但是变量要定义成public
     *
     * @param customer
     * @return
     */
    @Mapping(source = "name", target = "customerName")
    CustomerDTO toCustomerDTO(Customer customer);

}
