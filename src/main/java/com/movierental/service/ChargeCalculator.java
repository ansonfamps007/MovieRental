package com.movierental.service;

import com.movierental.model.Movie;
import org.springframework.stereotype.Service;

/*

Class for calculate rental charge

* */
@Service
public class ChargeCalculator {

    public double calculateCharge(Movie movie, int days) {
        double charge;
        return switch (movie.code()) {
            case REGULAR -> {
                charge = 2;
                if (days > 2) {
                    charge += (days - 2) * 1.5;
                }
                yield charge;
            }
            case NEW_RELEASE -> days * 3;
            case CHILDRENS -> {
                charge = 1.5;
                if (days > 3) {
                    charge += (days - 3) * 1.5;
                }
                yield charge;
            }
            default -> throw new IllegalArgumentException("Invalid movie code: " + movie.code());
        };
    }
}
