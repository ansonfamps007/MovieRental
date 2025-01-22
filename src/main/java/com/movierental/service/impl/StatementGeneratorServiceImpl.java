package com.movierental.service.impl;

import com.movierental.model.Customer;
import com.movierental.service.StatementGeneratorService;
import org.springframework.stereotype.Service;

/**
 * Service for generating rental statements for customers.
 */
@Service
public class StatementGeneratorServiceImpl implements StatementGeneratorService {

    @Override
    public String generateStatement(Customer customer, double totalAmount, int frequentEnterPoints, StringBuilder details) {
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(customer.name()).append(":\n");
        result.append(details);
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");
        return result.toString();
    }
}
