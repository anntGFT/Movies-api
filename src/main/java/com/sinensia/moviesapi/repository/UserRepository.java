package com.sinensia.moviesapi.repository;

import com.sinensia.moviesapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsernameAndMovie(String username, String movieId);
}
