package org.spring.boot.mapstruct.demo2;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhangzhigang
 * @date 2022-01-29 09:36
 */
public class CustomerMapperTest {
    private Customer customer = null;
    private CustomerDTO customerDTO = null;


    @Before
    public void before() {
        customer = new Customer(111L, "zhang");
    }

    @Test
    public void test() {
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.toCustomerDTO(customer);
        System.out.println(customerDTO);
    }
}