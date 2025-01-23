package com.movierental.dao;

import com.movierental.model.Customer;

import java.util.Optional;

public interface CustomerDAO {
    Optional<Customer> getCustomerById(String id);
}
