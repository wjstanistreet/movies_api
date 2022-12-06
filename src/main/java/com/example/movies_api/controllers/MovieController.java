package com.example.movies_api.controllers;

import com.example.movies_api.models.Movie;
import com.example.movies_api.models.Reply;
import com.example.movies_api.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id){
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Reply> newMovie(@RequestBody Movie movie){
        Reply reply = movieService.newMovie(movie);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Reply> patchMovie(@RequestBody Movie movie, @PathVariable int id){
        Reply reply = movieService.putMovie(movie, id);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Reply> deleteMovie(@PathVariable int id){
        Reply reply = movieService.deleteMovie(id);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

}
