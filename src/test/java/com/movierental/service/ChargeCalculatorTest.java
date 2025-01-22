package com.movierental.service;

import com.movierental.exception.MovieNotFoundException;
import com.movierental.model.Customer;
import com.movierental.model.Movie;
import com.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChargeCalculatorTest {

    @Test
    void testCalculateCharge_ForRegularMovie() {
        ChargeCalculator calculator = new ChargeCalculator();
        Movie regularMovie = new Movie("You've Got Mail", "regular");

        double charge = calculator.calculateCharge(regularMovie, 4);
        assertEquals(5.0, charge, "Charge should be 5.0 for 4 days rental of regular movie");
    }

    @Test
    void testCalculateCharge_ForNewRelease() {
        ChargeCalculator calculator = new ChargeCalculator();
        Movie newRelease = new Movie("Fast & Furious X", "new");

        double charge = calculator.calculateCharge(newRelease, 3);
        assertEquals(9.0, charge, "Charge should be 9.0 for 3 days rental of new release");
    }

    @Test
    void testCalculateCharge_ForChildrensMovie() {
        ChargeCalculator calculator = new ChargeCalculator();
        Movie childrensMovie = new Movie("Cars", "childrens");

        double charge = calculator.calculateCharge(childrensMovie, 5);
        assertEquals(4.5, charge, "Charge should be 4.5 for 5 days rental of children's movie");
    }
}
