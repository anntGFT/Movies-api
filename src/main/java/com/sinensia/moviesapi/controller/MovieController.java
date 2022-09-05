package com.sinensia.moviesapi.controller;

import com.sinensia.moviesapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    MovieService service;



    @GetMapping("api/genre/movie/list")
    public ResponseEntity<String> getListGenres(){
        String url = "https://api.themoviedb.org/3/genre/movie/list";
        return service.init(url);
    }

    @GetMapping("api/movie/popular")
    public ResponseEntity<String> getPopular(){
        String url = "https://api.themoviedb.org/3/movie/popular";
        return service.init(url);
    }

    @GetMapping("api/movie/top_rated")
    public ResponseEntity<String> getTop(){
        String url = "https://api.themoviedb.org/3/movie/top_rated";
        return service.init(url);
    }

    @GetMapping("api/movie/{movie_id}")
    public ResponseEntity<String> getMovieById(@PathVariable int movie_id){
        String url = "https://api.themoviedb.org/3/movie/" + movie_id;
        return service.init(url);
    }

    @GetMapping("api/movie/{movie_id}/credits")
    public ResponseEntity<String> getCreditsFromMovieById(@PathVariable int movie_id){
        String url = "https://api.themoviedb.org/3/movie/credits" + movie_id + "/credits";
        return service.init(url);
    }

    @GetMapping("api/movie/{movie_id}/images")
    public ResponseEntity<String> getImageFromMovieById(@PathVariable int movie_id){
        String url = "https://api.themoviedb.org/3/movie/" + movie_id + "/images";
        return service.init(url);
    }

    @GetMapping("api/movie/{movie_id}/keywords")
    public ResponseEntity<String> getKeywordsFromMovieById(@PathVariable int movie_id){
        String url = "https://api.themoviedb.org/3/movie/" + movie_id + "/keywords";
        return service.init(url);
    }
    @GetMapping("api/movie/{movie_id}/recommendations")
    public ResponseEntity<String> getRecommendationsFromMovieById(@PathVariable int movie_id){
        String url = "https://api.themoviedb.org/3/movie/" + movie_id + "/recommendations";
        return service.init(url);
    }
    @GetMapping("api/movie/{movie_id}/similar")
    public ResponseEntity<String> getSimilarFromMovieById(@PathVariable int movie_id){
        String url = "https://api.themoviedb.org/3/movie/" + movie_id + "/similar";
        return service.init(url);
    }

}
