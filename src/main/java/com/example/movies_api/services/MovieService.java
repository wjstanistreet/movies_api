package com.example.movies_api.services;

import com.example.movies_api.models.Movie;
import com.example.movies_api.models.Reply;
import com.example.movies_api.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieService(){

    }

    public Reply newMovie(String title, int rating, int duration){
        Movie movie = new Movie(title, rating, duration);
        movieRepository.save(movie);
        return new Reply(String.format("Added %s to Movies in Cinema", title));
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int id){
        return movieRepository.findById(id);
    }
}
