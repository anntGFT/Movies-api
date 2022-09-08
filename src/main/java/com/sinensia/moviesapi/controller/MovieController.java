package com.sinensia.moviesapi.controller;

import com.sinensia.moviesapi.model.User;
import com.sinensia.moviesapi.repository.UserRepository;
import com.sinensia.moviesapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MovieController {

    @Autowired
    MovieService service;
    @Autowired
    UserRepository repository;


    @GetMapping("/api/genre/movie/list")
    public HashMap<?,?> getAllGenres() {

        return service.getAllGenres();
    }
    @GetMapping("api/movie/popular")
    public HashMap<?,?> getPopularMovies(){

        return service.getPopular();
    }
    @GetMapping("api/movie/top_rated")
    public HashMap<?,?> getTopRated(){

        return service.getTopRated();
    }
    @GetMapping("api/movie/{movie_id}")
    public HashMap<?,?> getMovieById(@AuthenticationPrincipal UserDetails user, @PathVariable Integer movie_id){

        User userMovie = repository.findByUsernameAndMovie(user.getUsername(),movie_id.toString()).orElse(null);
        HashMap<String, Object> movie = service.getMovieById(movie_id);

        if(userMovie != null){
            movie.put("favorite",userMovie.getFavorite());
            movie.put("personal_rating",userMovie.getPersonalRating());
            movie.put("notes",userMovie.getNotes());
        }

        return movie;
    }

    @PatchMapping("api/movie/{movie_id}")
    public ResponseEntity<User> putFavoritePersonalRatingNotes(@PathVariable Integer movie_id, @RequestBody User newUserMovie, @AuthenticationPrincipal UserDetails user){

        User updatedMovie = repository.findByUsernameAndMovie(user.getUsername(),movie_id.toString()).orElse(null);
        if(updatedMovie == null){
            updatedMovie = new User();
        }
        updatedMovie.setUsername(user.getUsername());
        updatedMovie.setMovie(movie_id.toString());
        updatedMovie.setFavorite(newUserMovie.getFavorite());
        updatedMovie.setPersonalRating(newUserMovie.getPersonalRating());
        updatedMovie.setNotes((newUserMovie.getNotes()));

        repository.save(updatedMovie);

        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @GetMapping("api/movie/{movie_id}/credits")
    public HashMap<?,?> getCastAndCrew(@PathVariable Integer movie_id){

        return service.getCastAndCrew(movie_id);
    }
    @GetMapping("api/movie/{movie_id}/images")
    public HashMap<?,?> getImages(@PathVariable Integer movie_id){

        return service.getAllImages(movie_id);
    }
    @GetMapping("api/movie/{movie_id}/keywords")
    public HashMap<?,?> getKeywords(@PathVariable Integer movie_id){

        return service.getKeywords(movie_id);
    }
    @GetMapping("api/movie/{movie_id}/recommendations")
    public HashMap<?,?> getRecommendations(@PathVariable Integer movie_id){

        return service.getRecommendations(movie_id);
    }
    @GetMapping("api/movie/{movie_id}/similar")
    public HashMap<?,?> getSimilar(@PathVariable Integer movie_id){

        return service.getSimilarMovies(movie_id);
    }

}
