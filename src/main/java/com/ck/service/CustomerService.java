package com.ck.service;

import com.ck.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerByName(String name);
    int insertCustomer(Customer customer);
    List<String> getCustomerName(String name);
}
