package com.movierental.service;

import com.movierental.dao.MovieDAO;
import com.movierental.exception.MovieNotFoundException;
import com.movierental.model.Customer;
import com.movierental.model.Movie;
import com.movierental.model.MovieRental;
import com.movierental.model.MovieType;
import com.movierental.service.impl.RentalInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RentalInfoServiceTest {

    private RentalInfoService rentalInfoService;
    private ChargeCalculator chargeCalculator;
    private MovieDAO movieDAO;
    private StatementGeneratorService statementGeneratorService;

    @BeforeEach
    void setUp() {
        chargeCalculator = mock(ChargeCalculator.class);
        movieDAO = mock(MovieDAO.class);
        statementGeneratorService = mock(StatementGeneratorService.class);
        rentalInfoService = new RentalInfoServiceImpl(movieDAO, chargeCalculator, statementGeneratorService);
    }

    @Test
    void testGenerateStatement_ValidRental() {

        Customer customer = new Customer("C. U. Stomer", Arrays.asList(
                new MovieRental("F001", 3),  // Movie: "You've Got Mail", rental for 3 days
                new MovieRental("F002", 2)   // Movie: "Matrix", rental for 2 days
        ));

        // Mocking the movie dao
        when(movieDAO.getMovieById(anyString()))
                .thenReturn(Optional.of(new Movie("You've Got Mail", MovieType.REGULAR)))
                .thenReturn(Optional.of(new Movie("Matrix", MovieType.REGULAR)));

        // Mocking the charge calculator
        when(chargeCalculator.calculateCharge(any(Movie.class), anyInt()))
                .thenReturn(3.5)
                .thenReturn(2.0);

        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append("C. U. Stomer").append(":\n");
        result.append("\tYou've Got Mail\t3.5\n").append("\tMatrix\t2.0\n");
        result.append("Amount owed is ").append(5.5).append("\n");
        result.append("You earned ").append(2).append(" frequent points\n");

        when(statementGeneratorService.generateStatement(any(Customer.class), anyDouble(),anyInt(), any(StringBuilder.class)))
                .thenReturn(result.toString());


        String statement = rentalInfoService.generateStatement(customer);

        String expected = "Rental Record for C. U. Stomer:\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tMatrix\t2.0\n" +
                "Amount owed is 5.5\n" +
                "You earned 2 frequent points\n";

        assertEquals(expected, statement);
    }

    @Test
    void testGenerateStatement_WithNotFoundMovieMsg() {

        Customer customer = new Customer("C. U. Stomer", List.of());

        // Mocking the charge calculator for valid movies
        when(chargeCalculator.calculateCharge(any(Movie.class), anyInt()))
                .thenReturn(3.5);

        Exception exception = assertThrows(MovieNotFoundException.class, () -> {
            rentalInfoService.generateStatement(customer);
        });

        // Assert
        String expected = "Movie with ID No rental details found ! not found.";

        assertEquals(expected, exception.getLocalizedMessage());
    }

    @Test
    void testGenerateStatement_IllegalArgumentExceptionMsg() {

        Customer customer = new Customer("C. U. Stomer", Arrays.asList(
                new MovieRental("F001", 3),  // Movie: "You've Got Mail", rental for 3 days
                new MovieRental("F002", 2)   // Movie: "Matrix", rental for 2 days
        ));

        // Mocking the charge calculator for valid movies
        when(chargeCalculator.calculateCharge(any(Movie.class), anyInt()))
                .thenReturn(3.5);

        when(movieDAO.getMovieById(anyString()))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalInfoService.generateStatement(customer);
        });

        // Assert
        String expected = "Invalid movie ID: F001";

        assertEquals(expected, exception.getLocalizedMessage());
    }
}
