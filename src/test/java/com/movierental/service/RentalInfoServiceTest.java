package com.movierental.service;

import com.movierental.exception.MovieNotFoundException;
import com.movierental.model.Customer;
import com.movierental.model.Movie;
import com.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RentalInfoServiceTest {

    private RentalInfoService rentalInfoService;
    private ChargeCalculator chargeCalculator;

    @BeforeEach
    void setUp() {
        chargeCalculator = mock(ChargeCalculator.class);
        rentalInfoService = new RentalInfoService(chargeCalculator);
    }

    @Test
    void testGenerateStatement_ValidRental() {
        // Arrange
        Customer customer = new Customer("C. U. Stomer", Arrays.asList(
                new MovieRental("F001", 3),  // Movie: "You've Got Mail", rental for 3 days
                new MovieRental("F002", 2)   // Movie: "Matrix", rental for 2 days
        ));

        // Mocking the charge calculator
        when(chargeCalculator.calculateCharge(any(Movie.class), anyInt()))
                .thenReturn(3.5)  // Assuming charge for each movie is 3.5 for simplicity
                .thenReturn(2.0); // Second movie's charge is 2.0

        // Act
        String statement = rentalInfoService.generateStatement(customer);

        // Assert
        String expected = "Rental Record for C. U. Stomer:\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tMatrix\t2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent points\n";

        assertEquals(expected, statement);
    }

    @Test
    void testGenerateStatement_WithNotFoundMovieMessage() {
        // Arrange
        Customer customer = new Customer("C. U. Stomer", Arrays.asList());

        // Mocking the charge calculator for valid movies
        when(chargeCalculator.calculateCharge(any(Movie.class), anyInt()))
                .thenReturn(3.5);  // Assuming charge for each movie is 3.5 for simplicity

        // Act

        Exception exception = assertThrows(MovieNotFoundException.class, () -> {
            rentalInfoService.generateStatement(customer);
        });

        // Assert
        String expected = "Movie with ID No rental details found ! not found.";

        assertEquals(expected, exception.getLocalizedMessage());
    }
}
