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

    public Reply newMovie(Movie movie){
        movieRepository.save(movie);
        return new Reply(String.format("Added %s to Movies in Cinema", movie.getTitle()));
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int id){
        return movieRepository.findById(id);
    }

    public Reply putMovie(Movie movie, int id){
        Movie movieById = movieRepository.findById(id).get();
        if (!movieById.getTitle().equals(movie.getTitle())){
            movieById.setTitle(movie.getTitle());
        }
        if (movieById.getRating() != movie.getRating()){
            movieById.setRating(movie.getRating());
        }
        if (movieById.getDuration() != movie.getDuration()){
            movieById.setDuration(movie.getDuration());
        }
        movieRepository.save(movieById);

        return new Reply(String.format("Movie entry %d has been changed to title: %s, rating: %d, duration: %d", id, movieById.getTitle(), movieById.getRating(), movieById.getDuration()));
    }

    public Reply deleteMovie(int id){
        String title = movieRepository.findById(id).get().getTitle();
        movieRepository.deleteById(id);
        return new Reply(String.format("Movie entry %d, title: %s has been deleted", id, title));
    }
}
