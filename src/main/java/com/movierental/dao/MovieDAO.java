package com.movierental.dao;

import com.movierental.model.Movie;

import java.util.Optional;

public interface MovieDAO {
    Optional<Movie> getMovieById(String movieId);
}
