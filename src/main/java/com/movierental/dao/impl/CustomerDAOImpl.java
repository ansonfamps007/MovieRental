package com.movierental.dao.impl;

import com.movierental.dao.CustomerDAO;
import com.movierental.model.Customer;
import com.movierental.model.MovieRental;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //Hard code and initialize the map for demo purposes
    private final Map<String, Customer> customers = Map.of(
            "1001", new Customer("C. U. Stomer",
                    List.of(new MovieRental("F001", 3),
                            new MovieRental("F002", 1),
                            new MovieRental("F004", 2)))
    );

    @Override
    public Optional<Customer> getCustomerById(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }
}
