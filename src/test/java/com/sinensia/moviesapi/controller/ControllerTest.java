package com.sinensia.moviesapi.controller;

import com.sinensia.moviesapi.model.User;
import com.sinensia.moviesapi.repository.UserRepository;
import com.sinensia.moviesapi.service.MovieService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.RequestEntity.patch;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;
    @MockBean
    private DataSource dataSource;
    @Autowired
    private UserRepository repository;

    @Test
    void getMovie() throws Exception {
        HashMap<String, Object> resultForMock = new HashMap<>();
        resultForMock.put("page",1);
        resultForMock.put("total_pages",600);
        resultForMock.put("results",0);
        resultForMock.put("total_results",600);



        given(movieService.getMovieById(1)).willReturn(resultForMock);

        ResultActions response = mockMvc.perform(get("/api/movie/{movie_id}",1));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect((ResultMatcher) jsonPath("$.page",is(1)))
                .andExpect((ResultMatcher) jsonPath("$.total_pages",is(600)))
                .andExpect((ResultMatcher) jsonPath("$.results",is(0)))
                .andExpect((ResultMatcher) jsonPath("$.total_results",is(600)))
        ;
    }

    @Test
    void getPopularMovies() throws Exception {
        this.mockMvc.perform(get("/api/movie/popular")).andExpect(status().isOk());
    }

    @Test
    void getTopRatedMovies() throws Exception {
        this.mockMvc.perform(get("/api/movie/top_rated")).andExpect(status().isOk());
    }

    @Test
    void getMovieCredits() throws Exception {
        this.mockMvc.perform(get("/api/movie/1/credits")).andExpect(status().isOk());
    }

    @Test
    void getMovieImages() throws Exception {
        this.mockMvc.perform(get("/api/movie/1/images")).andExpect(status().isOk());
    }

    @Test
    void getMovieKeyWords() throws Exception {
        this.mockMvc.perform(get("/api/movie/1/keywords")).andExpect(status().isOk());
    }


    @Test
    void getMovieRecommendations() throws Exception {
        this.mockMvc.perform(get("/api/movie/1/recommendations")).andExpect(status().isOk());
    }


    @Test
    void getSimilarMovies() throws Exception {
        this.mockMvc.perform(get("/api/movie/1/similar")).andExpect(status().isOk());
    }

    @Test
    void putFavoritePersonalRatingNotes() throws Exception {

        HashMap<String, Object> movieMockRequest = new HashMap<>();
        movieMockRequest.put("page",1);
        movieMockRequest.put("total_pages",35032);
        movieMockRequest.put("results",0);
        movieMockRequest.put("total_results",35032);
        Integer id = 550;
        User userMovie = new User();

        Principal principal = () -> "admin";

        given(repository.findByUsernameAndMovie(principal.getName(),id.toString())).willReturn(Optional.of(userMovie));
        given(repository.save(userMovie)).willAnswer((invocation)->invocation.getArgument(0));

        ResultActions response = mockMvc.perform((RequestBuilder) patch("/api/movie/{movie_id}",id)

        );

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect((ResultMatcher) jsonPath("$.id",is(IsNull.nullValue())))
                .andExpect((ResultMatcher) jsonPath("$.username",is("user")))
                .andExpect((ResultMatcher) jsonPath("$.movie",is(id.toString())))
                .andExpect((ResultMatcher) jsonPath("$.favorite",is(IsNull.nullValue())))
                .andExpect((ResultMatcher) jsonPath("$.personal_rating",is(IsNull.nullValue())))
                .andExpect((ResultMatcher) jsonPath("$.notes",is(IsNull.nullValue())))

        ;
    }
}
