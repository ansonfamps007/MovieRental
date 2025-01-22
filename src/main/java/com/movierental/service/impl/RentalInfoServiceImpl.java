package com.movierental.service.impl;

import com.movierental.dao.MovieDAO;
import com.movierental.exception.MovieNotFoundException;
import com.movierental.model.Customer;
import com.movierental.model.Movie;
import com.movierental.model.MovieRental;
import com.movierental.model.MovieType;
import com.movierental.service.ChargeCalculator;
import com.movierental.service.RentalInfoService;
import com.movierental.service.StatementGeneratorService;
import org.springframework.stereotype.Service;

/*

Class for process rental info

* */
@Service
public class RentalInfoServiceImpl implements RentalInfoService {

    private final MovieDAO movieDAO;

    private final ChargeCalculator chargeCalculator;

    private final StatementGeneratorService statementGeneratorService;

    public RentalInfoServiceImpl(MovieDAO movieDAO, ChargeCalculator chargeCalculator, StatementGeneratorService statementGeneratorService) {
        this.movieDAO = movieDAO;
        this.chargeCalculator = chargeCalculator;
        this.statementGeneratorService = statementGeneratorService;
    }

    @Override
    public String generateStatement(Customer customer) {

        if (null == customer.rentals() || customer.rentals().size() == 0) {
            throw new MovieNotFoundException("No rental details found !");
        }

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder details = new StringBuilder();

        for (MovieRental rental : customer.rentals()) {

            if (null != rental) {

                // Fetch movie details
                Movie movie = movieDAO.getMovieById(rental.movieId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + rental.movieId()));

                // Calculate charge
                double charge = chargeCalculator.calculateCharge(movie, rental.days());

                // Append details
                details.append("\t").append(movie.title()).append("\t").append(charge).append("\n");

                // Update totals
                totalAmount += charge;

                // Add frequent renter points
                frequentEnterPoints++;
                if (MovieType.NEW_RELEASE.equals(movie.code()) && rental.days() > 2) {
                    frequentEnterPoints++;
                }
            }
        }
        return statementGeneratorService.generateStatement(customer, totalAmount, frequentEnterPoints, details);
    }
}
