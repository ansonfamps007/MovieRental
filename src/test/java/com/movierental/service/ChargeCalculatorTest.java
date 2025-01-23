package com.movierental.service;

import com.movierental.model.Movie;
import com.movierental.model.MovieType;
import com.movierental.util.ChargeCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ChargeCalculatorTest {

    @Test
    void testCalculateCharge_ForRegularMovie() {
        ChargeCalculator calculator = new ChargeCalculator();
        Movie regularMovie = new Movie("You've Got Mail", MovieType.REGULAR);

        double charge = calculator.calculateCharge(regularMovie, 4);
        assertEquals(5.0, charge, "Charge should be 5.0 for 4 days rental of regular movie");
    }

    @Test
    void testCalculateCharge_ForNewRelease() {
        ChargeCalculator calculator = new ChargeCalculator();
        Movie newRelease = new Movie("Fast & Furious X", MovieType.NEW_RELEASE);

        double charge = calculator.calculateCharge(newRelease, 3);
        assertEquals(9.0, charge, "Charge should be 9.0 for 3 days rental of new release");
    }

    @Test
    void testCalculateCharge_ForChildrensMovie() {
        ChargeCalculator calculator = new ChargeCalculator();
        Movie childrensMovie = new Movie("Cars", MovieType.CHILDRENS);

        double charge = calculator.calculateCharge(childrensMovie, 5);
        assertEquals(4.5, charge, "Charge should be 4.5 for 5 days rental of children's movie");
    }
}
