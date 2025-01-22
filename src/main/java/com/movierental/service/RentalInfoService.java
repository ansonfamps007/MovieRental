package com.movierental.service;

import com.movierental.exception.MovieNotFoundException;
import com.movierental.model.Customer;
import com.movierental.model.Movie;
import com.movierental.model.MovieRental;
import com.movierental.model.MovieType;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface RentalInfoService {

    String generateStatement(Customer customer);
}
