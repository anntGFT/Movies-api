package com.sinensia.moviesapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
public class MovieService {
    WebClient webClient = WebClient.create("https://api.themoviedb.org/3/");
    @Value("${tmdb.api_key}")
    private String apiKey;

    public HashMap<?,?> getAllGenres() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("genre/movie/list")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<?,?> getPopular() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/popular")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<?,?> getTopRated() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/top_rated")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<String,Object> getMovieById(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString())
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<?,?> getCastAndCrew(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/credits")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<?,?> getAllImages(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/images")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<?,?> getKeywords(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/keywords")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<?,?> getRecommendations(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/recommendations")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }

    public HashMap<?,?> getSimilarMovies(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/similar")
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
}
