package com.movierental.dao.impl;

import com.movierental.dao.MovieDAO;
import com.movierental.model.Movie;
import com.movierental.model.MovieType;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class MovieDAOImpl implements MovieDAO {

    //Hard code and initialize the map for demo purposes
    private final Map<String, Movie> movies = Map.of(
            "F001", new Movie("You've Got Mail", MovieType.REGULAR),
            "F002", new Movie("Matrix", MovieType.REGULAR),
            "F003", new Movie("Cars", MovieType.CHILDRENS),
            "F004", new Movie("Fast & Furious X", MovieType.NEW_RELEASE)
    );

    @Override
    public Optional<Movie> getMovieById(String movieId) {
        return Optional.ofNullable(movies.get(movieId));
    }
}
