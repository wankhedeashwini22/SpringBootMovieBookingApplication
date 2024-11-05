package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.CustomerDto;
import com.ultraPlexMovieBookingSystem.entity.Customer;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder().customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    public static Customer toCustomerEntity(CustomerDto dto) {
        return Customer.builder().customerId(dto.getCustomerId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }
}
