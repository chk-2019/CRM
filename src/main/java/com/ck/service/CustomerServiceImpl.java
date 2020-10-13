package com.ck.service;

import com.ck.dao.CustomerDao;
import com.ck.domain.Customer;
import com.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;
    @Override
    public Customer getCustomerByName(String name) {
        Customer customer = customerDao.selectCusByName(name);
        return customer;
    }

    @Override
    public int insertCustomer(Customer customer) {
        int i = customerDao.insertCustomer(customer);
        return i;
    }

    @Override
    public List<String> getCustomerName(String name) {
        List<String> stringList = customerDao.selectCustomerName(name);
        return stringList;

    }
}
