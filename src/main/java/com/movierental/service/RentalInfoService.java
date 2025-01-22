package com.movierental.service;

import com.movierental.exception.MovieNotFoundException;
import com.movierental.model.Customer;
import com.movierental.model.Movie;
import com.movierental.model.MovieRental;
import com.movierental.model.MovieType;
import org.springframework.stereotype.Service;

import java.util.Map;

/*

Class for process rental info

* */
@Service
public class RentalInfoService {

    //Hard code and initialize the map for demo purposes
    private final Map<String, Movie> movies = Map.of(
            "F001", new Movie("You've Got Mail", "regular"),
            "F002", new Movie("Matrix", "regular"),
            "F003", new Movie("Cars", "childrens"),
            "F004", new Movie("Fast & Furious X", "new")
    );

    private final ChargeCalculator chargeCalculator;

    public RentalInfoService(ChargeCalculator chargeCalculator) {
        this.chargeCalculator = chargeCalculator;
    }

    public String generateStatement(Customer customer) {
        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.name() + ":\n");

        if (null == customer.rentals() || customer.rentals().size() == 0) {
            throw new MovieNotFoundException("No rental details found !");
        } else {
            for (MovieRental rental : customer.rentals()) {

                if (null != rental && movies.containsKey(rental.movieId())) {
                    Movie movie = movies.get(rental.movieId());
                    // Calculate charge
                    double thisAmount = chargeCalculator.calculateCharge(movie, rental.days());

                    // Add frequent renter points
                    frequentEnterPoints++;
                    if (MovieType.NEW_RELEASE.equals(movie.code()) && rental.days() > 2) {
                        frequentEnterPoints++;
                    }

                    // Append details to result
                    result.append("\t").append(movie.title()).append("\t").append(thisAmount).append("\n");
                    totalAmount += thisAmount;
                }

            }

            result.append("Amount owed is ").append(totalAmount).append("\n");
            result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

            return result.toString();
        }
    }
}
