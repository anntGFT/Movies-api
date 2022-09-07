package com.sinensia.moviesapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
public class MovieService {

    public ResponseEntity<String> init(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOWYwZGM3MmJhY2FlYTA5ZDViZmM2ZjdmZmE1MTY1NiIsInN1YiI6IjYzMTVhZDFkMG" +
                "MxMjU1MDA5MjNhZTM3YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Y8bmdpj8dHbfv_-o-g9yWL3LjSMPeBcT59c3dM_9z6c");
        HttpEntity<?> request = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET,request,String.class);
    }
}
