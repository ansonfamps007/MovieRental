package com.movierental.controller;

import com.movierental.model.Customer;
import com.movierental.service.RentalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalInfoService rentalInfoService;

    @PostMapping("/statement")
    public String generateStatement(@RequestBody Customer customer) {
        return rentalInfoService.generateStatement(customer);
    }
}
