package com.ck.dao;

import com.ck.domain.Customer;

import java.util.List;

public interface CustomerDao {
    Customer selectCusByName(String name);
    int insertCustomer(Customer customer);
    List<String> selectCustomerName(String name);

}
