package com.movierental.service;

import com.movierental.model.Customer;

public interface StatementGeneratorService {
    String generateStatement(Customer customer, double totalAmount, int frequentEnterPoints, StringBuilder details);
}
