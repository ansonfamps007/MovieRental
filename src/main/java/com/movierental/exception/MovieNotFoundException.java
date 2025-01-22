package com.movierental.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String movieId) {
        super("Movie with ID " + movieId + " not found.");
    }
}
