package com.movierental.controller;

import com.movierental.model.Customer;
import com.movierental.service.RentalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalInfoService rentalInfoService;

    @GetMapping("/statement")
    public String generateStatement(@RequestParam String customerId) {
        return rentalInfoService.generateStatement(customerId);
    }
}
